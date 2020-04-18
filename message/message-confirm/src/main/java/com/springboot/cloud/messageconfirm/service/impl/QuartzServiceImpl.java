package com.springboot.cloud.messageconfirm.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.messageconfirm.config.quartz.QuartzHelper;
import com.springboot.cloud.messageconfirm.entity.form.JobQueryForm;
import com.springboot.cloud.messageconfirm.entity.vo.QuartzJob;
import com.springboot.cloud.messageconfirm.mapper.QuartzMapper;
import com.springboot.cloud.messageconfirm.service.IQuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuartzServiceImpl implements IQuartzService {

    @Autowired
    private QuartzMapper quartzMapper;

    @Autowired
    private QuartzHelper quartzHelper;

    @Override
    public Page<QuartzJob> findJobDetails(Page<QuartzJob> page, JobQueryForm jobQueryForm) {

        Page<QuartzJob> jobPage = quartzMapper.findJobDetails(page,jobQueryForm);
        try {
            for(QuartzJob quartzJob : jobPage.getRecords()) {
                quartzHelper.setQuartzJobDetail(quartzJob);
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
            throw new ServiceException(SystemErrorType.MESSAGE100500033);
        }
        return jobPage;
    }
}

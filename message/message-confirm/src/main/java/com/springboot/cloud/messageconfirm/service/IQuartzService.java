package com.springboot.cloud.messageconfirm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.messageconfirm.entity.form.JobQueryForm;
import com.springboot.cloud.messageconfirm.entity.vo.QuartzJob;

public interface IQuartzService {
    Page<QuartzJob> findJobDetails(Page<QuartzJob> page, JobQueryForm jobQueryForm);
}

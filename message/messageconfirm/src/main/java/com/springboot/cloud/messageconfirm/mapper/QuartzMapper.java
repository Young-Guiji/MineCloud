package com.springboot.cloud.messageconfirm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.messageconfirm.entity.form.JobQueryForm;
import com.springboot.cloud.messageconfirm.entity.vo.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuartzMapper{
    Page<QuartzJob> findJobDetails(Page<QuartzJob> page, @Param("jobQueryForm") JobQueryForm jobQueryForm);
}

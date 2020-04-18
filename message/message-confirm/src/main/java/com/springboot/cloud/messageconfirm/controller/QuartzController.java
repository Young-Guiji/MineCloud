package com.springboot.cloud.messageconfirm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.messageconfirm.config.quartz.QuartzConstant;
import com.springboot.cloud.messageconfirm.config.quartz.QuartzHelper;
import com.springboot.cloud.messageconfirm.entity.form.JobQueryForm;
import com.springboot.cloud.messageconfirm.entity.vo.JobVo;
import com.springboot.cloud.messageconfirm.entity.vo.QuartzJob;
import com.springboot.cloud.messageconfirm.service.IQuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/quartz", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - QuartzController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuartzController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(QuartzController.class);
	
	@Autowired
	private QuartzHelper quartzHelper;
	@Autowired
	private IQuartzService quartzService;
	
	@ApiOperation(value="列出所有调试器名字")
	@RequestMapping(value="listSchedulerName",method= RequestMethod.POST)
	public Result getSchedulerName(){
		Map<String, Scheduler> map = quartzHelper.getAllScheduler();
		List<String> list = new ArrayList<>();
		for(String key : map.keySet()) {
			list.add(key);
		}
		return Result.success(list);
	}
	
	@ApiOperation(value="批处理分页")
	@RequestMapping(value="page",method= RequestMethod.POST)
	public Result page (@RequestBody JobQueryForm queryForm){
		Page<QuartzJob> pages = quartzService.findJobDetails(queryForm.getPage(),queryForm);
		return Result.success(pages);
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value="新增批处理")
	@RequestMapping(value="add",method= RequestMethod.POST)
	public Result add(@RequestBody Map<String, Object> params){
		try {
			Scheduler scheduler = quartzHelper.getScheduler();
			createScheduleJob(scheduler,params);
		} catch (SecurityException e) {
			logger.error(e.getMessage());
			return Result.fail("创建任务失败");
		}
		return Result.success("创建任务成功");
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value="修改批处理")
	@RequestMapping(value="update",method= RequestMethod.POST)
	public Result update(@RequestBody Map<String, Object> params){
		Scheduler scheduler = quartzHelper.getScheduler();

		JobKey oldJobKey = new JobKey(params.get(QuartzConstant.OLD_QUARTZ_JOB_NAME).toString(),params.get(QuartzConstant.OLD_QUARTZ_JOB_GROUP_NAME).toString());
		TriggerKey oldTriggerKey = new TriggerKey(params.get(QuartzConstant.OLD_TRIGGER_NAME).toString(), params.get(QuartzConstant.OLD_TRIGGER_GROUP_NAME).toString());
		try {
			quartzHelper.removeJobAndTrigger(scheduler,oldJobKey,oldTriggerKey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return Result.fail("修改任务失败");
		}
		params.remove(QuartzConstant.OLD_QUARTZ_JOB_NAME);
		params.remove(QuartzConstant.OLD_QUARTZ_JOB_GROUP_NAME);
		params.remove(QuartzConstant.OLD_TRIGGER_NAME);
		params.remove(QuartzConstant.OLD_TRIGGER_GROUP_NAME);
		createScheduleJob(scheduler,params);
		return Result.success("修改任务成功");
	}

	@ApiOperation(value="删除批处理，删除trigger与job")
	@RequestMapping(value="delete",method= RequestMethod.POST)
	public Result delete(@RequestBody JobVo jobVo){
		JobKey jobKey = new JobKey(jobVo.getJobName(),jobVo.getJobGroup());
		TriggerKey triggerKey = new TriggerKey(jobVo.getTriggerName(),jobVo.getTriggerGroup());
		try {
			Scheduler scheduler = quartzHelper.getScheduler();
			quartzHelper.removeJobAndTrigger(scheduler,jobKey, triggerKey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return Result.fail("删除任务失败");
		}
		return Result.success("删除任务成功");
	}
	
	@ApiOperation(value="暂停批处理，暂停job的trigger")
	@RequestMapping(value="pause",method= RequestMethod.POST)
	public Result pause(@RequestBody JobVo jobVo){
		JobKey jobKey = new JobKey(jobVo.getJobName(),jobVo.getJobGroup());
		try {
			Scheduler scheduler = quartzHelper.getScheduler();
			quartzHelper.pauseJob(scheduler,jobKey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return Result.fail("暂停任务失败");
		}
		return Result.success("暂停任务成功");
	}
	
	@ApiOperation(value="恢复批处理")
	@RequestMapping(value="resume",method= RequestMethod.POST)
	public Result resume(@RequestBody JobVo jobVo){
		JobKey jobKey = new JobKey(jobVo.getJobName(),jobVo.getJobGroup());
		try {
			Scheduler scheduler = quartzHelper.getScheduler();
			quartzHelper.resumeJob(scheduler,jobKey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return Result.fail("恢复任务失败");
		}
		return Result.success("恢复任务成功");
	}
	
	@ApiOperation(value="立即执行批处理")
	@RequestMapping(value="fire",method= RequestMethod.POST)
	public Result fire(@RequestBody JobVo jobVo){
		JobKey jobKey = new JobKey(jobVo.getJobName(),jobVo.getJobGroup());
		try {
			Scheduler scheduler = quartzHelper.getScheduler();
			quartzHelper.triggerJob(scheduler,jobKey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			return Result.fail("恢复任务失败");
		}
		return Result.success("立即执行任务成功");
	}
	
	@ApiOperation(value="获得任务详细信息")
	@RequestMapping(value="getJobDetail",method= RequestMethod.POST)
	public Result getJobDetail(String jobName){
		Map<String,Object> jobDataMap = new HashMap<String,Object>();
		jobDataMap.put(QuartzConstant.QUARTZ_JOB_NAME,jobName);
		jobDataMap = quartzHelper.getDataByAnnotation(jobDataMap);
		return Result.success(jobDataMap);
	}

	private void createScheduleJob(Scheduler scheduler, Map<String, Object> params){
		try {
			JobDetail jobDetail = quartzHelper.jobCreate(scheduler,(Class<? extends Job>) Class.forName(params.get(QuartzConstant.QUARTZ_TARGET_CLASS).toString()), params);
			String cron = jobDetail.getJobDataMap().get(QuartzConstant.CRON).toString();
			String triggerName = jobDetail.getJobDataMap().get(QuartzConstant.TRIGGER_NAME).toString();
			String triggerGroupName = jobDetail.getJobDataMap().get(QuartzConstant.TRIGGER_GROUP_NAME).toString();
			Trigger trigger = quartzHelper.triggerCreate(scheduler,triggerName, triggerGroupName, cron);
			quartzHelper.scheduleJob(scheduler,jobDetail, trigger);
		} catch (SecurityException | ClassNotFoundException | SchedulerException e) {
			logger.error(e.getMessage());
			throw new ServiceException(SystemErrorType.MESSAGE100500032);
		}
	}
}

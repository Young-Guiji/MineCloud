package com.springboot.cloud.messageconfirm.config.quartz;

import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.messageconfirm.entity.vo.QuartzJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class QuartzHelper {

	@Value("${spring.quartz.properties.org.quartz.scheduler.instanceName}")
	private String schedulerName;

	@Autowired
	private Map<String, Scheduler> schedulerMap = new HashMap<>();

	/**
	 * 获取所有调度器
	 */
	public Map<String, Scheduler> getAllScheduler(){
		return schedulerMap;
	}
	
	/**
	 * 获取调试器
	 */
	public Scheduler getScheduler(){
		return schedulerMap.get(schedulerName);
	}
	
	/**
	 * 创建任务
	 * @param scheduler
	 * @param jobClass
	 * @param dataMap
	 * @return
	 * @throws SchedulerException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public JobDetail jobCreate(Scheduler scheduler, Class<? extends Job> jobClass, Map<? extends String, ? extends Object> dataMap) throws SchedulerException, SecurityException{
		JobDataMap jobDataMap = getDataByAnnotation(dataMap);

		String jobName = jobDataMap.getString(QuartzConstant.QUARTZ_JOB_NAME);
		String jobGroupName = jobDataMap.getString(QuartzConstant.QUARTZ_JOB_GROUP_NAME);
		String jobDescription = jobDataMap.getString(QuartzConstant.QUARTZ_JOB_DESC);
		if(scheduler.checkExists(new JobKey(jobName, jobGroupName))){
			throw new ServiceException(SystemErrorType.MESSAGE100500030);
		} else {
			JobDetail job = JobBuilder.newJob(jobClass).setJobData(jobDataMap).withDescription(jobDescription).withIdentity(jobName, jobGroupName).build();
			return job;
		}
	}
	
	/**
	 * 创建触发器
	 * @param triggerName
	 * @param triggerGroupName
	 * @param cronExpression
	 * @return
	 * @throws SchedulerException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Trigger triggerCreate(Scheduler scheduler, String triggerName, String triggerGroupName, String cronExpression) throws SchedulerException {
		if(scheduler.checkExists(new TriggerKey(triggerName, triggerGroupName))) {
			throw new ServiceException(SystemErrorType.MESSAGE100500031);
		}else {
			ScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
			Trigger trigger = TriggerBuilder.newTrigger().withSchedule(schedBuilder).withIdentity(triggerName, triggerGroupName).build();
			return trigger;
		}
	}
	
	/**
	 * 添加任务与触发器
	 * @param scheduler
	 * @param jobDetail
	 * @param trigger
	 * @throws SchedulerException
	 */
	public void scheduleJob(Scheduler scheduler, JobDetail jobDetail, Trigger trigger) throws SchedulerException {
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 立即执行任务
	 */
	public void triggerJob(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
		scheduler.triggerJob(jobKey);
	}
	
	/**
	 * 立即执行任务
	 * @param scheduler
	 * @param jobKey
	 * @param data
	 * @throws SchedulerException
	 */
	public void triggerJob(Scheduler scheduler, JobKey jobKey, JobDataMap data) throws SchedulerException {
		scheduler.triggerJob(jobKey, data);
	}
	
	/**
	 * 暂停单个任务
	 * @param scheduler
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void pauseJob(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
		scheduler.pauseJob(jobKey);
	}
	
	/**
	 * 恢复任务
	 * @param scheduler
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void resumeJob(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
		scheduler.resumeJob(jobKey);
	}
	
	/**
	 * 删除触发器与任务
	 * @param scheduler
	 * @param jobKey
	 * @param triggerKey
	 * @throws SchedulerException
	 */
	public void removeJobAndTrigger(Scheduler scheduler, JobKey jobKey , TriggerKey triggerKey) throws SchedulerException {
		TriggerState triggerState  = scheduler.getTriggerState(triggerKey);
		if(triggerState != TriggerState.PAUSED){
			scheduler.pauseTrigger(triggerKey);
		}
		scheduler.unscheduleJob(triggerKey);
		scheduler.deleteJob(jobKey);
	}

	public JobDataMap getDataByAnnotation(Map<? extends String, ? extends Object> dataMap) {
		JobDataMap jobDataMap = new JobDataMap();
		String jobName = dataMap.get(QuartzConstant.QUARTZ_JOB_NAME).toString();
		jobDataMap.put(QuartzConstant.QUARTZ_JOB_NAME, jobName);
		jobDataMap.put(QuartzConstant.QUARTZ_JOB_GROUP_NAME, jobName+"_group");
		jobDataMap.put(QuartzConstant.TRIGGER_NAME, jobName+"_trigger");
		jobDataMap.put(QuartzConstant.TRIGGER_GROUP_NAME, jobName+"_trigger_group");
		for(String key : dataMap.keySet()){
			jobDataMap.put(key,dataMap.get(key));
		}
		return jobDataMap;
	}

	public String status(TriggerState status){
		if(status == TriggerState.NORMAL){
			return "正常";
		}else if(status == TriggerState.PAUSED){
			return "暂停";
		}else{
			return status.toString();
		}
	}

	public void setQuartzJobDetail(QuartzJob quartzJob) throws SchedulerException {
		Scheduler scheduler = this.getScheduler();
		Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupContains(quartzJob.getJobGroup()));
		if (jobKeys != null) {
			List<JobKey> jobList = new ArrayList(jobKeys);
			JobDetail jobDetail = scheduler.getJobDetail(jobList.get(0));
			quartzJob.setJobData(jobDetail.getJobDataMap());
			List<CronTrigger> triggers = (List<CronTrigger>) scheduler.getTriggersOfJob(jobList.get(0));
			if (triggers != null && triggers.size() > 0) {
				CronTrigger trigger = triggers.get(0);
				TriggerKey triggerKey = trigger.getKey();
				quartzJob.setTriggerName(triggerKey.getName());
				quartzJob.setTriggerGroup(triggerKey.getGroup());
				quartzJob.setCron(trigger.getCronExpression());
				quartzJob.setPreviousFireTime(trigger.getPreviousFireTime());
				quartzJob.setNextFireTime(trigger.getNextFireTime());
				TriggerState triggerState = scheduler.getTriggerState(triggerKey);
				quartzJob.setStatus(this.status(triggerState));
			}
		}
	}
}

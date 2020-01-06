package com.springboot.cloud.messageconfirm.entity.vo;

import java.util.Date;
import java.util.Map;

public class QuartzJob {
	private String schedulerName;
	private String jobName;
	private String jobGroup;
	private String jobClass;

	private Map<String,Object> jobData;
	private Map<String,Object> defindeData;
	/** 任务描述 */
	private String description;
	
	private String triggerName;
	private String triggerGroup;
	/** 时间表达式 */
	private String cron;
	/** 上次执行时间 */
	private Date previousFireTime;
	/** 下次执行时间 */
	private Date nextFireTime;
	/** 状态 */
	private String status;
//	
//	private List<QuartzTrigger> quartzTriggers;
	
	public String getJobName() {
		return jobName;
	}
	public String getSchedulerName() {
		return schedulerName;
	}
	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public List<QuartzTrigger> getQuartzTriggers() {
//		return quartzTriggers;
//	}
//	public void setQuartzTriggers(List<QuartzTrigger> quartzTriggers) {
//		this.quartzTriggers = quartzTriggers;
//	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public Date getPreviousFireTime() {
		return previousFireTime;
	}
	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}
	public Date getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, Object> getJobData() {
		return jobData;
	}
	public void setJobData(Map<String, Object> jobData) {
		this.jobData = jobData;
	}
	public Map<String, Object> getDefindeData() {
		return defindeData;
	}
	public void setDefindeData(Map<String, Object> defindeData) {
		this.defindeData = defindeData;
	}
	
	
	
}

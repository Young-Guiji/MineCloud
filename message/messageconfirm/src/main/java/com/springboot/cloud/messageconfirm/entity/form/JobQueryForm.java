package com.springboot.cloud.messageconfirm.entity.form;

import com.springboot.cloud.common.core.entity.form.BaseForm;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;


public class JobQueryForm extends BaseQueryForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9089997093560800570L;
	
	private String schedulerName;

	private String jobName;
	
	private String jobGroup;
	
	private String triggerName;
	
	private String triggerGroup;
	
	private String jobClassName;

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public String getJobName() {
		return jobName;
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

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

}

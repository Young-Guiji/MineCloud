package com.springboot.cloud.messageconfirm.config.quartz;

/**
 * quartz 常量
 */
public class QuartzConstant {
	
	/** 任务job class */
	public final static String QUARTZ_TARGET_CLASS = "targetClass";
	/** 任务 job method */
	public final static String QUARTZ_TARGET_METHOD = "targetMethod";
	/** 任务重试次数 */
	public final static String QUARTZ_RETRY = "retry";
	/** 取模余值 */
	public final static String MOD_REST = "mod_rest";
	/** 模值 */
	public final static String MOD_VALUE = "mod_value";
	/** 默认分页数 */
	public final static String PAGE_SIZE = "pageSize";
	/** 默认起始页数 */
	public final static String PAGE = "page";
	/** 任务名 */
	public final static String QUARTZ_JOB_NAME = "jobName";
	/** 任务组名 */
	public final static String QUARTZ_JOB_GROUP_NAME = "jobGroupName";
	/** 任务描述 */
	public final static String QUARTZ_JOB_DESC= "description";
	/** 旧任务名 */
	public final static String OLD_QUARTZ_JOB_NAME = "oldJobName";
	/** 旧任务组名 */
	public final static String OLD_QUARTZ_JOB_GROUP_NAME = "oldJobGroupName";
	/** 任务模式 */
	public final static String QUARTZ_JOB_TYPE = "jobType";
	/** 任务类型 */
	public final static String SCHEDUL_TYPE = "schedulType";
	/** 触发时间 */
	public final static String CRON = "cron";
	/** 触发器名称 */
	public final static String  TRIGGER_NAME = "triggerName";
	/** 触发器组 */
	public final static String TRIGGER_GROUP_NAME= "triggerGroupName";
	/** 旧触发器名称 */
	public final static String  OLD_TRIGGER_NAME = "oldTriggerName";
	/** 旧触发器组 */
	public final static String OLD_TRIGGER_GROUP_NAME= "oldTriggerGroupName";
	
}

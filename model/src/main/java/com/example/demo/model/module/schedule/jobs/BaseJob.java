package com.example.demo.model.module.schedule.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * job父类，包含一个抽象函方法，将实现推迟到具体的子类
 */
public abstract class BaseJob implements Job, Serializable {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public static final String APPLICATION_CONTEXT_KEY = "applicationContextKey";

  // 抽象方法，由子类实现，即具体的业务逻辑
  public abstract void action(JobExecutionContext context);

  /**
   * 统计时间
   *
   * @param context
   * @throws JobExecutionException
   */
  @Override
  public void execute(JobExecutionContext context) {
    logger.info("job: {}, trigger: {}, start running ", context.getJobDetail().getKey(),
        context.getTrigger().getKey());
    long start = System.currentTimeMillis();
    this.action(context);
    long end = System.currentTimeMillis();
    logger.info("job: {}, trigger: {}, cost: {} s", context.getJobDetail().getKey(),
        context.getTrigger().getKey(), (end - start) / 1000);
  }
}
package com.example.demo.model.module.schedule.service;

import com.example.demo.model.module.schedule.pojo.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 定时任务 service
 */
@Service
public class ScheduleJobService {

  private static final Logger log = LoggerFactory.getLogger(ScheduleJobService.class);

  @Autowired
  private Scheduler scheduler;

  /**
   * 添加定时任务
   */
  public void add(ScheduleJob scheduleJob) {
    @SuppressWarnings("rawtypes")
    Class job = null;
    try {
      job = Class.forName(scheduleJob.getClassName());
    } catch (ClassNotFoundException e1) {
      log.error("任务类没找到");
      e1.printStackTrace();
    }
    @SuppressWarnings("unchecked")
    JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(scheduleJob.getName(), scheduleJob.getGroup()).build();
    jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);

    //表达式调度构建器（可判断创建SimpleScheduleBuilder）
    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
    //按新的cronExpression表达式构建一个新的trigger
    CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getName(), scheduleJob.getGroup()).withDescription(scheduleJob.getDescription()).withSchedule(scheduleBuilder).build();
    try {
      scheduler.scheduleJob(jobDetail, trigger);
      log.info("定时任务添加成功");
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取所有JobDetail
   *
   * @return 结果集合
   */
  public List<JobDetail> getJobs() {
    try {
      GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
      Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
      List<JobDetail> jobDetails = new ArrayList<JobDetail>();
      for (JobKey key : jobKeys) {
        jobDetails.add(scheduler.getJobDetail(key));
      }
      return jobDetails;
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取所有计划中的任务
   *
   * @return 结果集合
   */
  public List<ScheduleJob> getAllScheduleJob() {
    List<ScheduleJob> scheduleJobList = new ArrayList<>();
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    try {
      Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
      for (JobKey jobKey : jobKeys) {
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
        for (Trigger trigger : triggers) {
          try {
            ScheduleJob scheduleJob = new ScheduleJob();
            scheduleJob.setName(jobKey.getName());
            scheduleJob.setGroup(jobKey.getGroup());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            scheduleJob.setStatus(triggerState.name());
            //获取要执行的定时任务类名
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            scheduleJob.setClassName(jobDetail.getJobClass().getName());
            //判断trigger
            if (trigger instanceof SimpleTrigger) {
              SimpleTrigger simple = (SimpleTrigger) trigger;
              scheduleJob.setCronExpression("重复次数:" + (simple.getRepeatCount() == -1 ?
                  "无限" : simple.getRepeatCount()) + ",重复间隔:" + (simple.getRepeatInterval() / 1000L));
              scheduleJob.setDescription(simple.getDescription());
            }
            if (trigger instanceof CronTrigger) {
              CronTrigger cron = (CronTrigger) trigger;
              scheduleJob.setCronExpression(cron.getCronExpression());
              scheduleJob.setDescription(cron.getDescription() == null ? ("触发器:" + trigger.getKey()) : cron.getDescription());
            }
            scheduleJobList.add(scheduleJob);
          } catch (SchedulerException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return scheduleJobList;
  }

  /**
   * 获取所有运行中的任务
   *
   * @return 结果集合
   */
  public List<ScheduleJob> getAllRuningScheduleJob() {
    List<ScheduleJob> scheduleJobList = null;
    try {
      List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
      scheduleJobList = new ArrayList<>(executingJobs.size());
      for (JobExecutionContext executingJob : executingJobs) {
        ScheduleJob scheduleJob = new ScheduleJob();
        JobDetail jobDetail = executingJob.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        Trigger trigger = executingJob.getTrigger();
        scheduleJob.setName(jobKey.getName());
        scheduleJob.setGroup(jobKey.getGroup());
        //scheduleJob.setDescription("触发器:" + trigger.getKey());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        scheduleJob.setStatus(triggerState.name());
        //获取要执行的定时任务类名
        scheduleJob.setClassName(jobDetail.getJobClass().getName());
        //判断trigger
        if (trigger instanceof SimpleTrigger) {
          SimpleTrigger simple = (SimpleTrigger) trigger;
          scheduleJob.setCronExpression("重复次数:" + (simple.getRepeatCount() == -1 ?
              "无限" : simple.getRepeatCount()) + ",重复间隔:" + (simple.getRepeatInterval() / 1000L));
          scheduleJob.setDescription(simple.getDescription());
        }
        if (trigger instanceof CronTrigger) {
          CronTrigger cron = (CronTrigger) trigger;
          scheduleJob.setCronExpression(cron.getCronExpression());
          scheduleJob.setDescription(cron.getDescription());
        }
        scheduleJobList.add(scheduleJob);
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return scheduleJobList;
  }

  /**
   * 获取所有的触发器
   *
   * @return 结果集合
   */
  public List<ScheduleJob> getTriggersInfo() {
//        try {
//            GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
//            Set<TriggerKey> Keys = scheduler.getTriggerKeys(matcher);
//            List<ScheduleJob> triggers = new ArrayList<ScheduleJob>();
//
//            for (TriggerKey key : Keys) {
//                Trigger trigger = scheduler.getTrigger(key);
//                ScheduleJob scheduleJob = new ScheduleJob();
//                scheduleJob.setName(trigger.getJobKey().getName());
//                scheduleJob.setGroup(trigger.getJobKey().getGroup());
//                scheduleJob.setStatus(scheduler.getTriggerState(key) + "");
//                if (trigger instanceof SimpleTrigger) {
//                    SimpleTrigger simple = (SimpleTrigger) trigger;
//                    scheduleJob.setCronExpression("重复次数:" + (simple.getRepeatCount() == -1 ?
//                            "无限" : simple.getRepeatCount()) + ",重复间隔:" + (simple.getRepeatInterval() / 1000L));
//                    scheduleJob.setDescription(simple.getDescription());
//                }
//                if (trigger instanceof CronTrigger) {
//                    CronTrigger cron = (CronTrigger) trigger;
//                    scheduleJob.setCronExpression(cron.getCronExpression());
//                    scheduleJob.setDescription(cron.getDescription());
//                }
//                triggers.add(scheduleJob);
//            }
//            return triggers;
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    return null;
  }

  /**
   * 暂停任务
   *
   * @param name  任务名
   * @param group 任务组
   */
  public void stopJob(String name, String group) {
    JobKey key = new JobKey(name, group);
    try {
      scheduler.pauseJob(key);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 恢复任务
   *
   * @param name  任务名
   * @param group 任务组
   */
  public void restartJob(String name, String group) {
    JobKey key = new JobKey(name, group);
    try {
      scheduler.resumeJob(key);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 立马执行一次任务
   *
   * @param name  任务名
   * @param group 任务组
   */
  public void startNowJob(String name, String group) {
    JobKey jobKey = JobKey.jobKey(name, group);
    try {
      scheduler.triggerJob(jobKey);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除任务
   *
   * @param name  任务名
   * @param group 任务组
   */
  public void delJob(String name, String group) {
    JobKey key = new JobKey(name, group);
    try {
      scheduler.deleteJob(key);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改触发器时间
   *
   * @param name  任务名
   * @param group 任务组
   * @param cron  cron表达式
   */
  public void modifyTrigger(String name, String group, String cron, String description) {
    try {
      TriggerKey key = TriggerKey.triggerKey(name, group);
      //Trigger trigger = scheduler.getTrigger(key);
      CronTrigger newTrigger = TriggerBuilder.newTrigger()
          .withIdentity(key)
          .withSchedule(CronScheduleBuilder.cronSchedule(cron))
          .withDescription(description)
          .build();
      scheduler.rescheduleJob(key, newTrigger);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 暂停调度器
   */
  public void stopScheduler() {
    try {
      if (!scheduler.isInStandbyMode()) {
        scheduler.standby();
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}

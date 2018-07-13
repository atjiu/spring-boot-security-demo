package com.example.demo.admin.job;

import com.example.demo.model.module.schedule.jobs.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试任务
 */
@DisallowConcurrentExecution
public class TestJob extends BaseJob {

  @Override
  public void action(JobExecutionContext context) {
    try {
      SchedulerContext schedulerContext = context.getScheduler().getContext();
      ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContextKey");
    } catch (SchedulerException e) {
      e.printStackTrace();
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        System.out.println(obj);
//        ScheduleJob scheduleJob = (ScheduleJob) obj;
    System.out.println("任务名称 = [" + context.getJobDetail().getClass().getName() + "]" + " 在 " + dateFormat.format(new Date()) + " 时运行");
  }
}

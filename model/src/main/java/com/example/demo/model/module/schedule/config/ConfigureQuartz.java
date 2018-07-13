package com.example.demo.model.module.schedule.config;

import com.example.demo.model.module.schedule.jobs.BaseJob;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
public class ConfigureQuartz {

  @Bean
  public JobFactory jobFactory() {
    return new SpringBeanJobFactory();
  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setOverwriteExistingJobs(true);

    // 延时启动
    factory.setStartupDelay(20);
    factory.setJobFactory(jobFactory);
    factory.setApplicationContextSchedulerContextKey(BaseJob.APPLICATION_CONTEXT_KEY);

    // 加载quartz数据源配置
    factory.setConfigLocation(new ClassPathResource("quartz.properties"));
    factory.setDataSource(dataSource);
    // 自定义Job Factory，用于Spring注入
    factory.setJobFactory(jobFactory());
    factory.setSchedulerName("scheduler");
    return factory;
  }


}

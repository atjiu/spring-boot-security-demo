package com.example.demo.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by tomoya at 12/26/17
 */
@Configuration
@PropertySource("classpath:config.properties")
public class SiteConfig {

  @Value("${attempts}")
  private Integer attempts;

  @Value("${attemptsWaitTime}")
  private Integer attemptsWaitTime;

  @Value("${loginPoints}")
  private Integer loginPoints;

  @Value("${pageSize}")
  private Integer pageSize;

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getAttempts() {
    return attempts;
  }

  public void setAttempts(Integer attempts) {
    this.attempts = attempts;
  }

  public Integer getAttemptsWaitTime() {
    return attemptsWaitTime;
  }

  public void setAttemptsWaitTime(Integer attemptsWaitTime) {
    this.attemptsWaitTime = attemptsWaitTime;
  }

  public Integer getLoginPoints() {
    return loginPoints;
  }

  public void setLoginPoints(Integer loginPoints) {
    this.loginPoints = loginPoints;
  }
}

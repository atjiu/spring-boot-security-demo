package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by tomoya at 12/26/17
 */
@Configuration
@ConfigurationProperties(prefix = "site")
public class SiteConfig {

	private Integer attempts;
	private Integer attemptsWaitTime;
	private Integer loginPoints;
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

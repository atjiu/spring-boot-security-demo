package com.example.demo.module.user.model;

import com.example.demo.module.security.model.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tomoya at 12/26/17
 */
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String username;

	private String password;

	// 帐户是否被禁
	@Column(columnDefinition = "BIT DEFAULT 0")
	private Boolean block;

	private Date inTime;

	// 尝试登录次数
	private int attempts;

	// 最后一次尝试登录的时间
	@Column(name = "attempts_time")
	private Date attemptsTime;

	// 用户与角色的关联关系
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			joinColumns = {
					@JoinColumn(name = "user_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "role_id")
			}
	)
	private Set<Role> roles = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getAttemptsTime() {
		return attemptsTime;
	}

	public void setAttemptsTime(Date attemptsTime) {
		this.attemptsTime = attemptsTime;
	}
}

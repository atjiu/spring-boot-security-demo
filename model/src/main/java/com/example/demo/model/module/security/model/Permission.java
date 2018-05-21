package com.example.demo.model.module.security.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tomoya at 12/26/17
 */
@Entity
public class Permission implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // 权限名称
  @Column(unique = true)
  private String name;

  // 权限描述
  private String description;

  // 授权链接
  private String url;

  // 父节点id
  private Integer pid;

  /**
   * 角色与权限的关联关系
   * mappedBy: 就是 Role.class 里的 Set<Permission> 的对象名
   */
  @ManyToMany(mappedBy = "permissions")
  private Set<Role> roles = new HashSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}

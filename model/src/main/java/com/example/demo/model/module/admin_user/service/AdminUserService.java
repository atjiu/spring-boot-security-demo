package com.example.demo.model.module.admin_user.service;

import com.example.demo.model.module.security.model.Role;
import com.example.demo.model.module.admin_user.model.AdminUser;
import com.example.demo.model.module.admin_user.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by tomoya at 12/26/17
 */
@Service
@Transactional
public class AdminUserService {

  @Autowired
  private AdminUserRepository adminUserRepository;

  public AdminUser findById(int id) {
    return adminUserRepository.findById(id);
  }

  public AdminUser findByUsername(String username) {
    return adminUserRepository.findByUsername(username);
  }

  public AdminUser save(AdminUser adminUser) {
    return adminUserRepository.save(adminUser);
  }

  public AdminUser save(AdminUser adminUser, Integer roleId) {
    Role role = new Role();
    role.setId(roleId);
    adminUser.setRole(role);
    adminUser = adminUserRepository.save(adminUser);
    return adminUser;
  }

  public Page<AdminUser> page(Integer pageNo, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "inTime");
    return adminUserRepository.findAll(pageable);
  }

  /**
   * 禁用用户
   *
   * @param id
   */
  public void block(Integer id) {
    AdminUser adminUser = findById(id);
    adminUser.setBlock(true);
    adminUserRepository.save(adminUser);
  }

  /**
   * 用户解禁
   *
   * @param id
   */
  public void unBlock(Integer id) {
    AdminUser adminUser = findById(id);
    adminUser.setBlock(false);
    adminUserRepository.save(adminUser);
  }

  public void deleteById(Integer id) {
    adminUserRepository.deleteById(id);
  }

  public void update(Integer id, String password, Integer roleId) {
    AdminUser adminUser = findById(id);
    if (!StringUtils.isEmpty(password)) {
      adminUser.setPassword(new BCryptPasswordEncoder().encode(password));
    }
    Role role = new Role();
    role.setId(roleId);
    adminUser.setRole(role);
    adminUserRepository.save(adminUser);
  }

}

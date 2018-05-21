package com.example.demo.module.security.service;

import com.example.demo.module.security.core.MyInvocationSecurityMetadataSource;
import com.example.demo.module.security.model.Permission;
import com.example.demo.module.security.model.Role;
import com.example.demo.module.security.repository.RoleRepository;
import com.example.demo.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tomoya at 12/26/17
 */
@Service
@Transactional
public class RoleService {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PermissionService permissionService;
  @Autowired
  private MyInvocationSecurityMetadataSource myInvocationSecurityMetadataSource;

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public void deleteById(Integer id) {
    Role role = findById(id);
    roleRepository.delete(role);
    //重新加载权限
    myInvocationSecurityMetadataSource.loadResourceDefine();
  }

  public Role findById(int id) {
    return roleRepository.findById(id);
  }

  public void save(Role role, Integer[] permissionIds) {
    if (permissionIds != null && permissionIds.length > 0) {
      Set<Permission> set = new HashSet<>();
      for (Integer permissionId : permissionIds) {
        Permission permission = permissionService.findById(permissionId);
        permission.setId(permissionId);
        set.add(permission);
      }
      role.setPermissions(set);
    }
    roleRepository.save(role);
    //重新加载权限
    myInvocationSecurityMetadataSource.loadResourceDefine();
    userService.updatePermission();
  }
}

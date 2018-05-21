package com.example.demo.model.module.security.service;

import com.example.demo.model.module.security.model.Permission;
import com.example.demo.model.module.security.model.Role;
import com.example.demo.model.module.security.repository.RoleRepository;
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
  private RoleRepository roleRepository;
  @Autowired
  private PermissionService permissionService;

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public void deleteById(Integer id) {
    Role role = findById(id);
    roleRepository.delete(role);
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
  }
}

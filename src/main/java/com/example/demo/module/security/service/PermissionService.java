package com.example.demo.module.security.service;

import com.example.demo.module.security.model.Permission;
import com.example.demo.module.security.repository.PermissionRepository;
import com.example.demo.module.user.model.User;
import com.example.demo.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tomoya at 12/26/17
 */
@Service
@Transactional
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private UserService userService;

	public Permission findByName(String name) {
		return permissionRepository.findByName(name);
	}

	/**
	 * 根据pid查询权限
	 *
	 * @param pid
	 * @return
	 */
	public List<Permission> findByPid(int pid) {
		return permissionRepository.findByPid(pid);
	}

	/**
	 * 查询权限列表
	 *
	 * @return
	 */
	public List findAll(boolean child) {
		if (child) {
			return permissionRepository.findByPidGreaterThan(0);
		} else {
			List list = new ArrayList();
			List<Permission> permissions = this.findByPid(0);
			for (Permission permission : permissions) {
				Map map = new HashMap();
				map.put("permission", permission);
				map.put("childPermissions", this.findByPid(permission.getId()));
				list.add(map);
			}
			return list;
		}
	}

	/**
	 * 根据用户的id查询用户的所有权限
	 *
	 * @param adminUserId
	 * @return
	 */
	public List<Permission> findByAdminUserId(int adminUserId) {
		User user = userService.findById(adminUserId);
		List<Permission> permissions = new ArrayList<>();
		if (user.getRoles().size() > 0) {
			user.getRoles()
					.stream()
					.filter(role -> role.getPermissions().size() > 0)
					.forEach(role -> {
								permissions.addAll(
										role.getPermissions()
												.stream()
												.filter(permission -> permission.getPid() > 0)
												.collect(Collectors.toList())
								);
							}
					);
		}
		return permissions;
	}

	public void save(Permission permission) {
		permissionRepository.save(permission);
	}

	/**
	 * 删除权限
	 * 判断权限的pid是不是0，是的话，就删除其下所有的权限
	 *
	 * @param id
	 */
	public void deleteById(Integer id) {
		Permission permission = findById(id);
		if (permission.getPid() == 0) {
			permissionRepository.deleteByPid(permission.getId());
		}
		permissionRepository.delete(permission);
	}

	public Permission findById(int id) {
		return permissionRepository.findById(id);
	}

}

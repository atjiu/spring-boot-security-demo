package com.example.demo.module.user.service;

import com.example.demo.module.security.core.MyUserDetailService;
import com.example.demo.module.security.model.Role;
import com.example.demo.module.user.model.User;
import com.example.demo.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by tomoya at 12/26/17
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MyUserDetailService myUserDetailService;

	public User findById(int id) {
		return userRepository.findById(id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User save(User user, Integer roleId) {
		Role role = new Role();
		role.setId(roleId);
		user.setRole(role);
		user = userRepository.save(user);
		//更新用户的权限
		myUserDetailService.loadUserByUsername(user.getUsername());
		return user;
	}

	public Page<User> page(Integer pageNo, Integer pageSize) {
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "inTime"));
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
		return userRepository.findAll(pageable);
	}

	/**
	 * 禁用用户
	 *
	 * @param id
	 */
	public void block(Integer id) {
		User user = findById(id);
		user.setBlock(true);
		userRepository.save(user);
	}

	/**
	 * 用户解禁
	 *
	 * @param id
	 */
	public void unBlock(Integer id) {
		User user = findById(id);
		user.setBlock(false);
		userRepository.save(user);
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	public void update(Integer id, String password, Integer roleId) {
		User user = findById(id);
		if(!StringUtils.isEmpty(password)) {
			user.setPassword(new BCryptPasswordEncoder().encode(password));
		}
		Role role = new Role();
		role.setId(roleId);
		user.setRole(role);
		userRepository.save(user);
	}

	public void updatePermission() {
		List<User> users = userRepository.findAll();
		users.forEach(user -> myUserDetailService.loadUserByUsername(user.getUsername()));
	}

}

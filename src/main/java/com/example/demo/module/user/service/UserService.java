package com.example.demo.module.user.service;

import com.example.demo.module.user.model.User;
import com.example.demo.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tomoya at 12/26/17
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * search user by score desc
	 *
	 * @param p
	 * @param size
	 * @return
	 */
	public Page<User> findByScore(int p, int size) {
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "score"));
		Pageable pageable = new PageRequest(p - 1, size, sort);
		return userRepository.findByBlock(false, pageable);
	}

	public User findById(int id) {
		return userRepository.findById(id);
	}

	/**
	 * 根据用户名判断是否存在
	 *
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	/**
	 * 分页查询用户列表
	 *
	 * @param p
	 * @param size
	 * @return
	 */
	public Page<User> pageUser(int p, int size) {
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "inTime"));
		Pageable pageable = new PageRequest(p - 1, size, sort);
		return userRepository.findAll(pageable);
	}

	/**
	 * 禁用用户
	 *
	 * @param id
	 */
	public void blockUser(Integer id) {
		User user = findById(id);
		user.setBlock(true);
		save(user);
	}

	/**
	 * 用户解禁
	 *
	 * @param id
	 */
	public void unBlockUser(Integer id) {
		User user = findById(id);
		user.setBlock(false);
		save(user);
	}

}

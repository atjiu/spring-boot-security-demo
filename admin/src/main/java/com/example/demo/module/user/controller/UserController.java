package com.example.demo.module.user.controller;

import com.example.demo.config.SiteConfig;
import com.example.demo.module.security.service.RoleService;
import com.example.demo.module.user.model.User;
import com.example.demo.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by tomoya at 12/27/17
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private RoleService roleService;

	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") Integer pageNo, Model model) {
		model.addAttribute("page", userService.page(pageNo, siteConfig.getPageSize()));
		return "admin/user/list";
	}

	@GetMapping("/block")
	public String block(Integer id) {
		userService.block(id);
		return "redirect:/admin/user/list";
	}

	@GetMapping("/unblock")
	public String unblock(Integer id) {
		userService.unBlock(id);
		return "redirect:/admin/user/list";
	}

	@GetMapping("/delete")
	public String delete(Integer id) {
		userService.deleteById(id);
		return "redirect:/admin/user/list";
	}

	@GetMapping("/edit")
	public String edit(Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("roles", roleService.findAll());
		return "admin/user/edit";
	}

	@PostMapping("/edit")
	public String update(User user, Integer roleId) {
		userService.update(user.getId(), user.getPassword(), roleId);
		return "redirect:/admin/user/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("roles", roleService.findAll());
		return "admin/user/add";
	}

	@PostMapping("/add")
	public String save(User user, Integer roleId) {
		user.setInTime(new Date());
		user.setBlock(false);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.save(user, roleId);
		return "redirect:/admin/user/list";
	}

}

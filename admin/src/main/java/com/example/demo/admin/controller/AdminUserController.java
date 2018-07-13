package com.example.demo.admin.controller;

import com.example.demo.model.config.SiteConfig;
import com.example.demo.model.module.security.service.RoleService;
import com.example.demo.model.module.admin_user.model.AdminUser;
import com.example.demo.model.module.admin_user.service.AdminUserService;
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
@RequestMapping("/admin/admin_user")
public class AdminUserController {

  @Autowired
  private AdminUserService adminUserService;
  @Autowired
  private SiteConfig siteConfig;
  @Autowired
  private RoleService roleService;

  @GetMapping("/list")
  public String list(@RequestParam(defaultValue = "1") Integer pageNo, Model model) {
    model.addAttribute("page", adminUserService.page(pageNo, siteConfig.getPageSize()));
    return "admin/admin_user/list";
  }

  @GetMapping("/block")
  public String block(Integer id) {
    adminUserService.block(id);
    return "redirect:/admin/admin_user/list";
  }

  @GetMapping("/unblock")
  public String unblock(Integer id) {
    adminUserService.unBlock(id);
    return "redirect:/admin/admin_user/list";
  }

  @GetMapping("/delete")
  public String delete(Integer id) {
    adminUserService.deleteById(id);
    return "redirect:/admin/admin_user/list";
  }

  @GetMapping("/edit")
  public String edit(Integer id, Model model) {
    model.addAttribute("adminUser", adminUserService.findById(id));
    model.addAttribute("roles", roleService.findAll());
    return "admin/admin_user/edit";
  }

  @PostMapping("/edit")
  public String update(AdminUser adminUser, Integer roleId) {
    adminUserService.update(adminUser.getId(), adminUser.getPassword(), roleId);
    return "redirect:/admin/admin_user/list";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("roles", roleService.findAll());
    return "admin/admin_user/add";
  }

  @PostMapping("/add")
  public String save(AdminUser adminUser, Integer roleId) {
    adminUser.setInTime(new Date());
    adminUser.setBlock(false);
    adminUser.setPassword(new BCryptPasswordEncoder().encode(adminUser.getPassword()));
    adminUserService.save(adminUser, roleId);
    return "redirect:/admin/admin_user/list";
  }

}

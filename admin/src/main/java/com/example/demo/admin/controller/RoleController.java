package com.example.demo.admin.controller;

import com.example.demo.model.module.security.model.Role;
import com.example.demo.model.module.security.service.PermissionService;
import com.example.demo.model.module.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tomoya at 12/27/17
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private PermissionService permissionService;

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("roles", roleService.findAll());
    return "admin/role/list";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("list", permissionService.findAll(false));
    return "admin/role/add";
  }

  @PostMapping("/add")
  public String save(Role role, Integer[] permissionIds) {
    roleService.save(role, permissionIds);
    return "redirect:/admin/role/list";
  }

  @GetMapping("/edit")
  public String edit(Integer id, Model model) {
    model.addAttribute("list", permissionService.findAll(false));
    model.addAttribute("role", roleService.findById(id));
    return "admin/role/edit";
  }

  @PostMapping("/edit")
  public String update(Role role, Integer[] permissionIds) {
    roleService.save(role, permissionIds);
    return "redirect:/admin/role/list";
  }

  @GetMapping("/delete")
  public String delete(Integer id) {
    roleService.deleteById(id);
    return "redirect:/admin/role/list";
  }

}

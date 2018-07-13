package com.example.demo.admin.controller;

import com.example.demo.model.module.security.model.Permission;
import com.example.demo.model.module.security.service.PermissionService;
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
@RequestMapping("/admin/permission")
public class PermissionController {

  @Autowired
  private PermissionService permissionService;

  @GetMapping("/list")
  public String list(Integer pid, Model model) {
    if (pid == null || pid == 0) {
      model.addAttribute("childPermissions", permissionService.findAll(true));
      model.addAttribute("permissions", permissionService.findByPid(0));
    } else {
      model.addAttribute("childPermissions", permissionService.findByPid(pid));
      model.addAttribute("permissions", permissionService.findByPid(0));
    }
    model.addAttribute("pid", pid);
    return "admin/permission/list";
  }

  @GetMapping("/add")
  public String add(Model model, Integer pid) {
    model.addAttribute("permissions", permissionService.findByPid(0));
    model.addAttribute("pid", pid);
    return "admin/permission/add";
  }

  @PostMapping("/add")
  public String save(Permission permission) {
    permissionService.save(permission);
    if (permission.getPid() > 0) {
      return "redirect:/admin/permission/list?pid=" + permission.getPid();
    } else {
      return "redirect:/admin/permission/list";
    }
  }

  @GetMapping("/edit")
  public String edit(Integer id, Model model) {
    model.addAttribute("permissions", permissionService.findByPid(0));
    model.addAttribute("permission", permissionService.findById(id));
    return "admin/permission/edit";
  }

  @PostMapping("/edit")
  public String update(Permission permission) {
    permissionService.save(permission);
    return "redirect:/admin/permission/list?pid=" + permission.getPid();
  }

  @GetMapping("/delete")
  public String delete(Integer id) {
    permissionService.deleteById(id);
    return "redirect:/admin/permission/list";
  }

}

package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tomoya at 12/26/17
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController {

  @GetMapping("/dashboard")
  public String dashboard() {
    return "admin/dashboard";
  }

  @GetMapping("/test")
  public String test() {
    Assert.isTrue(false, "test error page");
    return "admin/test";
  }
}

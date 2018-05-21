package com.example.demo.api.controller;

import com.example.demo.model.exception.ApiAssert;
import com.example.demo.model.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tomoya at 5/21/18
 */
@RestController
@RequestMapping("/api")
public class IndexApiController extends BaseApiController {

  @GetMapping("/")
  public Result index(String hello) {
    ApiAssert.notNull(hello, "hello is not null");
    return Result.success();
  }
}

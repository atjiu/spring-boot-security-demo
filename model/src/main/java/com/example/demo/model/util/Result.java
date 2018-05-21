package com.example.demo.model.util;

/**
 * Created by tomoya at 12/27/17
 */
public class Result {

  private int code;

  private String description;

  private Object detail;

  public Result error(String description) {
    Result result = new Result();
    result.setCode(201);
    result.setDescription(description);
    result.setDetail(null);
    return result;
  }

  public static Result success() {
    return success(null);
  }

  public static Result success(Object detail) {
    Result result = new Result();
    result.setCode(200);
    result.setDescription(null);
    result.setDetail(detail);
    return result;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Object getDetail() {
    return detail;
  }

  public void setDetail(Object detail) {
    this.detail = detail;
  }
}

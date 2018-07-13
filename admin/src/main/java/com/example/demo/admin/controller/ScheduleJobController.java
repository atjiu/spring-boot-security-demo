package com.example.demo.admin.controller;

import com.example.demo.model.module.schedule.pojo.ScheduleJob;
import com.example.demo.model.module.schedule.service.ScheduleJobService;
import com.example.demo.model.util.Result;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务 controller
 */
@Controller
@RequestMapping("/admin/schedule")
public class ScheduleJobController {

  @Autowired
  private ScheduleJobService scheduleJobService;

  /**
   * 获取定时任务 json
   */
  @GetMapping(value = "/list")
  public String getAllJobs(Model model) {
    List<ScheduleJob> scheduleJobs = scheduleJobService.getAllScheduleJob();
    model.addAttribute("scheduleJobs", scheduleJobs);
    return "admin/schedule/list";
  }

  /**
   * 添加
   */
  @PostMapping(value = "/add")
  public String create(String name, String group, String cronExpression, String description, String className,
                       @RequestParam(required = false, defaultValue = "1") String status) {
    ScheduleJob scheduleJob = new ScheduleJob();
    scheduleJob.setName(name);
    scheduleJob.setGroup(group);
    scheduleJob.setCronExpression(cronExpression);
    scheduleJob.setClassName(className);
    scheduleJob.setDescription(description);
    scheduleJob.setStatus(status);
    scheduleJobService.add(scheduleJob);
    return "redirect:list";
  }

  /**
   * 暂停任务
   */
  @PostMapping(value = "/stop")
  @ResponseBody
  public Result stop(String name, String group) {
    scheduleJobService.stopJob(name, group);
    return Result.success();
  }

  /**
   * 删除任务
   */
  @PostMapping(value = "/delete")
  @ResponseBody
  public Result delete(String name, String group) {
    scheduleJobService.delJob(name, group);
    return Result.success();
  }

  /**
   * 立即运行一次
   */
  @PostMapping(value = "/startNow")
  @ResponseBody
  public Result stratNow(String name, String group) {
    scheduleJobService.startNowJob(name, group);
    return Result.success();
  }

  /**
   * 恢复
   */
  @PostMapping(value = "/resume")
  @ResponseBody
  public Result resume(String name, String group) {
    scheduleJobService.restartJob(name, group);
    return Result.success();
  }


  /**
   * 修改表达式
   */
  @PostMapping(value = "/edit")
  @ResponseBody
  public Result edit(String name, String group, String cronExpression, String description) {
    //验证cron表达式
    if (CronExpression.isValidExpression(cronExpression)) {
      scheduleJobService.modifyTrigger(name, group, cronExpression, description);
      return Result.success();
    } else {
      return Result.error("表达式错误");
    }
  }

//    /**
//     * 获取所有trigger
//     */
//    public void getTriggers(HttpServletRequest request) {
//        List<ScheduleJob> scheduleJobs = scheduleJobService.getTriggersInfo();
//        System.out.println(scheduleJobs.size());
//        request.setAttribute("triggers", scheduleJobs);
//    }

//    /**
//     * cron表达式生成页
//     */
//    @RequestMapping("quartzCron")
//    public String quartzCronCreate(){
//        return "system/quartzCron";
//    }
}

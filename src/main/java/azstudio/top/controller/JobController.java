package azstudio.top.controller;

import azstudio.top.Service.TaskServiceImpl;
import azstudio.top.config.BackJSON;
import azstudio.top.entity.Task;
import azstudio.top.entity.group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author:
 * Data:2019-05-02 15:46
 * Description:<>
 */
@RestController
@RequestMapping("/task")
public class JobController {

    @Autowired
    TaskServiceImpl taskService;
    //first Page  需要获取个人本周的任务，各种数
    // publish task
    @PutMapping("/captain")
    public BackJSON publishTask(@RequestBody Task  task){
        System.out.println(task);

        return taskService.publishTask(task);
    }
    //Ongoing tasks
    @GetMapping("/captain/Ongoing/{groupCreater}")
    public BackJSON CaptainGetTask(@PathVariable  int groupCreater){
        return taskService.getPublishedTask(groupCreater);
    }
    //get all tasks by group creater
    @GetMapping("/captain/AllTasks/{groupCreater}")
    public BackJSON getAllTasks(@PathVariable  int groupCreater){
        return taskService.getAllTasks(groupCreater);
    }
    //fast page show Weekly task
    @GetMapping("/firstPage/WeeklyTask/{wxId}")
    public BackJSON getWeeklyTask(@PathVariable String wxId ){
        return  taskService.getWeeklyTask(wxId);
    }
    @GetMapping("/firstPage/headInfo/{wxId}")
    public BackJSON getFirstPageHeadInfo(@PathVariable String wxId){
        return taskService.getFirstPageHeadInfo(wxId);
    }
}

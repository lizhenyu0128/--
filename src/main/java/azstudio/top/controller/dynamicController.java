package azstudio.top.controller;

import azstudio.top.Service.DynamicService;
import azstudio.top.Service.DynamicServiceImpl;
import azstudio.top.config.BackJSON;
import azstudio.top.entity.dynamic;
import azstudio.top.entity.dynamicReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author:
 * Data:2019-05-04 22:57
 * Description:<>
 */
@RestController
@RequestMapping("/dynamic")
public class dynamicController {

    @Autowired
    DynamicServiceImpl dynamicService;

    //1 publish dynamic
    @PutMapping("/dynamic")
    public BackJSON publishDynamic(@RequestBody dynamic dynamic) {
        return dynamicService.publishDynamic(dynamic);
    }

    //3 get group friend's dynamic content 点赞数量 评论数量   评论列表
    @GetMapping("/dynamic/{id}")
    public BackJSON getDyanmic(@PathVariable int id) {

        return dynamicService.getDynamic(id);
    }

    //4 点赞
    @PutMapping("/like")
    public BackJSON addLikeNum(@RequestBody HashMap<String, Integer> dynamic) {
        return dynamicService.addLikeNum(dynamic);
    }

    //5 评论
    @PostMapping("/reply")
    public BackJSON addreply(@RequestBody dynamicReply dynamicReply) {

        return dynamicService.addreply(dynamicReply);
    }
    //2 get group friend's dynamic list   点赞数量 评论数量 wx昵称 用户昵称 发表时间距离当前的时间戳
    @GetMapping("/dynamics/{begin}/{size}")
    public BackJSON getAllDynamic(@PathVariable int begin, @PathVariable int size){
        return dynamicService.getAllDynamics(begin,size);
    }
}

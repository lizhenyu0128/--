package azstudio.top.controller;

import azstudio.top.Service.UserServiceImpl;
import azstudio.top.config.BackJSON;
import azstudio.top.entity.group;
import azstudio.top.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:
 * Data:2019-04-20 20:21
 * Description:<>
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserServiceImpl userService;

    //获取某个用户信息
    @GetMapping("/userInfo")
    public BackJSON getUserInfoByWxCode(@RequestBody HashMap<String, String> par) {
        return userService.getUserByWxCode(par.get("wxId"));
    }

    //修改个人信息
    @PutMapping("/userInfo")
    public BackJSON updataInfoByWxCode(@RequestBody user user) {
        return userService.updataUserInfoByWxCode(user);
    }

    //add user
    @PostMapping("/userInfo")
    public BackJSON addUser(@RequestBody user user) {
        System.out.println(user);
        return userService.addUserInfo(user);
    }

    //new group
    @PostMapping("/group")
    public BackJSON newGroup(@RequestBody HashMap<String, String> par) {
        group group = new group();
        group.setGroupName(par.get("groupName"));
        group.setGroupSubject(par.get("groupSubject"));
        group.setGroupIntroduction(par.get("groupIntroduction"));
        System.out.println(group);
        return userService.newGroup(group, par.get("wxId"));
    }

    //join group
    @PutMapping("/group")
    public BackJSON joinGroup(@RequestBody HashMap<String, Object> par) {
        System.out.println(par);
        return userService.joinGroup((int) par.get("groupId"), (String) par.get("wxId"));
    }

    // group list
    @GetMapping("/group")
    public BackJSON getGroupList(@RequestBody HashMap<String, String> par) {
        return userService.getGroupList(par.get("wxId"));
    }
    // delete group
    @DeleteMapping("/group/captain")
    public BackJSON deleteGroup(@RequestBody HashMap<String, Object> par){
        return userService.deleteGroup((String)par.get("wxId"),(int)par.get("groupId"));
    }
}

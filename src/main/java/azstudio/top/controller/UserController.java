package azstudio.top.controller;

import azstudio.top.Service.UserServiceImpl;
import azstudio.top.config.BackJSON;
import azstudio.top.entity.OpeEntity.CaptainToMember;
import azstudio.top.entity.group;
import azstudio.top.entity.user;
import org.apache.ibatis.annotations.Delete;
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

    //get user details
    @GetMapping("/userInfo")
    public BackJSON getUserInfoByWxCode(@RequestBody HashMap<String, String> par) {
        return userService.getUserByWxCode(par.get("wxId"));
    }

    //update user details
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

    /**
     * function of all user
     *
     * @return
     */

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

    /**
     * captain function
     *
     * @return
     */

    // delete group
    @DeleteMapping("/group/captain")
    public BackJSON deleteGroup(@RequestBody HashMap<String, Object> par) {
        return userService.deleteGroup((String) par.get("wxId"), (int) par.get("groupId"));
    }

    //  group details
    @GetMapping("/group/captain")
    public BackJSON getGroupDetails(@RequestBody group group) {
        return userService.getGroupDetails(group);
    }

    //  update group detailsF
    @PutMapping("/group/captain")
    public BackJSON updateGroupDetails(@RequestBody group group) {
        return userService.updateGroupDetails(group);
    }

    //  delete group member
    @DeleteMapping("/group/captainToMember")
    public BackJSON deleteGroupMember(@RequestBody CaptainToMember cm) {
        return userService.deleteGroupMember(cm);
    }
    //  get group QR code




}

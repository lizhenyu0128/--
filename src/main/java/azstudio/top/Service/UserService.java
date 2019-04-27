package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.entity.OpeEntity.CaptainToMember;
import azstudio.top.entity.group;
import azstudio.top.entity.user;

/**
 * Author:
 * Data:2019-04-22 19:32
 * Description:<>
 */


public interface UserService {


    //获取用户信息
    BackJSON getUserByWxCode(String wxcode);
    //修改用户信息
    BackJSON updataUserInfoByWxCode(user user);
    //添加用户信息
    BackJSON addUserInfo(user user);
    //new group
    BackJSON newGroup(group group,String wx_id);
    //join group
    BackJSON joinGroup(int group_id,String wx_id);
    //get group list
    BackJSON getGroupList(String wx_id);
    //delete group;
    BackJSON deleteGroup(String wxId,int groupId);
    //get group details
    BackJSON getGroupDetails(group group);
    //update group details
    BackJSON updateGroupDetails(group group);
    //delete group member;
    BackJSON deleteGroupMember(CaptainToMember cm);
}

package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.entity.OpeEntity.CaptainToMember;
import azstudio.top.entity.group;
import azstudio.top.entity.user;
import azstudio.top.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:
 * Data:2019-04-22 19:34
 * Description:<>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public BackJSON getUserByWxCode(String wx_id) {
        user res = userMapper.getUserInfoByWxCode(wx_id);
        System.out.println(res);
        if (res == null)
            return new BackJSON(200, "ok");
        return new BackJSON(200, "ok", res);
    }

    @Override
    public BackJSON updataUserInfoByWxCode(user user) {
        if (user.getWxId() == null)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "fail"));
        HashMap<String, Object> resmap = new HashMap<>();
        int res = userMapper.updataUserInfo(user);
        if (res < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "fail"));
        return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
    }

    @Override
    public BackJSON addUserInfo(user user) {
        try {
            int res = userMapper.addUser(user);
            if (res > 0)
                return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
        } catch (Exception e) {
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "fail"));
        }
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "fail"));
    }

    @Override
    public BackJSON newGroup(group group, String wx_id) {
        try {
            int userid = userMapper.getUserId(wx_id);
            if (userid < 1) return new BackJSON(200, "ok", BackJSON.resMsg(0, "fail"));
            System.out.println("====asdasd=");
            group.setGroupCreater(userid);

            userMapper.newGroup(group);
            System.out.println(group.getId());
            if (userMapper.joingroup(group.getId(), wx_id, "组长") > 0) {
                return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
            }
        } catch (Exception e) {
        }
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "fail"));
    }

    @Override
    public BackJSON joinGroup(int group_id, String wx_id) {
        if (userMapper.checkGroupMember(group_id, wx_id) > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "您已在组内"));
        else if (userMapper.joingroup(group_id, wx_id, "成员") > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "加入失败"));
    }

    @Override
    public BackJSON getGroupList(String wx_id) {
        Integer userId = null;
        userId = userMapper.getUserId(wx_id);
        if (userId == null)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "查询失败"));
        List<HashMap<String, Object>> res = userMapper.getUserGroup(wx_id);
        return new BackJSON(200, "ok", JSON.toJSON(res));
    }

    @Override
    public BackJSON deleteGroup(String wxId, int groupId) {
        Integer userId = null;
        userId = userMapper.getUserId(wxId);
        if (userId == null)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        if (userMapper.deleteGroup(userId, groupId) > 0) return new BackJSON(200, "ok", BackJSON.resMsg(0, "success"));
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON getGroupDetails(group group) {
        if (group.getId() < 1 || group.getGroupCreater() < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        group g = userMapper.getGroupDetails(group);
        Map<String,Object> res = (Map<String, Object>) JSON.toJSON(g);
        if(g!=null){
            List<Map<String, Object>> members = userMapper.getGroupMember(group.getId());
            System.out.println(members);
            System.out.println(res);
            res.put("members",members);
        }
        return new BackJSON(200, "ok", res);
    }

    @Override
    public BackJSON updateGroupDetails(group group) {
        if (group.getId() < 1 || group.getGroupCreater() < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        else if (userMapper.updataGroupDetails(group) > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "success"));
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON deleteGroupMember(CaptainToMember cm) {
        System.out.println(cm);
        if (cm.getGroupId() < 1 || cm.getWxId() == null || cm.getGroupCreater() < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));

        Integer g = userMapper.checkgroup(cm.getGroupId(), cm.getGroupCreater());
        System.out.println(g);
        if(g==null||g<1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        else if (g>0 && userMapper.deleteGroupMember(cm) >0)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "success"));

        return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }
}

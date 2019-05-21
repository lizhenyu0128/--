package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.config.DesUtil;
import azstudio.top.config.MD5;
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

import java.awt.image.RescaleOp;
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
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
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
        System.out.println(userId);
        System.out.println(groupId);
        System.out.println(wxId);
        if (userId == null)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        if (userMapper.deleteGroup(userId, groupId) > 0) return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON getGroupDetails(int id,int groupCreater) {
        if (id < 1 || groupCreater < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        group g = userMapper.getGroupDetails(id,groupCreater);
        Map<String, Object> res = (Map<String, Object>) JSON.toJSON(g);
        if (g != null) {
            List<Map<String, Object>> members = userMapper.getGroupMember(id);
            System.out.println(members);
            System.out.println(res);
            res.put("members", members);
        }
        return new BackJSON(200, "ok", res);
    }

    @Override
    public BackJSON updateGroupDetails(group group) {
        if (group.getId() < 1 || group.getGroupCreater() < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        else if (userMapper.updataGroupDetails(group) > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
        return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON deleteGroupMember(CaptainToMember cm) {
        System.out.println(cm);
        if (cm.getGroupId() < 1 || cm.getWxId() == null || cm.getGroupCreater() < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));

        Integer g = userMapper.checkgroup(cm.getGroupId(), cm.getGroupCreater());
        System.out.println(g);
        if (g == null || g < 1)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        else if (g > 0 && userMapper.deleteGroupMember(cm) > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));

        return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON getQRcode(int id,int groupCreater) {
        group group = new group();
        group.setId(id);
        group.setGroupCreater(groupCreater);
        group groupDetails = userMapper.getGroupDetails(id,groupCreater);
        if (groupDetails == null)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        //Encrypt QR code
        Map<String, Integer> enc = new HashMap<String, Integer>();
        enc.put("id", id);
        enc.put("groupCreater", groupCreater);
        try {

            Object encRes = DesUtil.encrypt(enc.toString());
            return new BackJSON(200, "ok", encRes);

        } catch (Exception e) {
            e.printStackTrace();
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        }


    }

    @Override
    public BackJSON decodeJoinQR(String wxId,String Decode) {
        if (wxId == null || Decode== null)
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        String decode= DesUtil.decrypt(Decode);
        System.out.println(decode);
        //result of decryption
        String id = decode.substring(4,decode.indexOf(","));
        int idInt = Integer.parseInt(id);
        String groupCreater =decode.substring(4+ id.length()+15,decode.indexOf("}"));
        System.out.println(wxId);
        try {
            if(userMapper.checkGroupMember(idInt,wxId)<1&&userMapper.joingroup(idInt,wxId,"组员")>0)
                return new BackJSON(200, "ok", BackJSON.resMsg(1, "加入成功"));
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        }catch (Exception e){
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        }
    }
}

package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.entity.dynamic;
import azstudio.top.entity.dynamicReply;
import azstudio.top.mapper.DynamicMapper;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:
 * Data:2019-05-04 23:19
 * Description:<>
 */
@Service
@Transactional
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    DynamicMapper dynamicMapper;

    @Override
    public BackJSON publishDynamic(dynamic dynamic) {
        System.out.println(dynamic);
        if (dynamicMapper.addDynamic(dynamic) > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
        else return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON getDynamic(int id) {
        System.out.println(id);
        List<HashMap<String, Object>> allReply = dynamicMapper.getAllDynamicReply(id);
        HashMap<String, Integer> allLikeAndReplyCount = dynamicMapper.getReplyAndLikeCount(id);
        HashMap<String, Object> getDynamicCountent = dynamicMapper.getDynamicCountent(id);
        JSONObject resobj = new JSONObject();
        resobj.put("count", allLikeAndReplyCount);
        resobj.put("reply", allReply);
        resobj.put("DynamicCountent", getDynamicCountent);
        return new BackJSON(200, "ok", resobj);


    }

    @Override
    public BackJSON addLikeNum(HashMap<String, Integer> dynamic) {
        if (dynamicMapper.adddynamiclike(dynamic.get("dynamic_id"), dynamic.get("user_id")) > 0 && dynamicMapper.addDynamicLikeCount(dynamic.get("dynamic_id")) > 0)
            return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
        else return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON addreply(dynamicReply dynamicReply) {
       if(dynamicMapper.addDynamicReply(dynamicReply)>0&&dynamicMapper.addDynamicLikeCount(dynamicReply.getDyId())>0)
           return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
       else return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
    }

    @Override
    public BackJSON getAllDynamics(int begin, int size) {
        PageHelper.startPage(begin, size);
        List<Map<String, Object>> touser = dynamicMapper.getAllDynamics();
        PageInfo<Map<String, Object>> p = new PageInfo<>(touser);
        return new BackJSON(200, "ok", p);
    }


}

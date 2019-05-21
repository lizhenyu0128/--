package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.entity.dynamic;
import azstudio.top.entity.dynamicReply;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

/**
 * Author:
 * Data:2019-05-04 23:18
 * Description:<>
 */
public interface DynamicService {

    //1 publish dynamic
    BackJSON publishDynamic(dynamic dynamic);
    // get dynamic
    BackJSON getDynamic(int id);

    BackJSON addLikeNum(HashMap<String,Integer> dynamic);

    BackJSON addreply(dynamicReply dynamicReply);

    BackJSON getAllDynamics(int begin, int size);
}

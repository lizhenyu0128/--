package azstudio.top.mapper;

import azstudio.top.entity.dynamic;
import azstudio.top.entity.dynamicReply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:
 * Data:2019-05-12 10:55
 * Description:<>
 */


@Mapper
public interface DynamicMapper {

    @Insert("INSERT INTO t_dynamic(create_user_id,dy_content,reply_num,like_num,create_time,use_status) VALUES(#{createUserId},#{dyContent},0,0,UNIX_TIMESTAMP(now()),1)")
    int addDynamic(dynamic dynamic);

    @Select("SELECT r.*,u.user_name as replay_to_name,u2.user_name as create_user_name,u2.user_head as create_user_head FROM t_dynamic_reply r INNER JOIN t_user u ON r.reply_to_id = u.id INNER JOIN t_user u2 on u2.id = r.create_user_id WHERE r.dy_id = #{dy_id}")
    List<HashMap<String, Object>> getAllDynamicReply(int dy_id);

    @Select("select t1.dy_like,t2.dy_reply from \n" +
            "  (select count(*) dy_like from t_dynamic_like WHERE dynamic_id=#{dy_id}) t1,\n" +
            "  (select count(*) dy_reply from t_dynamic_reply WHERE dy_id = #{dy_id}) t2")
    HashMap<String, Integer> getReplyAndLikeCount(int dy_id);

    @Select("SELECT d.*,u.user_name,u.user_head from t_dynamic d INNER JOIN t_user u on u.id = d.create_user_id WHERE d.id=#{dy_id}")
    HashMap<String, Object> getDynamicCountent(int dy_id);

    @Insert("INSERT INTO t_dynamic_like(dynamic_id,user_id) VALUES (#{dynamic_id},#{user_id})")
    int adddynamiclike(int dynamic_id, int user_id);

    @Update("UPDATE t_dynamic SET like_num=like_num+1 WHERE id=#{id}")
    int addDynamicLikeCount(int id);


    @Insert("INSERT INTO t_dynamic_reply(create_user_id,dy_id,reply_to_id,reply_content,create_time) VALUES(#{createUserId},#{dyId},#{replyToId},#{replyContent},UNIX_TIMESTAMP(now()))")
    int addDynamicReply(dynamicReply dynamicReply);

    @Update("UPDATE t_dynamic SET reply_num=reply_num+1 WHERE id=#{id}")
    int addDynamicReplyCount(int id);

    @Select("SELECT * from t_dynamic  ")
    List<Map<String, Object>> getAllDynamics();
}

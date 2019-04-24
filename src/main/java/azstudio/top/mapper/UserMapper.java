package azstudio.top.mapper;

import azstudio.top.entity.group;
import azstudio.top.entity.user;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:
 * Data:2019-04-22 16:57
 * Description:<>
 */

@Mapper
public interface UserMapper {

    @Select("select user_name,user_phone,wx_id,user_head,create_time from t_user where wx_id = #{wx_id} and use_status=1")
    user getUserInfoByWxCode(String wx_id);

    @UpdateProvider(type = UserMapperProvider.class, method = "updataUserInfo")
    int updataUserInfo(user user);

    @Insert("INSERT INTO t_user (user_name,user_phone,user_head,wx_id,create_time,use_status) VALUES(#{userName},#{userPhone},#{userHead},#{wxId},UNIX_TIMESTAMP(),1)")
    int addUser(user user);

    @Insert("INSERT INTO t_group (group_name,group_creater,group_subject,group_introduction,create_time,use_status) VALUE(#{groupName},#{groupCreater},#{groupSubject},#{groupIntroduction},UNIX_TIMESTAMP(),1)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int newGroup(group group);

    @Select("select id from t_user where wx_id=#{wxId}")
    Integer getUserId(String wxId);

    @Select("SELECT COUNT(*) FROM t_group_memeber WHERE group_id=#{id} AND wx_id=#{wxId}")
    int checkGroupMember(int id, String wxId);

    @Insert("INSERT INTO t_group_memeber VALUES(#{id},#{wx_id},#{member_type})")
    int joingroup(int id,String wx_id,String member_type);

    //查询某个用户的组降序
    @Select("SELECT t1.*,t2.* FROM t_group t1 INNER JOIN t_group_memeber t2 ON t1.id = t2.group_id  WHERE t2.wx_id=#{wx_id} AND t1.use_status=1 ORDER BY t2.member_type DESC")
    List<HashMap<String,Object>> getUserGroup(String wx_id);

    //delete group by captain
    @Update("UPDATE t_group SET use_status=0 WHERE id = #{groupId} and group_creater=#{userId}")
    int deleteGroup(int userId,int groupId);
}
package azstudio.top.mapper;

import azstudio.top.entity.group;
import azstudio.top.entity.user;
import org.apache.ibatis.jdbc.SQL;

/**
 * Author:
 * Data:2019-04-23 20:29
 * Description:<>
 */
public class UserMapperProvider {
    public String updataUserInfo(user user) {
        return new SQL() {
            {
                UPDATE("t_user");
                if (user.getUserPhone() != null) {
                    SET("user_phone=#{userPhone}");
                }
                if (user.getUserName() != null) {
                    SET("user_name=#{userName}");
                }
                SET("wx_id=#{wxId}");
                WHERE("wx_id=#{wxId}");

            }
        }.toString();

    }

    public String updateGroupDetails(group group) {
        return new SQL() {
            {
                UPDATE("t_group");
                if(group.getGroupName()!=null){
                    SET("group_name=#{groupName}");
                }
                if(group.getGroupIntroduction()!=null){
                    SET("group_introduction=#{groupIntroduction}");
                }
                SET("id = #{id}");
                WHERE("id=#{id} and group_creater=#{groupCreater}");
            }

        }.toString();
    }
}

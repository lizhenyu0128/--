package azstudio.top.mapper;

import azstudio.top.entity.user;
import org.apache.ibatis.jdbc.SQL;

/**
 * Author:
 * Data:2019-04-23 20:29
 * Description:<>
 */
public class UserMapperProvider {
    public  String updataUserInfo(user user){
        return new SQL(){
            {
                UPDATE("t_user");
                if(user.getUserPhone()!=null){
                    SET("user_phone=#{userPhone}");
                }
                if(user.getUserName()!=null){
                    SET("user_name=#{userName}");
                }
                SET("wx_id=#{wxId}");
                WHERE("wx_id=#{wxId}");

            }
        }.toString();

    }
}

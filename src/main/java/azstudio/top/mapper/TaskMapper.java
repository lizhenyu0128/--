package azstudio.top.mapper;

import azstudio.top.entity.Task;
import azstudio.top.entity.dynamicReply;
import azstudio.top.entity.group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

/**
 * Author:
 * Data:2019-05-02 16:11
 * Description:<>
 */
@Mapper
public interface TaskMapper {

    //publish task
    @Insert("INSERT INTO t_task(user_id,group_id,task_name,task_content,start_time,end_time,create_time,use_status) VALUES(#{userId},#{groupId},#{taskName},#{taskContent},#{startTime},#{endTime},UNIX_TIMESTAMP(now()),#{useStatus})")
    int addTask(Task task);
    //get  Ongoing task
    @Select("SELECT * FROM t_task WHERE user_id=#{groupCreater} AND (end_time-start_time)<0 ORDER BY create_time DESC")
    List<Task> getOngoingTask(int groupCreater);

    //get all task by group creater
    @Select("SELECT * FROM t_task WHERE user_id=#{groupCreater} ORDER BY create_time  DESC")
    List<Task> getAllTasksByGroupCreater(int groupCreater);

    //get weekly tasks
    @Select("SELECT t.*,IFNULL(w.is_read,0) as is_read FROM t_group_memeber m INNER JOIN t_task t ON m.group_id =t.group_id LEFT JOIN t_watche_task w on w.task_id = t.id WHERE m.wx_id=#{wxId} AND m.member_type='组员' ORDER BY t.start_time DESC")
    List<HashMap<String,Object>> getWeeklyTasks(String wxId);

    @Select("SELECT count(*) FROM t_group_memeber m INNER JOIN t_task t ON m.group_id =t.group_id LEFT JOIN t_watche_task w on w.task_id = t.id WHERE m.wx_id=#{wxId} AND w.is_read !=1")
    int  getNotReadCount(String wxId);

}

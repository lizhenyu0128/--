package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.entity.Task;
import azstudio.top.entity.group;

import java.util.HashMap;

/**
 * Author:
 * Data:2019-05-02 16:02
 * Description:<>
 */

public interface TaskService {

    //publish task
    public BackJSON publishTask(Task task);

    //published  task
    public BackJSON getPublishedTask(group group);

    //get all tasks by group creater;
    BackJSON getAllTasks(group group);

    BackJSON getWeeklyTask(HashMap<String,String> wxId );
}

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
    public BackJSON getPublishedTask(int groupCreater);

    //get all tasks by group creater;
    BackJSON getAllTasks(int groupCreater);

    BackJSON getWeeklyTask(String wxId );

    //get first page head info
    BackJSON getFirstPageHeadInfo(String wxId);
}

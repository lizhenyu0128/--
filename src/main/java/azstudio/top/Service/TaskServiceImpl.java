package azstudio.top.Service;

import azstudio.top.config.BackJSON;
import azstudio.top.entity.Task;
import azstudio.top.entity.group;
import azstudio.top.mapper.TaskMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author:
 * Data:2019-05-02 16:03
 * Description:<>
 */

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public BackJSON publishTask(Task task) {
        System.out.println(task);
        if (task.getStartTime() >= task.getEndTime())
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "开始时间大于结束时间"));
        try {
            if (taskMapper.addTask(task) > 0)
                return new BackJSON(200, "ok", BackJSON.resMsg(1, "success"));
            else return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));
        } catch (Exception ex) {
            return new BackJSON(200, "ok", BackJSON.resMsg(0, "false"));

        }
    }

    @Override
    public BackJSON getPublishedTask(int groupCreater) {
        System.out.println( groupCreater);
        List<Task> reslist = taskMapper.getOngoingTask( groupCreater);
        System.out.println(reslist);
        String res = JSON.toJSONString(reslist);
        return new BackJSON(200, "ok", res);
    }

    @Override
    public BackJSON getAllTasks(int groupCreater) {
        System.out.println(groupCreater);
        List<Task> reslist = taskMapper.getAllTasksByGroupCreater(groupCreater);
        String res = JSON.toJSONString(reslist);
        return new BackJSON(200, "ok", res);
    }

    @Override
    public BackJSON getWeeklyTask(String wxId) {
        Date date;
        long mongdayTime = getThisWeekMonday(new Date());
        HashMap<String, List<HashMap<String, Object>>> weeklyTasks = new HashMap<>();
        weeklyTasks.put("1", new ArrayList<>());
        weeklyTasks.put("2", new ArrayList<>());
        weeklyTasks.put("3", new ArrayList<>());
        weeklyTasks.put("4", new ArrayList<>());
        weeklyTasks.put("5", new ArrayList<>());
        weeklyTasks.put("6", new ArrayList<>());
        weeklyTasks.put("7", new ArrayList<>());
        List<HashMap<String, Object>> reslist = taskMapper.getWeeklyTasks(wxId);
        for (HashMap<String, Object> entity : reslist) {
            Integer lt = (Integer) entity.get("start_time");
            //不是本周就走
            if (lt - mongdayTime < 0)
                continue;
            date = new Date(lt.longValue());
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            String currSun = dateFm.format(date);
            if (currSun.equals("星期日"))
                weeklyTasks.get("7").add(entity);
            else if (currSun.equals("星期六"))
                weeklyTasks.get("6").add(entity);
            else if (currSun.equals("星期五"))
                weeklyTasks.get("5").add(entity);
            else if (currSun.equals("星期四"))
                weeklyTasks.get("4").add(entity);
            else if (currSun.equals("星期三")) {
                System.out.println("我是星期3");
                weeklyTasks.get("3").add(entity);
            } else if (currSun.equals("星期二"))
                weeklyTasks.get("2").add(entity);
            else if (currSun.equals("星期一"))
                weeklyTasks.get("1").add(entity);
        }
        return new BackJSON(200, "ok", weeklyTasks);
    }

    @Override
    public BackJSON getFirstPageHeadInfo(String wxId) {
        int res1  = taskMapper.getNotReadCount(wxId);
        HashMap<String ,Integer> res= new HashMap<>();
        res.put("taskNotReadCount",res1);
        return new BackJSON(200, "ok", res);
    }

    private static long getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        long current = cal.getTime().getTime();
        long zero = (current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24)) / 1000;
        return zero;
    }
}

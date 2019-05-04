package azstudio.top.config;

import java.util.HashMap;

/**
 * Author:
 * Data:2019-04-22 19:41
 * Description:<>
 */
public class BackJSON {
    private int code;
    private String msg;
    private Object data;



    public BackJSON(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BackJSON(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BackJSON(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static HashMap<String, Object> resMsg(int result, String msg) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("result", result);
        res.put("msg", msg);
        return res;
    }

    public static HashMap<String, Object> resMsg(int code, String msg, Object sdata) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("result", code);
        res.put("msg", msg);
        res.put("data", sdata);
        return res;
    }

    @Override
    public String toString() {
        return "BackJSON{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
package azstudio.top.config;

import java.util.HashMap;

/**
 * Author:
 * Data:2019-04-22 19:41
 * Description:<>
 */
public class BackJSON {
    private int code;
    private Object data;
    private String msg;
    public BackJSON() {}
    public BackJSON(int code, String msg,Object data) {
        super();
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
    public String getMsg(int i, String asd) { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public static HashMap<String, Object> resMsg(int code, String msg){
        HashMap<String,Object> res = new HashMap<>();
        res.put("result",code);
        res.put("msg",msg);
        return res;
    }
}
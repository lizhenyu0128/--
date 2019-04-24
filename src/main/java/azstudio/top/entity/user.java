package azstudio.top.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Author:
 * Data:2019-04-22 19:35
 * Description:<>
 */
public class user {
    private int id;
    private String userName;
    private String userPhone;
    private String wxId;
    private String userHead;
    private int createTime;
    private int useStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public user() {
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", wxId='" + wxId + '\'' +
                ", userHead='" + userHead + '\'' +
                ", createTime=" + createTime +
                ", useStatus=" + useStatus +
                '}';
    }
}

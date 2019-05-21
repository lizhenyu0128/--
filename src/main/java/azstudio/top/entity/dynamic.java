package azstudio.top.entity;

/**
 * Author:
 * Data:2019-05-04 23:14
 * Description:<>
 */
public class dynamic {
    private int id;
    private int createUserId;
    private String dyContent;
    private int replyNum;
    private int likeNum;
    private long createTime;
    private int useStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getDyContent() {
        return dyContent;
    }

    public void setDyContent(String dyContent) {
        this.dyContent = dyContent;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public dynamic() {
    }

    @Override
    public String toString() {
        return "dynamic{" +
                "id=" + id +
                ", createUserId=" + createUserId +
                ", dyContent='" + dyContent + '\'' +
                ", replyNum=" + replyNum +
                ", likeNum=" + likeNum +
                ", createTime=" + createTime +
                ", useStatus=" + useStatus +
                '}';
    }
}

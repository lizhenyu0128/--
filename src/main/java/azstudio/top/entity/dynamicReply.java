package azstudio.top.entity;

/**
 * Author:
 * Data:2019-05-12 14:31
 * Description:<>
 */
public class dynamicReply {
    private int id;
    private int createUserId;
    private int dyId;
    private int replyToId;
    private String replyContent;
    private long createTime;

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

    public int getDyId() {
        return dyId;
    }

    public void setDyId(int dyId) {
        this.dyId = dyId;
    }

    public int getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(int replyToId) {
        this.replyToId = replyToId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public dynamicReply() {
    }

    @Override
    public String toString() {
        return "dynamicReply{" +
                "id=" + id +
                ", createUserId=" + createUserId +
                ", dyId=" + dyId +
                ", replyToId=" + replyToId +
                ", replyContent='" + replyContent + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

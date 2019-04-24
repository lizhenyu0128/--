package azstudio.top.entity;

/**
 * Author:
 * Data:2019-04-24 13:20
 * Description:<>
 */
public class group {
    private int id;
    private int groupCreater;
    private String groupName;
    private String groupSubject;
    private String groupIntroduction;
    private int createTime;
    private int useStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupCreater() {
        return groupCreater;
    }

    public void setGroupCreater(int groupCreater) {
        this.groupCreater = groupCreater;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupSubject() {
        return groupSubject;
    }

    public void setGroupSubject(String groupSubject) {
        this.groupSubject = groupSubject;
    }

    public String getGroupIntroduction() {
        return groupIntroduction;
    }

    public void setGroupIntroduction(String groupIntroduction) {
        this.groupIntroduction = groupIntroduction;
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

    public group() {
    }

    @Override
    public String toString() {
        return "group{" +
                "id=" + id +
                ", groupCreater=" + groupCreater +
                ", groupName='" + groupName + '\'' +
                ", groupSubject='" + groupSubject + '\'' +
                ", groupIntroduction='" + groupIntroduction + '\'' +
                ", createTime=" + createTime +
                ", useStatus=" + useStatus +
                '}';
    }
}

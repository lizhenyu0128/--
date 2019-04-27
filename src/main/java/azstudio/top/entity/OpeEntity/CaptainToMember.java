package azstudio.top.entity.OpeEntity;

/**
 * Author:
 * Data:2019-04-27 22:47
 * Description:<>
 */
public class CaptainToMember {
    private int groupId;
    private String wxId;
    private String memberType;
    private int groupCreater;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public int getGroupCreater() {
        return groupCreater;
    }

    public void setGroupCreater(int groupCreater) {
        this.groupCreater = groupCreater;
    }

    public CaptainToMember() {
    }

    @Override
    public String toString() {
        return "CaptainToMember{" +
                "groupId=" + groupId +
                ", wxId='" + wxId + '\'' +
                ", memberType='" + memberType + '\'' +
                ", groupCreater=" + groupCreater +
                '}';
    }
}

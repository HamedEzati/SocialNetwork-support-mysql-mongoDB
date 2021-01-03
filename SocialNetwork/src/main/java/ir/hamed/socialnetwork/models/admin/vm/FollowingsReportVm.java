package ir.hamed.socialnetwork.models.admin.vm;

public class FollowingsReportVm {
    private String follower;
    private String followed;

    public FollowingsReportVm(){}

    public FollowingsReportVm(String follower, String followed) {
        this.follower = follower;
        this.followed = followed;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }
}

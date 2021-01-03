package ir.hamed.socialnetwork.models.admin.dto;

public class FollowingsReportDto {
    private String follower;
    private String followed;

    public FollowingsReportDto(){}

    public FollowingsReportDto(String follower, String followed) {
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

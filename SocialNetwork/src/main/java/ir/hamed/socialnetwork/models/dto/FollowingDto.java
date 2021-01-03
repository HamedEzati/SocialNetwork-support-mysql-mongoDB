package ir.hamed.socialnetwork.models.dto;

public class FollowingDto {
    private String username;

    public FollowingDto(){

    }

    public FollowingDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

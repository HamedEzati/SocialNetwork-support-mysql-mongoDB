package ir.hamed.socialnetwork.models.dtu;

public class FollowingDto {
    private String mainusername;
    private String username;

    public FollowingDto(){

    }

    public FollowingDto(String username) {
        this.username = username;
    }

    public String getMainusername() {
        return mainusername;
    }

    public void setMainusername(String mainusername) {
        this.mainusername = mainusername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package ir.hamed.socialnetwork.models.vm;

import ir.hamed.socialnetwork.models.entity.mongo.Following;

public class FollowingVm {
    private String mainusername;
    private String username;

    public FollowingVm(){

    }

    public FollowingVm(String username) {
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

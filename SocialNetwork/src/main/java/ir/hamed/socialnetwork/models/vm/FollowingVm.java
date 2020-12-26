package ir.hamed.socialnetwork.models.vm;

import ir.hamed.socialnetwork.models.entity.mongo.Following;

public class FollowingVm {
    private String username;

    public FollowingVm(){

    }

    public FollowingVm(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

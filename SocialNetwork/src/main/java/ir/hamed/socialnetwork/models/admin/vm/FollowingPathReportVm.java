package ir.hamed.socialnetwork.models.admin.vm;

import java.util.List;

public class FollowingPathReportVm {
    private String firstUsername;
    private String secondUsername;
    private String path;

    public FollowingPathReportVm(){}

    public FollowingPathReportVm(String firstUsername, String secondUsername, String path) {
        this.firstUsername = firstUsername;
        this.secondUsername = secondUsername;
        this.path = path;
    }

    public FollowingPathReportVm(String path) {
        this.path = path;
    }

    public String getFirstUsername() {
        return firstUsername;
    }

    public void setFirstUsername(String firstUsername) {
        this.firstUsername = firstUsername;
    }

    public String getSecondUsername() {
        return secondUsername;
    }

    public void setSecondUsername(String secondUsername) {
        this.secondUsername = secondUsername;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

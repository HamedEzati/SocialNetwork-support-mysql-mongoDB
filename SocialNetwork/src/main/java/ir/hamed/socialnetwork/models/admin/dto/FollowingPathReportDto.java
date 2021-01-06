package ir.hamed.socialnetwork.models.admin.dto;

import java.util.List;

public class FollowingPathReportDto {
    private String firstUsername;
    private String secondUsername;
    private String path;

    public FollowingPathReportDto(){}

    public FollowingPathReportDto(String firstUsername, String secondUsername, String path) {
        this.firstUsername = firstUsername;
        this.secondUsername = secondUsername;
        this.path = path;
    }

    public FollowingPathReportDto(String path) {
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

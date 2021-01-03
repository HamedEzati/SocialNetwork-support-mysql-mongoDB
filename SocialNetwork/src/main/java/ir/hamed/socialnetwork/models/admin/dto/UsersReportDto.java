package ir.hamed.socialnetwork.models.admin.dto;

public class UsersReportDto {
    private String username;

    public UsersReportDto(){

    }

    public UsersReportDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

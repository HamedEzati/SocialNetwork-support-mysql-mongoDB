package ir.hamed.socialnetwork.models.admin.vm;

public class UsersReportVm {
    private String username;

    public UsersReportVm(){

    }

    public UsersReportVm(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

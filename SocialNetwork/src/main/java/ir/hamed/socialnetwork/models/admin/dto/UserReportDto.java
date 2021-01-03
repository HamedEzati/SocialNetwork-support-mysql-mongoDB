package ir.hamed.socialnetwork.models.admin.dto;

public class UserReportDto {
    private String username;
    private String email;
    private String numberOfPost;
    private String numberOfFollowing;

    public UserReportDto(){};

    public UserReportDto(String username, String email, String numberOfPost, String numberOfFollowing) {
        this.username = username;
        this.email = email;
        this.numberOfPost = numberOfPost;
        this.numberOfFollowing = numberOfFollowing;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberOfPost() {
        return numberOfPost;
    }

    public void setNumberOfPost(String numberOfPost) {
        this.numberOfPost = numberOfPost;
    }

    public String getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public void setNumberOfFollowing(String numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }
}

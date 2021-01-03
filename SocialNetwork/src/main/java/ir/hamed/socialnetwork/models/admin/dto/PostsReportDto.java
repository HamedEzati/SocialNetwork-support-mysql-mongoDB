package ir.hamed.socialnetwork.models.admin.dto;

public class PostsReportDto {
    private String id;
    private String username;
    private String title;

    public PostsReportDto(){}

    public PostsReportDto(String id, String username, String title) {
        this.id = id;
        this.username = username;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package ir.hamed.socialnetwork.models.admin.vm;

public class PostReportVm {
    private String id;
    private String username;
    private String title;
    private String description;
    private String numberOfComment;

    public PostReportVm(){}

    public PostReportVm(String id, String username, String title, String description, String numberOfComment) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.numberOfComment = numberOfComment;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOfComment() {
        return numberOfComment;
    }

    public void setNumberOfComment(String numberOfComment) {
        this.numberOfComment = numberOfComment;
    }
}

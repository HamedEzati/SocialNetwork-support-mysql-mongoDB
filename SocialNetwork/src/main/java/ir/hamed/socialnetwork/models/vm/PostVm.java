package ir.hamed.socialnetwork.models.vm;

public class PostVm {
    private String title;
    private String description;

    public PostVm(){

    }
    public PostVm(String title, String description) {
        this.title = title;
        this.description = description;
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
}

package ir.hamed.socialnetwork.models.vm;

public class CommentVm {
    private String postId;
    private String text;

    public CommentVm(){

    }

    public CommentVm(String postId, String text) {
        this.postId = postId;
        this.text = text;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

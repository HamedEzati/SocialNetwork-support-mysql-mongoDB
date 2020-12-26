package ir.hamed.socialnetwork.Request;

public class CommentRequest {
    private String text;
    private String accountName;
    private String postId;

    public CommentRequest(){

    }

    public CommentRequest(String text,String accountName, String postId) {
        this.text = text;
        this.accountName = accountName;
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}

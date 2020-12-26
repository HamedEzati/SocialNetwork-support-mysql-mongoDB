package ir.hamed.socialnetwork.models.entity.mongo;

import org.bson.types.ObjectId;

public class Comment {
    private String id = new ObjectId().toString();
    private String postId;
    private String text;

    public Comment(){

    }

    public Comment(String postId,String text) {
        this.postId = postId;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

package ir.hamed.socialnetwork.models.entity.mongo;

import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;

public class Post {
    private String id = new ObjectId().toString();
    private String title;
    private String description;

    private List<Comment> comments = new ArrayList<>();

    public Post(){

    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public void setNewComment(Comment comment){
        this.comments.add(comment);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

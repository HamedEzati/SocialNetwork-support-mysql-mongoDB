package ir.hamed.socialnetwork.models.entity.mongo;


import org.bson.types.ObjectId;

public class Following {
    private String id = new ObjectId().toString();
    private String username;
    public Following(){

    }

    public Following(String username) {
        this.username = username;
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
}

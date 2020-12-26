package ir.hamed.socialnetwork.models.entity.mysql;

import ir.hamed.socialnetwork.models.entity.mongo.Comment;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "postsMysql")
public class PostMysql implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userMysql_id", nullable = false)
    private UserMysql userMysql;

@OneToMany(mappedBy = "postMysql", fetch = FetchType.EAGER,
        cascade = CascadeType.ALL)
    private List<CommentMysql> comments = new ArrayList<>();

    public PostMysql(){

    }

    public PostMysql(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public void setNewComment(CommentMysql comment){
        this.comments.add(comment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<CommentMysql> getComments() {
        return comments;
    }

    public void setComments(List<CommentMysql> comments) {
        this.comments = comments;
    }

    public UserMysql getUserMysql() {
        return userMysql;
    }

    public void setUserMysql(UserMysql userMysql) {
        this.userMysql = userMysql;
    }

}

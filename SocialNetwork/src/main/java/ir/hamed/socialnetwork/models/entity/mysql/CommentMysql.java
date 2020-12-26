package ir.hamed.socialnetwork.models.entity.mysql;

import javax.persistence.*;

@Entity(name = "commentsMysql")
public class CommentMysql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postId;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postMysql_id", nullable = false)
    private PostMysql postMysql;

    public CommentMysql(){

    }

    public CommentMysql(String text,String postId) {
        this.text = text;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PostMysql getPostMysql() {
        return postMysql;
    }

    public void setPostMysql(PostMysql postMysql) {
        this.postMysql = postMysql;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}

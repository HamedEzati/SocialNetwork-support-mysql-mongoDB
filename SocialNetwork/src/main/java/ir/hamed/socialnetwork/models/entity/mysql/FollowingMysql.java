package ir.hamed.socialnetwork.models.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "followingsmysql")
public class FollowingMysql implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userMysql_id", nullable = false)
    private UserMysql userMysql;

    public FollowingMysql(){

    }

    public FollowingMysql(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserMysql getUserMysql() {
        return userMysql;
    }

    public void setUserMysql(UserMysql userMysql) {
        this.userMysql = userMysql;
    }
}

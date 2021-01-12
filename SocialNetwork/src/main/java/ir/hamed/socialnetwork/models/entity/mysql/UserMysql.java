package ir.hamed.socialnetwork.models.entity.mysql;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "usersmysql")
public class UserMysql{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "usersmysql_rolesmysql",
            joinColumns = @JoinColumn(name = "usersmysql_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rolesmysql_id",referencedColumnName = "id"))
    private Set<RoleMysql> roles = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userMysql",
        cascade = CascadeType.ALL)
    private List<PostMysql> posts = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userMysql",
            cascade = CascadeType.ALL)
    private List<FollowingMysql> followings = new ArrayList<>();
    public UserMysql() {

    }

    public UserMysql(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setNewPost(PostMysql post){
        this.posts.add(post);
    }

    public void setNewFollowing(FollowingMysql following){
        this.followings.add(following);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleMysql> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleMysql> roles) {
        this.roles = roles;
    }

    public List<PostMysql> getPosts() {
        return posts;
    }

    public void setPosts(List<PostMysql> posts) {
        this.posts = posts;
    }

    public List<FollowingMysql> getFollowings() {
        return followings;
    }

    public void setFollowings(List<FollowingMysql> followings) {
        this.followings = followings;
    }


}
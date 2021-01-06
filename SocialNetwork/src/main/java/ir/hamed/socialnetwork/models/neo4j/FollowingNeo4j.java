package ir.hamed.socialnetwork.models.neo4j;

import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("following")
public class FollowingNeo4j {
    @Id
    private final String username;

    @Relationship(type = "FOLLOWING",direction = Relationship.Direction.OUTGOING)
    private Set<FollowingNeo4j> followings = new HashSet<>();

    public FollowingNeo4j(String username) {
        this.username = username;
    }

    public void setNewFollowing(FollowingNeo4j following){
        this.followings.add(following);
    }


    public String getUsername() {
        return username;
    }

    public Set<FollowingNeo4j> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<FollowingNeo4j> followings) {
        this.followings = followings;
    }
}

package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dto.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.neo4j.FollowingNeo4j;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.neo4j.FollowingNeo4jRepository;
import ir.hamed.socialnetwork.security.mongo.jwt.JwtUtilsMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class FollowingServiceMongoImpl implements FollowingService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;
    @Autowired(required = false)
    JwtUtilsMongo jwtUtilsMongo;
    @Autowired(required = false)
    FollowingNeo4jRepository followingNeo4jRepository;
    @Override
    public ResponseEntity<?> following(FollowingDto followingDto, String authorization, HttpSession session) {

        String username =(String) session.getAttribute("username");
        if (username == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: session not exist!"));
        }
        System.out.println( "session: " + username );

        if (!userMongoRepository.existsByUsername(followingDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: username not exists to follow!"));
        }

        User user = userMongoRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: user is not found."));

        Following following = new Following(followingDto.getUsername());

        user.setNewFollowing(following);
        userMongoRepository.save(user);
        savedInNeo4j(user,following);
        return ResponseEntity.ok(new MessageResponse("Following: "+followingDto.getUsername()));
    }

    private void savedInNeo4j(User user,Following following){
        FollowingNeo4j followingNeo4j = followingNeo4jRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: user is not found."));
        FollowingNeo4j followed = followingNeo4jRepository.findByUsername(following.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: user is not found."));
        followingNeo4j.setNewFollowing(followed);
        followingNeo4jRepository.save(followingNeo4j);
        
    }
}

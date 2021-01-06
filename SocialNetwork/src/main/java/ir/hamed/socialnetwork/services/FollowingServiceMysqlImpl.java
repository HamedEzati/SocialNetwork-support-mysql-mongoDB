package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.FollowingMapperImpl;
import ir.hamed.socialnetwork.models.dto.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.FollowingMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.models.neo4j.FollowingNeo4j;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mysql.FollowingMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import ir.hamed.socialnetwork.repository.neo4j.FollowingNeo4jRepository;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class FollowingServiceMysqlImpl implements FollowingService {
    @Autowired(required = false)
    JwtUtilsMysql jwtUtilsMysql;
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;
    @Autowired(required = false)
    FollowingMysqlRepository followingMysqlRepository;
    @Autowired(required = false)
    FollowingNeo4jRepository followingNeo4jRepository;

    FollowingMapperImpl followingMapper = new FollowingMapperImpl();

    @Override
    public ResponseEntity<?> following(FollowingDto followingDto, String authorization, HttpSession session) {

        String username =(String) session.getAttribute("username");
        if (username == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: session not exist!"));
        }
        System.out.println( "session: " + username );

        UserMysql user = userMysqlRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: user is not found."));
        if(!userMysqlRepository.existsByUsername(followingDto.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username not exist!"));
        }
        FollowingMysql followingMysql = followingMapper.followingDtoToFollowingMysql(followingDto);
        followingMysql.setUserMysql(user);
        FollowingMysql newfollowingMysql = followingMysqlRepository.save(followingMysql);

        user.setNewFollowing(newfollowingMysql);
        userMysqlRepository.save(user);
        savedInNeo4j(user,newfollowingMysql);

        return ResponseEntity.ok(new MessageResponse("following: "+ followingDto.getUsername()));
    }

    private void savedInNeo4j(UserMysql user, FollowingMysql following){
        FollowingNeo4j followingNeo4j = followingNeo4jRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: user is not found."));
        FollowingNeo4j followed = followingNeo4jRepository.findByUsername(following.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: user is not found."));
        followingNeo4j.setNewFollowing(followed);
        followingNeo4jRepository.save(followingNeo4j);
    }

}


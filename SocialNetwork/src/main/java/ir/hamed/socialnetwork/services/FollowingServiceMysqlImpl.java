package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.FollowingMapperImpl;
import ir.hamed.socialnetwork.models.dtu.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mysql.FollowingMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mysql.FollowingMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowingServiceMysqlImpl implements FollowingService {
    @Autowired(required = false)
    JwtUtilsMysql jwtUtilsMysql;
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;
    @Autowired(required = false)
    FollowingMysqlRepository followingMysqlRepository;

    FollowingMapperImpl followingMapper = new FollowingMapperImpl();

    @Override
    public ResponseEntity<?> following(FollowingDto followingDto, String authorization) {
        String username = jwtUtilsMysql.getUserNameFromJwtToken(authorization.substring(7));
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
        return ResponseEntity.ok(new MessageResponse("following: "+ followingDto.getUsername()));
    }

}


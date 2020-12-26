package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.security.mongo.jwt.JwtUtilsMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowingServiceMongoImpl implements FollowingService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;
    @Autowired(required = false)
    JwtUtilsMongo jwtUtilsMongo;
    @Override
    public ResponseEntity<?> following(FollowingDto followingDto, String authorization) {
        if (!userMongoRepository.existsByUsername(followingDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: username not exists to follow!"));
        }
        String username = jwtUtilsMongo.getUserNameFromJwtToken(authorization.substring(7));
        User user = userMongoRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: user is not found."));
        Following following = new Following(followingDto.getUsername());
        user.setNewFollowing(following);
        userMongoRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Following: "+followingDto.getUsername()));
    }
}

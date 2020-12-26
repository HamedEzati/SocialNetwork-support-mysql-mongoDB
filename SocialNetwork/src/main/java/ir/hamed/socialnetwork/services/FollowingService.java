package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import org.springframework.http.ResponseEntity;

public interface FollowingService {
    public ResponseEntity<?> following(FollowingDto followingDto, String authorization);
}

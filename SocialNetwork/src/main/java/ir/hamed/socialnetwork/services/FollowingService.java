package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dto.FollowingDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface FollowingService {
    public ResponseEntity<?> following(FollowingDto followingDto, String authorization, HttpSession session);
}

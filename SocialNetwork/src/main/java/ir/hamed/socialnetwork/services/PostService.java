package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dto.PostDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface PostService {
    public ResponseEntity<?> sendPost(PostDto postDto, String authorization, HttpSession session);
}

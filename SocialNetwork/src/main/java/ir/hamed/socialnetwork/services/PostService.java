package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface PostService {
    public ResponseEntity<?> sendPost(PostDto postDto,String authorization);
}

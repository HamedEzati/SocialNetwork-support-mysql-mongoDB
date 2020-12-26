package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.CommentDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    public ResponseEntity<?> sendComment(CommentDto commentDto,String authorization);
}

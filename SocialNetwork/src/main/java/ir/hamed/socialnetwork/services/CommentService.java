package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.CommentDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface CommentService {
    public ResponseEntity<?> sendComment(CommentDto commentDto, String authorization, HttpSession session);
}

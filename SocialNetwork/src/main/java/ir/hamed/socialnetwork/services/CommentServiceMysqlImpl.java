package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.CommentMapperImpl;
import ir.hamed.socialnetwork.models.dtu.CommentDto;
import ir.hamed.socialnetwork.models.entity.mongo.Comment;
import ir.hamed.socialnetwork.models.entity.mysql.CommentMysql;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mysql.CommentMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.PostMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CommentServiceMysqlImpl implements CommentService {
    @Autowired(required = false)
    PostMysqlRepository postMysqlRepository;

    @Autowired(required = false)
    CommentMysqlRepository commentMysqlRepository;

    CommentMapperImpl commentMapper = new CommentMapperImpl();

    @Override
    public ResponseEntity<?> sendComment(CommentDto commentDto, String authorization, HttpSession session) {

        String username =(String) session.getAttribute("username");
        if (username == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: session not exist!"));
        }
        System.out.println( "session: " + username );

        CommentMysql commentMysql = commentMapper.commentDtoToCommentMysql(commentDto);

        PostMysql postMysql = postMysqlRepository.findById(Long.valueOf(commentMysql.getPostId()))
                .orElseThrow(() -> new RuntimeException("Error: post is not found."));

        commentMysql.setPostMysql(postMysql);
        commentMysqlRepository.save(commentMysql);

        postMysql.setNewComment(commentMysql);
        postMysqlRepository.save(postMysql);

        return ResponseEntity.ok(new MessageResponse("Comment send."));
    }
}

package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.CommentDto;
import ir.hamed.socialnetwork.models.entity.mongo.Comment;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.CommentMysql;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentServiceMongoImpl implements CommentService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;
    @Override
    public ResponseEntity<?> sendComment(CommentDto commentDto, String authorization, HttpSession session) {

        String username =(String) session.getAttribute("username");
        if (username == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: session not exist!"));
        }
        System.out.println( "session: " + username );

        Comment comment = new Comment(commentDto.getPostId(),commentDto.getText());

        List<User> users = userMongoRepository.findAll();
        for(int j = 0; j < users.size();j++){
            List<Post> posts = users.get(j).getPosts();
            for (int i = 0;i<posts.size();i++){
                if(posts.get(i).getId().equals(commentDto.getPostId())){
                    posts.get(i).setNewComment(comment);
                    userMongoRepository.save(users.get(j));
                    return ResponseEntity.ok(new MessageResponse("Comment send."));
                }
            }
        }

        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: post not exist!"));
    }
}

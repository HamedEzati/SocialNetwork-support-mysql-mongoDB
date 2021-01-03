package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dto.PostDto;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.security.mongo.jwt.JwtUtilsMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PostServiceMongoImpl implements PostService {
    @Autowired(required = false)
    JwtUtilsMongo jwtUtilsMongo;
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;
    @Override
    public ResponseEntity<?> sendPost(PostDto postDto, String authorization, HttpSession session) {

        String username =(String) session.getAttribute("username");
        if (username == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: session not exist!"));
        }
        System.out.println( "session: " + username );

        User user = userMongoRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: user is not found."));

        if (user == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: user not exist!"));
        }

        Post newPost = new Post(postDto.getTitle(),postDto.getDescription());

        user.setNewPost(newPost);
        userMongoRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Post send."));
    }

}

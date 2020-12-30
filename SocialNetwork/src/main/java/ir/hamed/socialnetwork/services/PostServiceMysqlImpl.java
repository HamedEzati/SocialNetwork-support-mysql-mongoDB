package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.dtu.PostDto;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mysql.PostMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PostServiceMysqlImpl implements PostService {
    @Autowired(required = false)
    JwtUtilsMysql jwtUtilsMysql;
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;
    @Autowired(required = false)
    PostMysqlRepository postMysqlRepository;
    PostMapperImpl postMapper = new PostMapperImpl();
    @Override
    public ResponseEntity<?> sendPost(PostDto postDto, String authorization, HttpSession session) {

        String username =(String) session.getAttribute("username");
        if (username == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: session not exist!"));
        }
        System.out.println( "session: " + username );

        UserMysql user = userMysqlRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: user is not found."));

        if (user == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: user not exist!"));
        }

        PostMysql newPost = postMapper.postDtoToPostMysql(postDto);
        newPost.setUserMysql(user);
        PostMysql savedPost = postMysqlRepository.save(newPost);

        user.setNewPost(savedPost);
        userMysqlRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Post send."));
    }
}

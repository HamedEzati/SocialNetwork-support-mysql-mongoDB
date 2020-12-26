package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.dtu.PostDto;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.mysql.PostMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import ir.hamed.socialnetwork.security.mongo.jwt.JwtUtilsMongo;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostServiceMongoImpl implements PostService {
    @Autowired(required = false)
    JwtUtilsMongo jwtUtilsMongo;
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;
    @Override
    public ResponseEntity<?> sendPost(PostDto postDto, String authorization) {
        String username = jwtUtilsMongo.getUserNameFromJwtToken(authorization.substring(7));
        User user = userMongoRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: user is not found."));
        if (!userMongoRepository.existsByUsername(username)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username not exist!"));
        }

        Post newPost = new Post(postDto.getTitle(),postDto.getDescription());
        user.setNewPost(newPost);
        userMongoRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Post send."));
    }
}
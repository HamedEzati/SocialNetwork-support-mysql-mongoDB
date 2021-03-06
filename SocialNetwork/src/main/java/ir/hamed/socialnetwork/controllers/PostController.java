package ir.hamed.socialnetwork.controllers;
import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.dto.PostDto;
import ir.hamed.socialnetwork.models.vm.PostVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.PostServiceMongoImpl;
import ir.hamed.socialnetwork.services.PostServiceMysqlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/test")
public class PostController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    PostServiceMysqlImpl postServiceMysql;

    @Autowired(required = false)
    PostServiceMongoImpl postServiceMongo;

    PostMapperImpl postMapper = new PostMapperImpl();

    @PostMapping("/sendpost")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createpost(@RequestBody PostVm postVm, @RequestHeader String authorization, HttpSession session){
        PostDto postDto = postMapper.PostVmToPostDto(postVm);
        if(mysqldb){
            return postServiceMysql.sendPost(postDto,authorization,session);
        }
        if(mongodb){
            return postServiceMongo.sendPost(postDto,authorization,session);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));

    }

}

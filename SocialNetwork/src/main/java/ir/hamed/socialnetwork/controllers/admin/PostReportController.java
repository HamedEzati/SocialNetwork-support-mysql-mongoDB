package ir.hamed.socialnetwork.controllers.admin;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dtu.PostDto;
import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.vm.PostVm;
import ir.hamed.socialnetwork.models.vm.UserVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.admin.PostReportServiceMongoImpl;
import ir.hamed.socialnetwork.services.admin.UserReportServiceMongoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class PostReportController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    PostReportServiceMongoImpl postReportServiceMongo;


    PostMapperImpl postMapper = new PostMapperImpl();

    @PostMapping("/getposts")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getposts(){
        if(mysqldb){

        }
        if(mongodb){
            List<PostVm> posts = postMapper.listPostDtoToPostVm(postReportServiceMongo.getposts());
            return ResponseEntity.ok(posts);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }

    @PostMapping("selectpost")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> selectpost(@RequestBody PostVm postVm){
        PostDto postDto = postMapper.PostVmToPostDto(postVm);
        if(mysqldb){

        }
        if(mongodb){
            PostDto resultPostDto = postReportServiceMongo.selectpost(postDto);
            PostVm resultPostVm = postMapper.postDtoToPostVm(resultPostDto);
            return ResponseEntity.ok(resultPostVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }
}

package ir.hamed.socialnetwork.controllers.admin;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.PostReportDto;
import ir.hamed.socialnetwork.models.admin.vm.PostReportVm;
import ir.hamed.socialnetwork.models.admin.vm.PostsReportVm;
import ir.hamed.socialnetwork.models.dto.PostDto;
import ir.hamed.socialnetwork.models.vm.PostVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.admin.PostReportServiceMongoImpl;
import ir.hamed.socialnetwork.services.admin.PostReportServiceMysqlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasRole('ADMIN')")
public class PostReportController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    PostReportServiceMongoImpl postReportServiceMongo;

    @Autowired(required = false)
    PostReportServiceMysqlImpl postReportServiceMysql;


    PostMapperImpl postMapper = new PostMapperImpl();

    @PostMapping("/getposts")
    public ResponseEntity<?> getposts(){
        if(mysqldb){
            List<PostsReportVm> postsReportVm = postMapper.listPostsReportDtoToPostsReportVm(postReportServiceMysql.getposts());
            return ResponseEntity.ok(postsReportVm);
        }
        if(mongodb){
            List<PostsReportVm> postsReportVm = postMapper.listPostsReportDtoToPostsReportVm(postReportServiceMongo.getposts());
            return ResponseEntity.ok(postsReportVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }

    @PostMapping("selectpost")
    public ResponseEntity<?> selectpost(@RequestBody PostReportVm postReportVm){
        PostReportDto postReportDto = postMapper.postReportVmToPostReportDto(postReportVm);
        if(mysqldb){
            PostReportDto resultPostReportDto = postReportServiceMysql.selectpost(postReportDto);
            PostReportVm resultPostVm = postMapper.postReportDtoToPostReportVm(resultPostReportDto);
            return ResponseEntity.ok(resultPostVm);
        }
        if(mongodb){
            PostReportDto resultPostReportDto = postReportServiceMongo.selectpost(postReportDto);
            PostReportVm resultPostVm = postMapper.postReportDtoToPostReportVm(resultPostReportDto);
            return ResponseEntity.ok(resultPostVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }
}

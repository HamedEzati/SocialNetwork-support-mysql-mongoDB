package ir.hamed.socialnetwork.controllers;

import ir.hamed.socialnetwork.mapper.CommentMapperImpl;
import ir.hamed.socialnetwork.models.dtu.CommentDto;
import ir.hamed.socialnetwork.models.vm.CommentVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mysql.CommentMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.PostMysqlRepository;
import ir.hamed.socialnetwork.services.CommentServiceMongoImpl;
import ir.hamed.socialnetwork.services.CommentServiceMysqlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class CommentController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;
    @Autowired(required = false)
    CommentServiceMysqlImpl commentServiceMysql;

    @Autowired(required = false)
    CommentServiceMongoImpl commentServiceMongo;

    @Autowired(required = false)
    PostMysqlRepository postMysqlRepository;

    @Autowired(required = false)
    CommentMysqlRepository commentMysqlRepository;

    CommentMapperImpl commentMapper = new CommentMapperImpl();

    @PostMapping("/sendcomment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createpost(@RequestBody CommentVm commentVm, @RequestHeader String authorization){
        CommentDto commentDto = commentMapper.commentVmToCommentDto(commentVm);
        if(mysqldb){
            return commentServiceMysql.sendComment(commentDto,authorization);
        }
        if(mongodb){
            return commentServiceMongo.sendComment(commentDto,authorization);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));

    }
}

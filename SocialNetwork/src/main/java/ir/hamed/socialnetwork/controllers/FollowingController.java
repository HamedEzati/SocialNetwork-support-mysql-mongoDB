package ir.hamed.socialnetwork.controllers;

import ir.hamed.socialnetwork.mapper.FollowingMapperImpl;
import ir.hamed.socialnetwork.models.dtu.FollowingDto;
import ir.hamed.socialnetwork.models.vm.FollowingVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.FollowingServiceMongoImpl;
import ir.hamed.socialnetwork.services.FollowingServiceMysqlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class FollowingController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;
    @Autowired(required = false)
    FollowingServiceMysqlImpl followingServiceMysql;
    @Autowired(required = false)
    FollowingServiceMongoImpl followingServiceMongo;

    FollowingMapperImpl followingMapper = new FollowingMapperImpl();
    @PostMapping("/following")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> following(@RequestBody FollowingVm followingVm, @RequestHeader String authorization){
        FollowingDto followingDto = followingMapper.followingVmToFollowingDto(followingVm);
        if(mysqldb){
            return followingServiceMysql.following(followingDto,authorization);
        }
        if(mongodb){
            return followingServiceMongo.following(followingDto,authorization);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));

    }
}

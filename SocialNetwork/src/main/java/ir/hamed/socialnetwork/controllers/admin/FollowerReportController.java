package ir.hamed.socialnetwork.controllers.admin;

import ir.hamed.socialnetwork.mapper.FollowingMapperImpl;
import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.FollowingsReportDto;
import ir.hamed.socialnetwork.models.admin.dto.PostReportDto;
import ir.hamed.socialnetwork.models.admin.vm.FollowingsReportVm;
import ir.hamed.socialnetwork.models.admin.vm.PostReportVm;
import ir.hamed.socialnetwork.models.admin.vm.PostsReportVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.admin.FollowingReportServiceMongoImpl;
import ir.hamed.socialnetwork.services.admin.FollowingReportServiceMysqlImpl;
import ir.hamed.socialnetwork.services.admin.PostReportServiceMongoImpl;
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
public class FollowerReportController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    FollowingReportServiceMongoImpl followingReportServiceMongo;

    @Autowired(required = false)
    FollowingReportServiceMysqlImpl followingReportServiceMysql;


    FollowingMapperImpl followingMapper = new FollowingMapperImpl();

    @PostMapping("/getfollowings")
    public ResponseEntity<?> getfollowings(){
        if(mysqldb){
            List<FollowingsReportVm> followingsReportVm =
                    followingMapper.listFollowingsReportDtoToFollowingsReportVm(followingReportServiceMysql.getfollowings());
            return ResponseEntity.ok(followingsReportVm);
        }
        if(mongodb){
            List<FollowingsReportVm> followingsReportVm =
                    followingMapper.listFollowingsReportDtoToFollowingsReportVm(followingReportServiceMongo.getfollowings());
            return ResponseEntity.ok(followingsReportVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }
}

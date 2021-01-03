package ir.hamed.socialnetwork.controllers.admin;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.UserReportDto;
import ir.hamed.socialnetwork.models.admin.vm.UserReportVm;
import ir.hamed.socialnetwork.models.admin.vm.UsersReportVm;
import ir.hamed.socialnetwork.models.dto.UserDto;
import ir.hamed.socialnetwork.models.vm.UserVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.admin.UserReportServiceMongoImpl;
import ir.hamed.socialnetwork.services.admin.UserReportServiceMysqlImpl;
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
public class UserReportController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    UserReportServiceMongoImpl userReportServiceMongo;

    @Autowired(required = false)
    UserReportServiceMysqlImpl userReportServiceMysql;

    UserMapperImpl userMapper = new UserMapperImpl();

    @PostMapping("/getusers")
    public ResponseEntity<?> getusers(){
        if(mysqldb){
            List<UsersReportVm> usersReportVm = userMapper.listUsersReportDtoToUsersReportVm(userReportServiceMysql.getusers());
            return ResponseEntity.ok(usersReportVm);
        }
        if(mongodb){
            List<UsersReportVm> usersReportVm = userMapper.listUsersReportDtoToUsersReportVm(userReportServiceMongo.getusers());
            return ResponseEntity.ok(usersReportVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }

    @PostMapping("selectuser")
    public ResponseEntity<?> selectuser(@RequestBody UserReportVm userReportVm){
        UserReportDto userReportDto = userMapper.userReportVmToUserReportDto(userReportVm);
        if(mysqldb){
            UserReportDto resultUserReportDto = userReportServiceMysql.selectuser(userReportDto);
            UserReportVm resultUserReportVm = userMapper.userReportDtoToUserReportVm(resultUserReportDto);
            return ResponseEntity.ok(resultUserReportVm);
        }
        if(mongodb){
            UserReportDto resultUserReportDto = userReportServiceMongo.selectuser(userReportDto);
            UserReportVm resultUserReportVm = userMapper.userReportDtoToUserReportVm(resultUserReportDto);
            return ResponseEntity.ok(resultUserReportVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }


}

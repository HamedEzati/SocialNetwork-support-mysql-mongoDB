package ir.hamed.socialnetwork.controllers.admin;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dtu.UserDto;
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
public class UserReportController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    UserReportServiceMongoImpl userReportServiceMongo;


    UserMapperImpl userMapper = new UserMapperImpl();

    @PostMapping("/getusers")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getusers(){
        if(mysqldb){

        }
        if(mongodb){
            List<UserVm> usersVm = userMapper.listUserDtoToUserVm(userReportServiceMongo.getusers());
            return ResponseEntity.ok(usersVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }

    @PostMapping("selectuser")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> selectuser(@RequestBody UserVm userVm){
        UserDto userDto = userMapper.UserVmToUserDto(userVm);
        if(mysqldb){

        }
        if(mongodb){
            UserDto resultUserDto = userReportServiceMongo.selectuser(userDto);
            UserVm resultUserVm = userMapper.userDtoToUserVm(resultUserDto);
            return ResponseEntity.ok(resultUserVm);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }


}

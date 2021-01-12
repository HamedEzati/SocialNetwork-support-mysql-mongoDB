package ir.hamed.socialnetwork.controllers.admin;


import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dto.UserDto;
import ir.hamed.socialnetwork.models.vm.UserVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.security.jwt.mysql.jwt.JwtUtilsMysql;
import ir.hamed.socialnetwork.services.SigninServiceMongoImpl;
import ir.hamed.socialnetwork.services.SigninServiceMysqlImpl;
import ir.hamed.socialnetwork.services.SignupServiceMongoImpl;
import ir.hamed.socialnetwork.services.SignupServiceMysqlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ADMIN')")
public class SignupByAdminController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    AuthenticationManager authenticationManager;

    @Autowired(required = false)
    PasswordEncoder encoder;

    @Autowired(required = false)
    JwtUtilsMysql jwtUtilsMysql;

    @Autowired(required = false)
    SignupServiceMysqlImpl signupServiceMysql;

    @Autowired(required = false)
    SignupServiceMongoImpl signupServiceMongo;

    @Autowired(required = false)
    SigninServiceMysqlImpl signinServiceMysql;

    @Autowired(required = false)
    SigninServiceMongoImpl signinServiceMongo;

    UserMapperImpl userMapper = new UserMapperImpl();

    @PostMapping("/signupbyadmin")
    public ResponseEntity<?> registerUser(@RequestBody UserVm userVm) {
        UserDto userDto = userMapper.UserVmToUserDto(userVm);
        if (mysqldb){
            return signupServiceMysql.signupUser(userDto);
        }
        if (mongodb){
            return signupServiceMongo.signupUser(userDto);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("bad request."));
    }

}

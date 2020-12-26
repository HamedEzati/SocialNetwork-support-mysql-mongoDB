package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.UserMapper;
import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.entity.ERole;

import ir.hamed.socialnetwork.models.entity.mongo.Role;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.RoleMysql;

import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.models.vm.UserVm;
import ir.hamed.socialnetwork.payload.response.ErrorsResponse;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.mysql.RoleMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SignupServiceMysqlImpl implements SignupService {
    @Value("${mysqldb}")
    private boolean mysqldb;
    @Value("${mongodb}")
    private boolean mongodb;
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;
    @Autowired(required = false)
    RoleMysqlRepository roleMysqlRepository;
    @Autowired(required = false)
    PasswordEncoder encoder;


    UserMapperImpl userMapper = new UserMapperImpl();


    public SignupServiceMysqlImpl(){

    }

    @Override
    public List<String> validationUser(UserDto userDto) {

        //map UserDto to User Entity
        UserMysql userMysql = userMapper.userDtoToUserMysql(userDto);

        //error list
        List<String> errors = new ArrayList<>();
        if (userMysqlRepository.existsByUsername(userMysql.getUsername())) {
            errors.add("Error: Username is already taken!");
        }
        if (userMysqlRepository.existsByEmail(userMysql.getEmail())) {
            errors.add("Error: Email is already taken!");
        }
        return errors;
    }

    @Override
    public ResponseEntity<?> signupUser(UserDto userDto) {
        //error list
        System.out.println("**********************");
        List<String> errors = validationUser(userDto);
        if(errors.size() != 0){

            return ResponseEntity
                    .badRequest()
                    .body(new ErrorsResponse(errors));
        }

        //map UserDto to User Entity
        UserMysql userMysql = userMapper.userDtoToUserMysql(userDto);

        //find roles
        Set<RoleMysql> roles = new HashSet<>();

        RoleMysql userRole = roleMysqlRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        //set userMysql roles
        userMysql.setRoles(roles);
        userMysql.setPassword(encoder.encode(userMysql.getPassword()));
        userMysqlRepository.save(userMysql);
        return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
    }
}

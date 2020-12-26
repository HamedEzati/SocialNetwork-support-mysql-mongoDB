package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.entity.ERole;
import ir.hamed.socialnetwork.models.entity.mongo.Role;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.RoleMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.payload.response.ErrorsResponse;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.RoleMongoRepository;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SignupServiceMongoImpl implements SignupService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;
    @Autowired(required = false)
    RoleMongoRepository roleMongoRepository;
    @Autowired(required = false)
    PasswordEncoder encoder;


    UserMapperImpl userMapper = new UserMapperImpl();


    @Override
    public List<String> validationUser(UserDto userDto) {

        //map UserDto to User Entity
        User user = userMapper.userDtoToUserMongo(userDto);

        //error list
        List<String> errors = new ArrayList<>();
        if (userMongoRepository.existsByUsername(user.getUsername())) {
            errors.add("Error: Username is already taken!");
        }
        if (userMongoRepository.existsByEmail(user.getEmail())) {
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
        User user = userMapper.userDtoToUserMongo(userDto);

        //find roles
        Set<Role> roles = new HashSet<>();

        Role userRole = roleMongoRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        //set userMysql roles
        user.setRoles(roles);
        user.setPassword(encoder.encode(user.getPassword()));
        userMongoRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
    }
}

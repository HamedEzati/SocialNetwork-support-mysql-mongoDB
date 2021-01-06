package ir.hamed.socialnetwork.controllers;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dto.UserDto;
import ir.hamed.socialnetwork.models.entity.ERole;
import ir.hamed.socialnetwork.models.entity.mongo.Role;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.RoleMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.models.vm.UserVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.RoleMongoRepository;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.mysql.RoleMysqlRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import ir.hamed.socialnetwork.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
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

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody UserVm userVm, HttpServletRequest request) {
		UserDto userDto = userMapper.UserVmToUserDto(userVm);
		if (mysqldb){
			return signinServiceMysql.signinUser(userDto,request);
		}
		if (mongodb){
			return signinServiceMongo.signinUser(userDto,request);
		}
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("bad request."));
	}

	@PostMapping("/signup")
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

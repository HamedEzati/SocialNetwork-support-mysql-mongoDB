package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.payload.response.JwtResponse;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.security.mongo.jwt.JwtUtilsMongo;
import ir.hamed.socialnetwork.security.mongo.service.UserMongoDetailsImpl;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import ir.hamed.socialnetwork.security.mysql.service.UserMysqlDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SigninServiceMongoImpl implements SigninService {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired(required = false)
    JwtUtilsMongo jwtUtilsMongo;
    @Autowired(required = false)
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> signinUser(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtilsMongo.generateJwtToken(authentication);

        UserMongoDetailsImpl userDetails = (UserMongoDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}

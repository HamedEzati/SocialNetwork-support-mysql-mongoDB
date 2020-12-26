package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.payload.response.JwtResponse;
import ir.hamed.socialnetwork.security.mysql.jwt.JwtUtilsMysql;
import ir.hamed.socialnetwork.security.mysql.service.UserMysqlDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "mysqldb")
public class SigninServiceMysqlImpl implements SigninService {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired(required = false)
    JwtUtilsMysql jwtUtilsMysql;
    @Autowired(required = false)
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> signinUser(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtilsMysql.generateJwtToken(authentication);

        UserMysqlDetailsImpl userDetails = (UserMysqlDetailsImpl) authentication.getPrincipal();
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
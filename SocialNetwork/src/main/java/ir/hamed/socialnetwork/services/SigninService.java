package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface SigninService {

    public ResponseEntity<?> signinUser(UserDto userDto, HttpServletRequest request);
}

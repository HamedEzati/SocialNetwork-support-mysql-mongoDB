package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.UserDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SigninService {

    public ResponseEntity<?> signinUser(UserDto userDto, HttpServletRequest request);
}

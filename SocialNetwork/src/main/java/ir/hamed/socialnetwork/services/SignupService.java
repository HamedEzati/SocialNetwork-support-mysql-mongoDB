package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SignupService {
    public List<String> validationUser(UserDto userDto);
    public ResponseEntity<?> signupUser(UserDto userDto);

}

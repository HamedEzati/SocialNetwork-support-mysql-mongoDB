package ir.hamed.socialnetwork.services;

import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.vm.UserVm;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface SignupService {
    public List<String> validationUser(UserDto userDto);
    public ResponseEntity<?> signupUser(UserDto userDto);

}

package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.vm.UserVm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserReportService {
    public List<UserDto> getusers();
    public UserDto selectuser(UserDto userDto);
}

package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.admin.dto.UserReportDto;
import ir.hamed.socialnetwork.models.admin.dto.UsersReportDto;
import ir.hamed.socialnetwork.models.dto.UserDto;

import java.util.List;

public interface UserReportService {
    public List<UsersReportDto> getusers();
    public UserReportDto selectuser(UserReportDto userReportDto);
}

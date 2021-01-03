package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.UserReportDto;
import ir.hamed.socialnetwork.models.admin.dto.UsersReportDto;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReportServiceMysqlImpl implements UserReportService {
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;

    UserMapperImpl userMapper = new UserMapperImpl();

    @Override
    public List<UsersReportDto> getusers() {
        List<UserMysql> users = (List<UserMysql>) userMysqlRepository.findAll();
        List<UsersReportDto> usersReportDto = mapListEntityToDtu(users);
        return usersReportDto;
    }

    @Override
    public UserReportDto selectuser(UserReportDto userReportDto) {
        UserMysql user = userMysqlRepository.findByUsername(userReportDto.getUsername()).orElseThrow(() -> new RuntimeException("Error: user is not found."));
        UserReportDto resultUserReportDto = completeUserReport(user);
        return resultUserReportDto;
    }

    private List<UsersReportDto> mapListEntityToDtu(List<UserMysql> users){
        List<UsersReportDto> usersReportDto = userMapper.listUserMysqlToUsersReportDto(users);
        return usersReportDto;
    }
    private UserReportDto completeUserReport(UserMysql user){
        UserReportDto resultUserReportDto = userMapper.userMysqlToUserReportDto(user);
        resultUserReportDto.setNumberOfPost(String.valueOf(user.getPosts().size()));
        resultUserReportDto.setNumberOfFollowing(String.valueOf(user.getFollowings().size()));
        return resultUserReportDto;
    }
}

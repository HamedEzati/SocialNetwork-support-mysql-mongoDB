package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.UserReportDto;
import ir.hamed.socialnetwork.models.admin.dto.UsersReportDto;
import ir.hamed.socialnetwork.models.dto.UserDto;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReportServiceMongoImpl implements UserReportService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;

    UserMapperImpl userMapper = new UserMapperImpl();

    @Override
    public List<UsersReportDto> getusers() {
        List<User> users = userMongoRepository.findAll();
        List<UsersReportDto> usersReportDto = mapListEntityToDtu(users);
        return usersReportDto;
    }

    @Override
    public UserReportDto selectuser(UserReportDto userReportDto) {
        User user = userMongoRepository.findByUsername(userReportDto.getUsername()).orElseThrow(() -> new RuntimeException("Error: user is not found."));
        UserReportDto resultUserReportDto = completeUserReport(user);
        return resultUserReportDto;
    }

    private List<UsersReportDto> mapListEntityToDtu(List<User> users){
        List<UsersReportDto> usersReportDto = userMapper.listUserMongoToUsersReportDto(users);
        return usersReportDto;
    }
    private UserReportDto completeUserReport(User user){
        UserReportDto resultUserReportDto = userMapper.userMongoToUserReportDto(user);
        resultUserReportDto.setNumberOfPost(String.valueOf(user.getPosts().size()));
        resultUserReportDto.setNumberOfFollowing(String.valueOf(user.getFollowings().size()));
        return resultUserReportDto;
    }
}

package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.mapper.UserMapperImpl;
import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReportServiceMongoImpl implements UserReportService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;

    UserMapperImpl userMapper = new UserMapperImpl();

    @Override
    public List<UserDto> getusers() {
        List<User> users = userMongoRepository.findAll();
        List<UserDto> usersDto = mapListEntityToDtu(users);
        return usersDto;
    }

    @Override
    public UserDto selectuser(UserDto userDto) {
        User user = userMongoRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new RuntimeException("Error: user is not found."));
        UserDto resultUserDto = userMapper.userMongoToUserDto(user);
        return resultUserDto;
    }

    private List<UserDto> mapListEntityToDtu(List<User> users){
        List<UserDto> usersDto = userMapper.listUserMongoToListDto(users);
        return usersDto;
    }
}

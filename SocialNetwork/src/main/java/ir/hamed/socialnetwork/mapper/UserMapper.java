package ir.hamed.socialnetwork.mapper;

import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.models.vm.UserVm;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Mappings({
            @Mapping(target="username", source="vm.username"),
            @Mapping(target="email", source="vm.email"),
            @Mapping(target="password", source="vm.password")
    })
    UserDto UserVmToUserDto(UserVm vm);
    @Mappings({
            @Mapping(target="username", source="dto.username"),
            @Mapping(target="email", source="dto.email"),
            @Mapping(target="password", source="dto.password")
    })
    UserMysql userDtoToUserMysql(UserDto dto);
    @Mappings({
            @Mapping(target="username", source="dto.username"),
            @Mapping(target="email", source="dto.email"),
            @Mapping(target="password", source="dto.password")
    })
    User userDtoToUserMongo(UserDto dto);

    @Named(value = "listUserMongoToUserDto")
    UserDto userMongoToUserDto(User user);
    @IterableMapping(qualifiedByName = "listUserMongoToUserDto")
    @Named(value = "mapListUserMongoToUserDto")
    List<UserDto> listUserMongoToListDto(List<User> users);

    @Named(value = "listUserDtoToUserVm")
    UserVm userDtoToUserVm(UserDto dto);
    @IterableMapping(qualifiedByName = "listUserDtoToUserVm")
    @Named(value = "mapListUserDtoToUserVm")
    List<UserVm> listUserDtoToUserVm(List<UserDto> dto);

}

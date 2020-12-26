package ir.hamed.socialnetwork.mapper;

import ir.hamed.socialnetwork.models.dtu.PostDto;
import ir.hamed.socialnetwork.models.dtu.UserDto;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.models.vm.PostVm;
import ir.hamed.socialnetwork.models.vm.UserVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface PostMapper {
    @Mappings({
            @Mapping(target="title", source="vm.title"),
            @Mapping(target="description", source="vm.description")
    })
    PostDto PostVmToPostDto(PostVm vm);
    @Mappings({
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="description", source="dto.description")
    })
    PostMysql postDtoToPostMysql(PostDto dto);

}

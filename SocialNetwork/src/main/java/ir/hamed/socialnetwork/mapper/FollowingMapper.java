package ir.hamed.socialnetwork.mapper;

import ir.hamed.socialnetwork.models.dtu.FollowingDto;
import ir.hamed.socialnetwork.models.dtu.PostDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mysql.FollowingMysql;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.models.vm.FollowingVm;
import ir.hamed.socialnetwork.models.vm.PostVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface FollowingMapper {
    @Mappings({
            @Mapping(target="username", source="vm.username")
    })
    FollowingDto followingVmToFollowingDto(FollowingVm vm);
    @Mappings({
            @Mapping(target="username", source="dto.username")
    })
    FollowingMysql followingDtoToFollowingMysql(FollowingDto dto);
}

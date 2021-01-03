package ir.hamed.socialnetwork.mapper;

import ir.hamed.socialnetwork.models.admin.dto.FollowingsReportDto;
import ir.hamed.socialnetwork.models.admin.vm.FollowingsReportVm;
import ir.hamed.socialnetwork.models.dto.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mysql.FollowingMysql;
import ir.hamed.socialnetwork.models.vm.FollowingVm;
import org.mapstruct.*;

import java.util.List;

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

    @Named(value = "followingsReportDtoToFollowingReportVm")
    FollowingsReportVm followingsReportDtoToFollowingsReportVm(FollowingsReportDto followingsReportDto);
    @IterableMapping(qualifiedByName = "followingsReportDtoToFollowingReportVm")
    @Named(value = "listFollowingsReportDtoToFollowingReportVm")
    List<FollowingsReportVm> listFollowingsReportDtoToFollowingsReportVm(List<FollowingsReportDto> followingsReportDto);
}

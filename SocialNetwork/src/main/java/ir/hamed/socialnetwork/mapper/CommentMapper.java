package ir.hamed.socialnetwork.mapper;

import ir.hamed.socialnetwork.models.dto.CommentDto;
import ir.hamed.socialnetwork.models.entity.mysql.CommentMysql;
import ir.hamed.socialnetwork.models.vm.CommentVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CommentMapper {
    @Mappings({
            @Mapping(target="postId", source="vm.postId"),
            @Mapping(target="text", source="vm.text")
    })
    CommentDto commentVmToCommentDto(CommentVm vm);
    @Mappings({
            @Mapping(target="postId", source="dto.postId"),
            @Mapping(target="text", source="dto.text")
    })
    CommentMysql commentDtoToCommentMysql(CommentDto dto);
}

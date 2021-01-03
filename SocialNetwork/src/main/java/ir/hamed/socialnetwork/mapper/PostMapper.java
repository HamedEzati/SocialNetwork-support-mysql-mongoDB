package ir.hamed.socialnetwork.mapper;

import ir.hamed.socialnetwork.models.admin.dto.PostReportDto;
import ir.hamed.socialnetwork.models.admin.dto.PostsReportDto;
import ir.hamed.socialnetwork.models.admin.vm.PostReportVm;
import ir.hamed.socialnetwork.models.admin.vm.PostsReportVm;
import ir.hamed.socialnetwork.models.dto.PostDto;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.models.vm.PostVm;
import javafx.geometry.Pos;
import org.mapstruct.*;

import java.util.List;

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

    @Named(value = "listPostDtoToPostVm")
    PostVm postDtoToPostVm(PostDto postDto);
    @IterableMapping(qualifiedByName = "listPostDtoToPostVm")
    @Named(value = "mapListPostDtoToPostVm")
    List<PostVm> listPostDtoToPostVm(List<PostDto> dto);

    @Named(value = "listPostMongoToPostDto")
    PostDto postMongoToPostDto(Post post);
    @IterableMapping(qualifiedByName = "listPostMongoToPostDto")
    @Named(value = "mapListPostMongoToPostDto")
    List<PostDto> listPostMongoToPostDto(List<Post> post);

    @Named(value = "listPostMysqlToPostDto")
    PostsReportDto postMysqlToPostDto(PostMysql post);
    @IterableMapping(qualifiedByName = "listPostMysqlToPostDto")
    @Named(value = "mapListPostMysqlToPostDto")
    List<PostsReportDto> listPostMysqlToPostsReportDto(List<PostMysql> post);

    @Named(value = "postMongoToPostsReportDto")
    PostsReportDto postMongoToPostsReportDto(Post post);
    @IterableMapping(qualifiedByName = "postMongoToPostsReportDto")
    @Named(value = "listPostMongoToPostsReportDto")
    List<PostsReportDto> listPostMongoToPostsReportDto(List<Post> post);

    @Named(value = "postsReportDtoToPostsReportVm")
    PostsReportVm postsReportDtoToPostsReportVm(PostsReportDto postsReportDto);
    @IterableMapping(qualifiedByName = "postsReportDtoToPostsReportVm")
    @Named(value = "listPostsReportDtoToPostsReportVm")
    List<PostsReportVm> listPostsReportDtoToPostsReportVm(List<PostsReportDto> postsReportDto);

    PostReportDto postMongoToPostReportDto(Post post);
    PostReportDto postMysqlToPostReportDto(PostMysql post);
    PostReportVm postReportDtoToPostReportVm(PostReportDto postReportDto);
    PostReportDto postReportVmToPostReportDto(PostReportVm postReportVm);
}

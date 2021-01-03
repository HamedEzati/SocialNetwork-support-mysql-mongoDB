package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.PostReportDto;
import ir.hamed.socialnetwork.models.admin.dto.PostsReportDto;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.PostMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import ir.hamed.socialnetwork.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostReportServiceMysqlImpl implements PostReportService {
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;

    PostMapperImpl postMapper = new PostMapperImpl();

    @Override
    public List<PostsReportDto> getposts() {
        return getPostsFromUsers();
    }

    @Override
    public PostReportDto selectpost(PostReportDto postReportDto) {
        return getPostFromUser(postReportDto);
    }

    private PostReportDto getPostFromUser(PostReportDto postReportDto){
        PostReportDto resultPost = null;
        List<UserMysql> users = (List<UserMysql>) userMysqlRepository.findAll();
        List<PostReportDto> posts = new ArrayList<>();
        for (UserMysql user:users){
            List<PostMysql> postsMysql = user.getPosts();
            for (PostMysql post:postsMysql){
                if (post.getId() == Long.valueOf(postReportDto.getId())){
                    resultPost = postMapper.postMysqlToPostReportDto(post);
                    resultPost.setUsername(user.getUsername());
                    resultPost.setNumberOfComment(String.valueOf(post.getComments().size()));
                }
            }
        }
        return resultPost;
    }

    private List<PostsReportDto> getPostsFromUsers(){
        List<UserMysql> users = (List<UserMysql>) userMysqlRepository.findAll();
        List<PostsReportDto> posts = new ArrayList<>();
        for (UserMysql user:users){
            List<PostMysql> postsMysql = user.getPosts();
            List<PostsReportDto> postsReportDto = postMapper.listPostMysqlToPostsReportDto(postsMysql);
            for (PostsReportDto post:postsReportDto){
                post.setUsername(user.getUsername());
                posts.add(post);
            }
        }
        return posts;
    }
}

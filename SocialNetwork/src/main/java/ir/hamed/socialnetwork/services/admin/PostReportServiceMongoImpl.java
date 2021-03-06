package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.PostReportDto;
import ir.hamed.socialnetwork.models.admin.dto.PostsReportDto;
import ir.hamed.socialnetwork.models.dto.PostDto;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostReportServiceMongoImpl implements PostReportService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;

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
        List<User> users = userMongoRepository.findAll();
        List<PostReportDto> posts = new ArrayList<>();
        for (User user:users){
            List<Post> postsMongo = user.getPosts();
            for (Post post:postsMongo){
                if (post.getId().equals(postReportDto.getId())){
                    resultPost = postMapper.postMongoToPostReportDto(post);
                    resultPost.setUsername(user.getUsername());
                    resultPost.setNumberOfComment(String.valueOf(post.getComments().size()));
                }
            }
        }
        return resultPost;
    }

    private List<PostsReportDto> getPostsFromUsers(){
        List<User> users = userMongoRepository.findAll();
        List<PostsReportDto> posts = new ArrayList<>();
        for (User user:users){
            List<Post> postsMongo = user.getPosts();
            List<PostsReportDto> postsReportDto = postMapper.listPostMongoToPostsReportDto(postsMongo);
            for (PostsReportDto post:postsReportDto){
                post.setUsername(user.getUsername());
                posts.add(post);
            }
        }
        return posts;
    }
}

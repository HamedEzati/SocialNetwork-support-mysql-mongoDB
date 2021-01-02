package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.mapper.PostMapperImpl;
import ir.hamed.socialnetwork.models.dtu.PostDto;
import ir.hamed.socialnetwork.models.dtu.UserDto;
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
    public List<PostDto> getposts() {
        return getPostsFromUsers();
    }

    @Override
    public PostDto selectpost(PostDto postDto) {
        return getPostFromUser(postDto);
    }

    private PostDto getPostFromUser(PostDto postDto){
        PostDto resultPost = null;
        List<User> users = userMongoRepository.findAll();
        List<PostDto> posts = new ArrayList<>();
        for (User user:users){
            List<Post> postsMongo = user.getPosts();
            for (Post post:postsMongo){
                if (post.getId().equals(postDto.getId())){
                    resultPost = postMapper.postMongoToPostDto(post);
                    resultPost.setUsername(user.getUsername());
                }
            }
        }
        return resultPost;
    }

    private List<PostDto> getPostsFromUsers(){
        List<User> users = userMongoRepository.findAll();
        List<PostDto> posts = new ArrayList<>();
        for (User user:users){
            List<Post> postsMongo = user.getPosts();
            List<PostDto> postsDto = postMapper.listPostMongoToPostDto(postsMongo);
            for (PostDto post:postsDto){
                post.setUsername(user.getUsername());
                posts.add(post);
            }
        }
        return posts;
    }
}

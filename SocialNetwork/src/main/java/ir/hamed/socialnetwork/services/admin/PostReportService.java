package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.dtu.PostDto;

import java.util.List;

public interface PostReportService {
    public List<PostDto> getposts();
    public PostDto selectpost(PostDto postDto);
}

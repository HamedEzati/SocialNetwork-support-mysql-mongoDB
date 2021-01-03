package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.admin.dto.PostReportDto;
import ir.hamed.socialnetwork.models.admin.dto.PostsReportDto;
import ir.hamed.socialnetwork.models.dto.PostDto;

import java.util.List;

public interface PostReportService {
    public List<PostsReportDto> getposts();
    public PostReportDto selectpost(PostReportDto postReportDto);
}

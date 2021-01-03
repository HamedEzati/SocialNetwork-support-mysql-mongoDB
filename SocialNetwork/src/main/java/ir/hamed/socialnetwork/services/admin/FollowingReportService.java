package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.admin.dto.FollowingsReportDto;
import ir.hamed.socialnetwork.models.dto.FollowingDto;

import java.util.List;

public interface FollowingReportService {
    public List<FollowingsReportDto> getfollowings();
}

package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.dtu.FollowingDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FollowingReportService {
    public List<FollowingDto> getfollowings();
}

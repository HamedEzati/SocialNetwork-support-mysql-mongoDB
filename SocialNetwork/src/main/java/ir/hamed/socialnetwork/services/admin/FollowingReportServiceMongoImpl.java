package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.admin.dto.FollowingsReportDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mongo.Post;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.services.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowingReportServiceMongoImpl implements FollowingReportService {
    @Autowired(required = false)
    UserMongoRepository userMongoRepository;

    @Override
    public List<FollowingsReportDto> getfollowings() {
        List<FollowingsReportDto> followingsReportDto = new ArrayList<>();
        List<User> users = userMongoRepository.findAll();
        for (User user:users){
            List<Following> followingsMongo = user.getFollowings();
            for (Following following:followingsMongo){
                FollowingsReportDto resultFollowing = new FollowingsReportDto(user.getUsername(),following.getUsername());
                followingsReportDto.add(resultFollowing);
            }
        }
        return followingsReportDto;
    }
}

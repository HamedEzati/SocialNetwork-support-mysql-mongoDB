package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.admin.dto.FollowingsReportDto;
import ir.hamed.socialnetwork.models.entity.mongo.Following;
import ir.hamed.socialnetwork.models.entity.mongo.User;
import ir.hamed.socialnetwork.models.entity.mysql.FollowingMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import ir.hamed.socialnetwork.repository.mongo.UserMongoRepository;
import ir.hamed.socialnetwork.repository.mysql.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowingReportServiceMysqlImpl implements FollowingReportService {
    @Autowired(required = false)
    UserMysqlRepository userMysqlRepository;

    @Override
    public List<FollowingsReportDto> getfollowings() {
        List<FollowingsReportDto> followingsReportDto = new ArrayList<>();
        List<UserMysql> users = (List<UserMysql>) userMysqlRepository.findAll();
        for (UserMysql user:users){
            List<FollowingMysql> followingsMysql = user.getFollowings();
            for (FollowingMysql following:followingsMysql){
                FollowingsReportDto resultFollowing = new FollowingsReportDto(user.getUsername(),following.getUsername());
                followingsReportDto.add(resultFollowing);
            }
        }
        return followingsReportDto;
    }
}

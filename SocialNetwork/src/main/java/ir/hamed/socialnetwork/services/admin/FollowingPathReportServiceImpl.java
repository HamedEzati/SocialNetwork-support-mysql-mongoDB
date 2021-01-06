package ir.hamed.socialnetwork.services.admin;

import ir.hamed.socialnetwork.models.admin.dto.FollowingPathReportDto;
import ir.hamed.socialnetwork.models.neo4j.FollowingNeo4j;
import ir.hamed.socialnetwork.repository.neo4j.FollowingNeo4jRepository;
import ir.hamed.socialnetwork.repository.neo4j.FollowingRepoImpl;
import ir.hamed.socialnetwork.repository.neo4j.PathModel;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.nio.file.Path;
import java.util.List;

@Service
public class FollowingPathReportServiceImpl implements FollowingPathReportService {

    @Autowired(required = false)
    FollowingNeo4jRepository followingNeo4jRepository;
    @Autowired
    FollowingRepoImpl followingRepo;

    @Override
    public FollowingPathReportDto getPath(FollowingPathReportDto followingPathReportDto) {
        PathModel pathModel = followingRepo.findShortestPath(followingPathReportDto.getFirstUsername(),followingPathReportDto.getSecondUsername());
        StringBuilder path = new StringBuilder();
        List<String> usernames = pathModel.getPathList();
        for(int i = 0;i<usernames.size();i++){
            if (i == usernames.size()-1){
                path.append(usernames.get(i));
                break;
            }
            path.append(usernames.get(i)+" =>");
        }
        followingPathReportDto.setPath(path.toString());
        return followingPathReportDto;
    }

}

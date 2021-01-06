package ir.hamed.socialnetwork.repository.neo4j;

import ir.hamed.socialnetwork.models.neo4j.FollowingNeo4j;

import java.util.List;

public interface FollowingRepo {
    PathModel findShortestPath(String firstPrimaryName, String secondPrimaryName);
}

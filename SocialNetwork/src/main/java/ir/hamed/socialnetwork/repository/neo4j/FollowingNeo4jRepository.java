package ir.hamed.socialnetwork.repository.neo4j;

import ir.hamed.socialnetwork.models.neo4j.FollowingNeo4j;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface FollowingNeo4jRepository extends Neo4jRepository<FollowingNeo4j,String> {
    Optional<FollowingNeo4j> findByUsername(String username);
//    @Query(value = "MATCH (start:following {username: 'ezzati2'}), (end:following {username: 'ezzati4'})\n" +
//            "MATCH p=shortestPath((start)-[:FOLLOWING*]->(end))\n" +
//            "RETURN p",nativeQuery = true)
//    Flux<FollowingNeo4j> PathByUsername ();
//@Query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r")
//void emptyData();
}

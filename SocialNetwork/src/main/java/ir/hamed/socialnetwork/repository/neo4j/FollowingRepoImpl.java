package ir.hamed.socialnetwork.repository.neo4j;

import com.google.common.collect.Lists;
import ir.hamed.socialnetwork.models.neo4j.FollowingNeo4j;


import ir.hamed.socialnetwork.repository.config.NeoConfigServer;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.model.Property;
import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.response.model.PropertyModel;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Repository
public class FollowingRepoImpl implements FollowingRepo {
    private final SessionFactory sessionFactory;

    //endregion

    //region Constructor

    public FollowingRepoImpl() {
        Configuration configuration = new Configuration.Builder().uri(NeoConfigServer.SERVER_URI).connectionPoolSize(1500).credentials(NeoConfigServer.SERVER_USERNAME, NeoConfigServer.SERVER_PASSWORD).build();
        sessionFactory = new SessionFactory(configuration, NeoConfigServer.DOMAIN_PACKAGE);
    }
    @Transactional
    @Override
    public PathModel findShortestPath(String firstPrimaryName, String secondPrimaryName) {
        Session session = sessionFactory.openSession();
        Map<String, Object> params = new HashMap<>(2);
        params.put("firstName", firstPrimaryName);
        params.put("secondName", secondPrimaryName);
//        String query1 = "MATCH (n { username:$firstName }) RETURN n";
//        String query2 = "MATCH path=shortestPath((start:following {username:$firstName})-[:FOLLOWING*]-(end:following {username:$secondName})) RETURN path";
        String query = "MATCH (start:following {username:$firstName}), (end:following {username:$secondName}) MATCH p=shortestPath((start)-[:FOLLOWING*]->(end)) RETURN nodes(p)";
        List<Map<String,Object>> lists = Lists.newArrayList(session.query(query, params));
        PathModel paths = new PathModel();
        for (Map pathList:lists){
            List<Object> valuesList = Lists.newArrayList(pathList.get("nodes(p)")) ;
            for (Object nodes:valuesList){
                List<NodeModel> nodesss = (List)nodes;
                for (NodeModel nodeVal:nodesss){
                    List<Property<String, Object>> username = nodeVal.getPropertyList();
                    paths.setNewPath(username.get(0).getValue().toString());
                }
            }
        }
        return paths;
    }
}

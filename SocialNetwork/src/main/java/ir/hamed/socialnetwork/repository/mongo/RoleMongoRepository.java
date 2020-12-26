package ir.hamed.socialnetwork.repository.mongo;

import java.util.Optional;


import ir.hamed.socialnetwork.models.entity.ERole;
import ir.hamed.socialnetwork.models.entity.mongo.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleMongoRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}

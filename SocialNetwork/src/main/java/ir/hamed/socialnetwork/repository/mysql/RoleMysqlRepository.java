package ir.hamed.socialnetwork.repository.mysql;

import ir.hamed.socialnetwork.models.entity.ERole;
import ir.hamed.socialnetwork.models.entity.mongo.Role;
import ir.hamed.socialnetwork.models.entity.mysql.RoleMysql;
import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleMysqlRepository extends CrudRepository<RoleMysql,Long> {
    Optional<RoleMysql> findByName(ERole name);
}

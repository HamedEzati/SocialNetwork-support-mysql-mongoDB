package ir.hamed.socialnetwork.repository.mysql;


import ir.hamed.socialnetwork.models.entity.mysql.UserMysql;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserMysqlRepository extends CrudRepository<UserMysql,Long> {

    Optional<UserMysql> findByEmail(String email);

    Optional<UserMysql> findByUsernameOrEmail(String username, String email);

    List<UserMysql> findByIdIn(List<Long> userIds);

    Optional<UserMysql> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}

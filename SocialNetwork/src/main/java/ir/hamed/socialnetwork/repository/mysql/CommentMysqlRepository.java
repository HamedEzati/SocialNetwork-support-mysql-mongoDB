package ir.hamed.socialnetwork.repository.mysql;

import ir.hamed.socialnetwork.models.entity.mysql.CommentMysql;
import org.springframework.data.repository.CrudRepository;

public interface CommentMysqlRepository extends CrudRepository<CommentMysql,Long> {
}

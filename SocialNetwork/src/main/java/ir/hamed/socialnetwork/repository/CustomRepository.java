package ir.hamed.socialnetwork.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomRepository<B,C> extends CrudRepository<B,C> {
    @Query("SELECT password FROM usersmysql WHERE username=?1")
    Iterable<B> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

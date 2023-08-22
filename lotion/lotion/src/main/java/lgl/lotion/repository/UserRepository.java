package lgl.lotion.repository;

import lgl.lotion.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //User save(User user);

    List<User> findByEMAIL(String email);
}

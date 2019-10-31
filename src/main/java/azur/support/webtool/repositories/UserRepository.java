package azur.support.webtool.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azur.support.webtool.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByName(String name);
    
}

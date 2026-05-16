package com.gustavo.login_api.repository;

import com.gustavo.login_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    //con optional evito el nullpointer y que pueda estar o no en la tabla

}

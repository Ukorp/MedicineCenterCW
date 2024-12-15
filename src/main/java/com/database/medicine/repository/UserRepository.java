package com.database.medicine.repository;


import com.database.medicine.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByFirstName(String firstName);

    Optional<User> findByEmail(String Email);
}

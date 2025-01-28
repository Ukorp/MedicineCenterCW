package com.database.medicine.repository;


import com.database.medicine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFirstName(String firstName);

    Optional<User> findByEmail(String Email);

    @Transactional
    @Modifying
    @Query(value = "update user_data set role = 'ADMIN' where id = ?1", nativeQuery = true)
    void changeToAdmin(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "update user_data set role = 'USER' where id = ?1", nativeQuery = true)
    void changeToUser(Integer userId);
}

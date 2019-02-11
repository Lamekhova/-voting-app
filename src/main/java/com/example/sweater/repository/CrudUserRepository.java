package com.example.sweater.repository;

import com.example.sweater.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    @Transactional
    int removeById(int id);
}

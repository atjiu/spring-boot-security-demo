package com.example.demo.module.user.repository;

import com.example.demo.module.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tomoya at 12/26/17
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findById(int id);

  Page<User> findByBlock(boolean block, Pageable pageable);

  User findByUsername(String username);

  void deleteById(int id);

}

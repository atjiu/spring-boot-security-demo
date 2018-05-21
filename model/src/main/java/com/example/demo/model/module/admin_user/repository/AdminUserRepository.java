package com.example.demo.model.module.admin_user.repository;

import com.example.demo.model.module.admin_user.model.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tomoya at 12/26/17
 */
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

  AdminUser findById(int id);

  Page<AdminUser> findByBlock(boolean block, Pageable pageable);

  AdminUser findByUsername(String username);

  void deleteById(int id);

}

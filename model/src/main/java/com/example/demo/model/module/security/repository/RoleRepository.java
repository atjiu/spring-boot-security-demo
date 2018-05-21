package com.example.demo.model.module.security.repository;

import com.example.demo.model.module.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tomoya at 12/26/17
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Role findByName(String name);

  Role findById(int id);

  List<Role> findAll();

  void delete(Role role);
}

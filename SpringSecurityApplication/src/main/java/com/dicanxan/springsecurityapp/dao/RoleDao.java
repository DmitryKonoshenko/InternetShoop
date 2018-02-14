package com.dicanxan.springsecurityapp.dao;


import com.dicanxan.springsecurityapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}

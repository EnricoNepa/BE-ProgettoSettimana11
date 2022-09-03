package com.catalogo.repository.security;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.model.security.Role;
import com.catalogo.model.security.Roles;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}

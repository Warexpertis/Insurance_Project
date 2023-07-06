package com.wrxprts.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wrxprts.ims.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
	Role findByEmail(String email);
}

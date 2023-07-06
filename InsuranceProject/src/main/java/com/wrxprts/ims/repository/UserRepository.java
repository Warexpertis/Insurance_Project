package com.wrxprts.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wrxprts.ims.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findByTc(String tc);
}

package com.adminService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminService.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
	
	public Optional<Admin> findByAdminName(String adminName);

}

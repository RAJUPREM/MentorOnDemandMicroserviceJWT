package com.adminService.service;

import java.util.List;

import com.adminService.entity.Admin;



public interface AdminService {
	
	public List<Admin> getAllAdmin();
	
	public Admin getAdminById(int adminId);
	
	public Admin saveAdmin(Admin admin);
	
	public Admin updateAdminByAdminId( Admin admin, int adminId);
	
	public Admin deleteAdminByAdminId(int adminId);

}

package com.adminService.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adminService.entity.Admin;
import com.adminService.repository.AdminRepository;
import com.adminService.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> ladmin=adminRepository.findAll();
		return ladmin;
	}
	
	@Override
	public Admin getAdminById(int adminId) {
		Optional<Admin> tempAdmin=adminRepository.findById(adminId);
		Admin admin=tempAdmin.get();
		return admin;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pass", admin.getAdminPassword());
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:4444/jwt/getPass/{pass}", String.class,params);
		System.out.println(s);
		admin.setAdminPassword(s);
		//admin.setAdminPassword(new BCryptPasswordEncoder().encode(admin.getAdminPassword()));
		adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin updateAdminByAdminId(Admin admin, int adminId) {
		Optional<Admin> tempAdmin=adminRepository.findById(adminId);
		Admin newAdmin=tempAdmin.get();
		newAdmin.setAdminName(admin.getAdminName());
		Map<String, String> params = new HashMap<String, String>();
		params.put("pass", admin.getAdminPassword());
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:4444/jwt/getPass/{pass}", String.class,params);
		admin.setAdminPassword(s);
		newAdmin.setAdminPassword(admin.getAdminPassword());
		adminRepository.save(newAdmin);
		return newAdmin;
	}

	@Override
	public Admin deleteAdminByAdminId(int adminId) {
		adminRepository.deleteById(adminId);
		return null;
	}

}

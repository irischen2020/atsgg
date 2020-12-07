package com.atsgg.service.api;

import com.atsgg.entity.Admin;

import java.util.List;

public interface AdminService {
	void saveAdmin(Admin admin);
	
	List<Admin> getAll();
	
	
	Admin getAdminByAcct(String loginAcct, String userPswd);
}

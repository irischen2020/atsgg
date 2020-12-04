package com.atsgg.service.impl;

import com.atsgg.entity.Admin;
import com.atsgg.entity.AdminExample;
import com.atsgg.mapper.AdminMapper;
import com.atsgg.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	
	@Override
	public void saveAdmin(Admin admin) {
		
		adminMapper.insert(admin);
		
	}
	
	@Override
	public List<Admin> getAll() {
		return adminMapper.selectByExample(new AdminExample());
	}
}

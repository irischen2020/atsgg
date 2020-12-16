package com.atsgg.service.impl;

import com.atsgg.entity.Admin;
import com.atsgg.entity.AdminExample;
import com.atsgg.exception.LoginFailedException;
import com.atsgg.mapper.AdminMapper;
import com.atsgg.service.api.AdminService;
import com.atsgg.util.CrowdConstant;
import com.atsgg.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.atsgg.util.CrowdUtil.md5;

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
	
	@Override
	public Admin getAdminByAcct(String loginAcct, String userPswd) {
		// 1、根据登录账号查询Admin对象
			// 先创建一个adminExample对象
		AdminExample adminExample = new AdminExample();
			// 创建criteria对象
		AdminExample.Criteria criteria = adminExample.createCriteria();
			// 封装查询条件
		criteria.andLoginAcctEqualTo(loginAcct);
			// 调用AdminMapper的方法执行查询
		List<Admin> list = adminMapper.selectByExample(adminExample);
		// 2、判断Admin对象是否为空
		if(list == null || list.size() == 0){
			// 3、若为空，则抛出LoginFailedException
			throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
		}
		if(list.size() > 1){
			throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
		}
			// 4、若不为空，则将数据库密码从Admin对象中取出
			Admin admin = list.get(0);
			String dbPwd = admin.getUserPswd();
		
			// 5、将表单提交的明文密码进行加密
			String uPwd = CrowdUtil.md5(userPswd);
			// 6、对密码进行比较
			if(!Objects.equals(uPwd,dbPwd)){
				// 7、如果比较结果不一致，则抛出异常
				throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
			}
		
			// 8、如果一致则返回Admin对象
			return admin;
	}
	
	
}

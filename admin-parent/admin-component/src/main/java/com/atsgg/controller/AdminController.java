package com.atsgg.controller;

import com.atsgg.entity.Admin;
import com.atsgg.service.api.AdminService;
import com.atsgg.util.CrowdConstant;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("admin/do/login.html")
	public String doLogin(
			@RequestParam("loginAcct") String loginAcct,
			@RequestParam("userPswd")  String userPswd,
			HttpSession session
						 )
	
	{
		// 调用Service方法执行登录检查。这个方法如果能够返回admin对象说明登录成功，如果账号，密码不正确则会抛出异常
		
		Admin admin = adminService.getAdminByAcct(loginAcct,userPswd);
		
		// 将登录成功返回的admin对象存入Session域
		
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
		
		return "redirect:/admin/to/main/page.html";
	}
	
	//登出
	@RequestMapping("admin/do/logout.html")
	public String doLogout(HttpSession session){
		session.invalidate();
		return "redirect:/admin/to/login/page.html";
	}
	
	//用户维护页面：分页
	@RequestMapping("admin/get/page.html")
	public String getPageInfo(@RequestParam(value="keyword",defaultValue = "") String keyword,
							  @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
							  @RequestParam(value="pageSize",defaultValue = "5") Integer pageSize,
							  ModelMap modelMap)
	{

		PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword,pageNum,pageSize);
		
		System.out.println("===================================");
		System.out.println(pageInfo);

		modelMap.addAttribute("pageInfo",pageInfo);

		return "admin-page";

	}
	
	//删除用户
	@RequestMapping("admin/remove/{adminId}/{pageNum}/{keyword}.html")
	public String remove(@PathVariable("adminId") Integer adminId,
						 @PathVariable("pageNum") Integer pageNum,
						 @PathVariable("keyword") String keyword)
 {
		//执行删除
		adminService.remove(adminId);
		//页面跳转，回到分页页面,尝试方案1：直接转发到admin-page,jsp会无法显示分页数据
//		return "admin-page";
		//尝试方案2：转发到admin/get/page.html地址，一旦页面刷新会重复执行删除，浪费性能
//		return "forward:admin/get/page.html";
		//尝试方案3：重定向到/admin/get/page.html. 重定向，前面一定要加/
		return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
	}
	

}

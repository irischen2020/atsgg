package com.atsgg.controller;

import com.atsgg.entity.Admin;
import com.atsgg.entity.Student;
import com.atsgg.service.api.AdminService;
import com.atsgg.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestHandler {
	
	private static Logger logger = LoggerFactory.getLogger(TestHandler.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/test/ssm.html")
	public String testSsm(Model model){
		List<Admin> adminList = adminService.getAll();
		
		model.addAttribute("adminList",adminList);
		
		return "target";
	}
	
	@ResponseBody
	@RequestMapping("/send/array.html")
	public String testReceiveArrayOne(@RequestBody List<Integer> array){
		
		for (Integer number:array){
			System.out.println("number="+number);
		}
		return "success";
	}
	
//	@ResponseBody
//	@RequestMapping("/send/array2.html")
//	public String testTwo(@RequestBody Student student){
//
//		logger.info(student.toString());
//
//		return "success";
//	}
	
	@ResponseBody
	@RequestMapping("/send/array2.html")
	public ResultEntity<Student> testTwo(@RequestBody Student student){
		
		logger.info(student.toString());
		
		ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);
		
		return resultEntity;
	}
}

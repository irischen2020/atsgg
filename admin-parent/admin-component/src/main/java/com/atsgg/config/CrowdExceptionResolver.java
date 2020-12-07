package com.atsgg.config;

import com.atsgg.exception.LoginFailedException;
import com.atsgg.util.CrowdUtil;
import com.atsgg.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//ControllAdvice表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {
	
	//处理登录异常
	@ExceptionHandler(value = LoginFailedException.class)
	public ModelAndView resolveLoginFailedException(
			//实际捕获到的异常类型
			NullPointerException exception,
			//当前请求对象
			HttpServletRequest request,
			//当前响应对象
			HttpServletResponse response
	) throws IOException
	
	{
		String viewName = "admin-login";
		return resolveCommonException(exception,request,response,viewName);
	}
	
	//将一个具体的异常类型和一个方法关联起来。这是处理空指针异常
	@ExceptionHandler(value = NullPointerException.class)
	public ModelAndView resolveNullPointerException(
			//实际捕获到的异常类型
			NullPointerException exception,
			//当前请求对象
			HttpServletRequest request,
			//当前响应对象
			HttpServletResponse response
	) throws IOException
	
	{
		String viewName = "system-error";
		return resolveCommonException(exception,request,response,viewName);
	}
	
	//将一个具体的异常类型和一个方法关联起来。这是处理MATH异常
	@ExceptionHandler(value = ArithmeticException.class)
	public ModelAndView resolveMathException(
			//实际捕获到的异常类型
			ArithmeticException exception,
			//当前请求对象
			HttpServletRequest request,
			//当前响应对象
			HttpServletResponse response
	) throws IOException
	
	{
		String viewName = "system-error";
		return resolveCommonException(exception,request,response,viewName);
	}
	
	
	
	
	//把通用的代码抽取出来
	public static ModelAndView resolveCommonException(
			//实际捕获到的异常类型
			Exception exception,
			//当前请求对象
			HttpServletRequest request,
			//当前响应对象
			HttpServletResponse response,
			//返回的视图
			String returnView
	) throws IOException
	
	{
		
		//1、先判断请求的类型
		boolean judgeResult = CrowdUtil.judgeRequestType(request);
		
		//2、如果是AJAX请求
		if(judgeResult){
			
			//3、创建ResultEntity对象
			ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
			
			//4、创建Gson对象
			Gson gson = new Gson();
			
			//5、将ResultEntity对象转换为JSON字符串
			String json = gson.toJson(resultEntity);
			
			//6、将JSON字符串作为响应体返回给浏览器
			response.getWriter().write(json);
			
			//7、由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象进行返回
			return null;
		}
		//8、如果不是AJAX请求，则创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		
		//9、将Exception对象存入模型
		modelAndView.addObject("exception",exception);
		
		//10、返回对应的视图名称
		modelAndView.setViewName(returnView);
		
		//11、返回ModelAndView对象
		return modelAndView;
	}
}

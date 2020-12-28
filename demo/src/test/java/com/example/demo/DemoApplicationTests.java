//package com.example.demo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class DemoApplicationTests {
//
//
//	@Autowired
//	private RedisTemplate<Object,Object> redisTemplate;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	public void testRedis(){
//		// 1、获取用来操作string类型数据的valueOperations对象
//		ValueOperations<Object,Object> operations = redisTemplate.opsForValue();
//
//		// 2、借助ValueOperations对象存入数据
//		Object key = "good";
//		Object value = "sss";
//		operations.set(key,value);
//
//		// 3、尝试读取刚才设置的数据
//	}
//
//}


import com.atsgg.entity.Admin;
import com.atsgg.mapper.AdminMapper;
import com.atsgg.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//在类上标记必要的注解，Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class AtsggTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void testTx(){
		
		Admin admin = new Admin(null,"ss1204","123456","ccname","345@qq.com", "2020-12-03");
		adminService.saveAdmin(admin);
//		System.out.println("受影响的行数："+ count);
		
	}

	@Test
	public void testConnection() throws SQLException {

		Connection connection = dataSource.getConnection();
		System.out.println(connection);

	}

	@Test
	public void testMapper(){
		Admin admin = new Admin(null,"cc","123456","ccname","345@qq.com", "2020-12-03");
		int count = adminMapper.insert(admin);
		System.out.println("受影响的行数："+ count);

	}
	
	@Test
	public void testLog(){
		//1、获取Logger对象
		Logger logger = LoggerFactory.getLogger(AtsggTest.class);
		//根据不同日志级别打印日志
		logger.debug("hello iam debugg");
		logger.debug("hello iam debugg");
		logger.debug("hello iam debugg");
		
		logger.info("hello iam info");
		logger.info("hello iam info");
		logger.info("hello iam info");
		
		logger.warn("hello iam warn");
		logger.warn("hello iam warn");
		logger.warn("hello iam warn");
		
		logger.error("hello iam debugg");
		logger.error("hello iam debugg");
		logger.error("hello iam debugg");
		
		
	}
}

import com.atsgg.entity.Admin;
import com.atsgg.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

//在类上标记必要的注解，Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class AtsggTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AdminMapper adminMapper;
	
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
}

package Team4.Booksys;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatasourceTest {
	@Autowired
	private DataSource data;
	
	@Test
	public void testConnection() {
		try {
			Connection con = data.getConnection();
			System.out.println("-----------------------------------");
			System.out.println("연결 성공");
			System.out.println("-----------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

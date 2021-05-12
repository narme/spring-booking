package Team4.mariadb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

public class connectionTest {
	public void viewtest() {
			System.out.println("오라클 공용 mariadb 연결 테스트...");
			try {
				Statement stmt = Database.getInstance().getConnection().createStatement();
				System.out.println("오라클 공용 mariadb 연결 성공");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
}



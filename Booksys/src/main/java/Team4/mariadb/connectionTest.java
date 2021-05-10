package Team4.mariadb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

public class connectionTest {
	public void viewtest() {
		Vector v = new Vector();
		try {
			System.out.println("show list of `Table`");
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM `Table`") ;
		
			while (rset.next()) {
				v.addElement(new Integer(rset.getInt(1))) ;
			      }
			Iterator iter = v.iterator();
			while(iter.hasNext()){
			    System.out.println("table id = "+iter.next());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

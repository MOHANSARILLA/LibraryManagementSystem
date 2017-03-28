import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class SearchServlet extends GenericServlet {
	Connection con;

	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void service(ServletRequest req, ServletResponse res) {
		try {
			String s1 = req.getParameter("bname");
			PreparedStatement p = con.prepareStatement("select * from books where bname=?");
			p.setString(1, s1);
			ResultSet rs = p.executeQuery();
			PrintWriter pw = res.getWriter();
			if (rs.next())
				pw.println("Book is found successfully");
			else
				pw.println("Book currently not available");

		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
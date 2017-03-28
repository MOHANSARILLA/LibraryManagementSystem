import java.sql.*;
import javax.servlet.*;
import java.io.*;

public class Returning extends GenericServlet {
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

			String st1 = req.getParameter("lid");
			String st2 = req.getParameter("lbname");
			String st3 = req.getParameter("doi");
			String st4 = req.getParameter("doe");

			int n = Integer.parseInt(st1);
			PreparedStatement p = con.prepareStatement("delete from issue where lid=? and lbname=?");
			p.setInt(1, n);
			p.setString(2, st2);
			p.executeUpdate();

			PrintWriter pw = res.getWriter();

			pw.println("<body bgcolor=lightcyan><center><h2>");

			pw.println("Data deleted successfully");

			pw.println("</h2></center></body>");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;

public class Lending extends GenericServlet {
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
			String st3 = req.getParameter("ldoi");
			String st4 = req.getParameter("ldoe");
			PreparedStatement p = con.prepareStatement("insert into issue values(?,?,?,?)");
			p.setString(1, st1);
			p.setString(2, st2);
			p.setString(3, st3);
			p.setString(4, st4);
			p.executeUpdate();

			PrintWriter pw = res.getWriter();

			pw.println("<body bgcolor=lightcyan><center><h2>");

			pw.println("Data inserted successfully");

			pw.println("</h2></center></body>");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class AddBooks extends GenericServlet
{
	Connection con;
	public void init()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		}catch(Exception e){
			System.err.println(e);
		}
	}

	public void service(ServletRequest req,ServletResponse res)
	{
		try{
			String s1=req.getParameter("bname");
			String s2=req.getParameter("bcode");
			String s3=req.getParameter("bauthor");
			String s4=req.getParameter("bprice");
			String s6=req.getParameter("barrival");
			String s7=req.getParameter("brackno");
			String s8=req.getParameter("bscode");

			int p=Integer.parseInt(s4);
		

			PreparedStatement pstmt=con.prepareStatement("insert into books values(?,?,?,?,?,?,?)");
			
			pstmt.setString(1,s1);
			pstmt.setString(2,s2);
			pstmt.setString(3,s3);
			pstmt.setInt(4,p);
			pstmt.setString(5,s6);
			pstmt.setString(6,s7);
			pstmt.setString(7,s8);

			pstmt.executeUpdate();

			PrintWriter pw=res.getWriter();

			pw.println("<h1><center>Books are added Successfully</center></h1>");


		}catch(Exception e){
			System.err.println(e);
		}
	}
}
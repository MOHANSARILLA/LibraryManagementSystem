import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class AddMembers extends GenericServlet
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
			String s1=req.getParameter("mid");
			String s2=req.getParameter("mname");
			String s3=req.getParameter("maddress");
			String s4=req.getParameter("missue");
			String s5=req.getParameter("mexpiry");
			String s6=req.getParameter("mstatus");
			String s7=req.getParameter("mtype");
			String s8=req.getParameter("mamount");

			int id=Integer.parseInt(s1);
			int amount=Integer.parseInt(s8);

			PreparedStatement pstmt=con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?)");
			
			pstmt.setInt(1,id);
			pstmt.setString(2,s2);
			pstmt.setString(3,s3);
			pstmt.setString(4,s4);
			pstmt.setString(5,s5);
			pstmt.setString(6,s6);
			pstmt.setString(7,s7);
			pstmt.setInt(8,amount);

			pstmt.executeUpdate();

			PrintWriter pw=res.getWriter();

			pw.println("<h1><center>Member is added Successfully</center></h1>");


		}catch(Exception e){
			System.err.println(e);
		}
	}
}
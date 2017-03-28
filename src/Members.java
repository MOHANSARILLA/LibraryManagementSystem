import java.io.*;
import java.sql.*;
import javax.servlet.*;

public class Members extends GenericServlet
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
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from member");
				ResultSetMetaData rsmd=rs.getMetaData();
				int len=rsmd.getColumnCount();
				PrintWriter pw=res.getWriter();
				pw.println("<h1>Available members info is..</h1><br>");
				pw.println("<table border=3><tr>");
				for(int i=1;i<=len;i++)
				{
					pw.print("<th>"+rsmd.getColumnName(i));
				}
				pw.println("</tr>");
				while(rs.next())
				{
					pw.println("<tr>");
					pw.print("<td>"+rs.getInt(1)+
							 "<td>"+rs.getString(2)+
						"<td>"+rs.getString(3)+
						"<td>"+rs.getString(4)+
						"<td>"+rs.getString(5)+
						"<td>"+rs.getString(6)+
						"<td>"+rs.getString(7)+
						"<td>"+rs.getInt(8));
					pw.println("</tr>");
				}
				pw.println("</table>");
			}catch(Exception e){
			System.err.println(e);
		}
	}
}
import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
public class Del extends HttpServlet
{

public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");  
	PreparedStatement s=con.prepareStatement("delete from registeruser where name= ?");
	String q=req.getParameter("del");
	s.setString(1,q);
	int j=s.executeUpdate();
	PrintWriter out=res.getWriter();
		
	if(j>0)
	{
		res.setContentType("text/html");
		out.println("Deleted");
		

	}	
	Cookie[] cook=req.getCookies();
	for(int i=0;i<cook.length;i++){
	out.println(cook[i].getName());
    out.println(cook[i].getValue());
}
HttpSession h=req.getSession();
out.println(h.getAttribute("firstname"));
out.println(h.getAttribute("lastname"));

	s.close();
	out.close();
}
catch(Exception w)
{

}
}
}
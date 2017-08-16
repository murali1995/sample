import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class Show extends HttpServlet {  
public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
  try{
  	Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","system","manager");  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  


Statement pf=con.createStatement(); 

ResultSet rs=pf .executeQuery("Select * from registeruser");
 
             out.println("<table border=1>");
                 while(rs.next())
                 {
                     out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td>"+
                                      "<td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
                 }
             out.println("</table>");
             pf.close();
             rs.close();
}
catch(Exception e)
{

}
}
}
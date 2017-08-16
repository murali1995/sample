import java.io.IOException;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
* Servlet implementation class HelloWorld
*/
public class NewClass extends HttpServlet {
private static final long serialVersionUID = 1L;
/**
* Default constructor.
*/
public NewClass() {
}
/*
* This method will handle all GET request.
*/
protected void doGet(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
	try{
  System.out.println("Dei");
	response.setContentType("text/html");

    PrintWriter out = response.getWriter();
    String title = "Using GET Method to Read Form Data";
    String docType =
       "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
       
    out.println(docType +
       "<html>\n" +
          "<head><title>" + title + "</title></head>\n" +
          "<body bgcolor = \"#f0f0f0\">\n" +
             "<h1 align = \"center\">" + title + "</h1>\n" +
             "<ul>\n" +
                "  <li><b>First Name</b>: "
                + request.getParameter("first_name") + "\n" +
                "  <li><b>Last Name</b>: "
                + request.getParameter("last_name") + "\n" +
             "</ul>\n" +
          "</body></html>"
    );
    System.out.println(request.getParameter("first_name"));
    System.out.println(request.getParameter("last_name"));
    Class.forName("oracle.jdbc.driver.OracleDriver");  
    Connection con=DriverManager.getConnection(  
    "jdbc:oracle:thin:@localhost:1521:xe","system","manager");  
    String sta="select pass from registeruser where name=?";
    Statement pf=con.createStatement(); 

    ResultSet res=pf .executeQuery("Select * from registeruser");
 
             out.println("<table border=1>");
                 while(res.next())
                 {
                     out.println("<tr><td>"+res.getString(1)+"</td><td>"+res.getString(2)+"</td>"+
                                      "<td>"+res.getString(3)+"</td><td>"+res.getString(4)+"</td></tr>");
                 }
             out.println("</table>");
             pf.close();
             res.close();

    PreparedStatement st=con.prepareStatement("select pass from registeruser where name=?");
    st.setString(1,request.getParameter("first_name"));
    ResultSet rs=st.executeQuery(sta);

    String pass="";
    while(rs.next())
        pass=rs.getString(1);
    out.println("hi"+pass);
    if(request.getParameter("last_name").equals(pass))
    {
      out.println("validated user");
    }
  
    HttpSession s=request.getSession();
    String heading;
    s.setAttribute("firstname",request.getParameter("first_name"));
    s.setAttribute("lastname",request.getParameter("last_name"));
    Integer count=(Integer)s.getAttribute("accessCount");
    if(count==null)
    {
      count=0;
      heading="Welcome new user";
    }
    else
    {
      heading="welcome back";
      count+=1;
    }
    s.setAttribute("accessCount",count);
    
    out.println(heading+" "+s.getId()+" "+ new Date(s.getCreationTime())+" count="+count);

}

  catch(Exception w)

  
  {

  }
  }
/*
* This method will handle all POST request
*/
protected void doPost(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
  doGet(request,response);
}
}
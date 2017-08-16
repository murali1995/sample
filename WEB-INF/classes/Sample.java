import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
* Servlet implementation class HelloWorld
*/
public class Sample extends HttpServlet {
private static final long serialVersionUID = 1L;
/**
* Default constructor.
*/
public Sample() {
}
/*
* This method will handle all GET request.
*/
protected void doGet(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Hello World");
	HttpSession s=request.getSession();
	s.invalidate();
}
/*
* This method will handle all POST request
*/
protected void doPost(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
}
}
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UploadFile extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private final String UPLOAD_DIRECTORY = "C:/Users/RBS/Documents/server/apache-tomcat-7.0.78/webapps/sample/tmp";

        protected void doPost(HttpServletRequest request,HttpServletResponse response)  {
                try {
                    // Parse the request
                     boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // process only if its multipart content
        if (isMultipart) {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
       
                    List<FileItem> multiparts = upload.parseRequest(request);

                   for (FileItem item : multiparts) {
                   if (!item.isFormField()) {
                   String name = new File(item.getName()).getName();
                   item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                   }

                
                 PrintWriter r=response.getWriter();
                 r.println("uploaded");       
                // File uploaded successfully
                } 
            }
        }
                catch (Exception e) 
                {
                 request.setAttribute("message", "File Upload Failed due to " + e);
                }

        } 
    }

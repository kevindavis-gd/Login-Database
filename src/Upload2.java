import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
@MultipartConfig()
public class Upload2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
        try{
        	//get session information and store in a variable
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            //get data from form and store them
            String description = request.getParameter("description");
            //connect to local database
        	String url = "jdbc:mysql://localhost:3306/students";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);
            // input stream of the upload file
            InputStream inputStream = null; 
            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("file");
            inputStream = filePart.getInputStream();
            //update the file and file description cells with new values
            String sql = "UPDATE data SET file = (?), file_description = "
            		+ "(?) WHERE username = '"+username+"' ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBlob(1, inputStream);
            statement.setString(2, description);
            int row = statement.executeUpdate();
            //if atleast 1 row is returned from the execution, then it was a success
            if (row > 0) 	
            conn.close();
            //response.sendRedirect("welcome");
            
            //needs to work with request dispatcher for doPost
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome");
       	 	requestDispatcher.forward(request, response);
         }
        catch(Exception se) { se.printStackTrace(); }
    }
}
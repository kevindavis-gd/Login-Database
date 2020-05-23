import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      //get data from form and store them
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String name = request.getParameter("name");
        String field = request.getParameter("field");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String work = request.getParameter("work");
        String title = request.getParameter("title");
        try {
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //creating connection with the database 
            Connection con = DriverManager.getConnection
            		("jdbc:mysql://localhost:3306/students","root","root");
            //insert into username and password table
            PreparedStatement ps = con.prepareStatement("insert into credentials values(?,?)");
            ps.setString(1, username);
            ps.setString(2, pass);
            //insert into data table
            PreparedStatement py = con.prepareStatement 
            		("insert into data(username,name,field_of_study,address,phone_number,"
            				+ "working_organization,job_title) values(?,?,?,?,?,?,?)");
            py.setString(1, username);
            py.setString(2, name);
            py.setString(3, field);
            py.setString(4, address);
            py.setString(5, phone);
            py.setString(6, work);
            py.setString(7, title);
            int i = ps.executeUpdate();
            int j = py.executeUpdate();
            //if both tables have been updated successfully then go back to index
            if(i > 0 && j > 0) 
                response.sendRedirect("index.html");    
        }
        catch(Exception se) {se.printStackTrace();}
    }
}
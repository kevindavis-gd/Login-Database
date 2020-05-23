import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        	response.setContentType("text/html;charset=UTF-8");
        	//allows program to print to website
        	PrintWriter out = response.getWriter();
        	//get information from login form and store it in variables
        	String username = request.getParameter("username");
        	String pass = request.getParameter("pass");   
        try { // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //creating connection with the database 
            Connection con = DriverManager.getConnection
            		("jdbc:mysql://localhost:3306/students","root","root");
            Statement stmt=con.createStatement();
            String sql="Select * from credentials where username "
            		+ "='"+username+"' and password = '"+pass+"'";
            ResultSet rs=stmt.executeQuery(sql);
            //if the Sql Statement has been executed correctly create 
            //a session and call the welcome servlet
            if(rs.next()){ 
            	HttpSession session = request.getSession();
            	 session.setAttribute("username", username);
            	 session.setAttribute("password", pass);
            	 //needs to work with request dispatcher for doPost
            	 RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome");
            	 requestDispatcher.forward(request, response); 
            }else 
            {	//if incorrect password or username 
            	out.println("Incorrect username or password....");
            	out.println("<form action=\"index.html\" method=\"get\">");
        		out.println("<input type=\"submit\" value=\"Try Again\" />");
            }  
            con.close();
        }catch(Exception se) {se.printStackTrace();} 
    }
    
}
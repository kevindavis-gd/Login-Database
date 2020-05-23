import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
public class Welcome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        	response.setContentType("text/html;charset=UTF-8");
        	//allows program to print to website
        	PrintWriter out = response.getWriter();
        try {
        	//get session information and store it in a variable
        	 HttpSession session = request.getSession();
             String username = (String) session.getAttribute("username");
           //connect to local database
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection
            		 ("jdbc:mysql://localhost:3306/students","root","root");
             String sql2 = "SELECT * FROM data where username = '"+username+"' ";
             Statement stmt=con.createStatement();
             ResultSet data= stmt.executeQuery(sql2);

        	//***************************Display Data on Webpage************************
        	if (data.next()) {
        		
        		out.println("<html>");
        		out.println("<head>");
        		out.println("<meta charset=\"utf-8\">");
        		out.println("<meta name=\"viewport\" content=\"width=device-width,"
        				+ " initial-scale=1.0\">");
        		out.println("<title>Student Data</title>");
        		out.println(" <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/"
        				+ "twitter-bootstrap/4.1.3/css/bootstrap.min.css\">");
        		out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/"
        				+ "ionicons/2.0.1/css/ionicons.min.css\">");
        		
        		//*********************************CSS****************************
        		// Using bootstrap for design
        		out.println("<style>");
        		out.println(".login-clean \r\n" + 
        				"		{\r\n" + 
        				"		  background:#f1f7fc;\r\n" + 
        				"		  padding:80px 0;\r\n" + 
        				"		}");
        		out.println(".login-clean form \r\n" + 
        				"		{\r\n" + 
        				"		  max-width:320px;\r\n" + 
        				"		  width:90%;\r\n" + 
        				"		  margin:0 auto;\r\n" + 
        				"		  background-color:#ffffff;\r\n" + 
        				"		  padding:40px;\r\n" + 
        				"		  border-radius:4px;\r\n" + 
        				"		  color:#505e6c;\r\n" + 
        				"		  box-shadow:1px 1px 5px rgba(0,0,0,0.1);\r\n" + 
        				"		}");
        		out.println(".login-clean .illustration \r\n" + 
        				"		{\r\n" + 
        				"		  text-align:center;\r\n" + 
        				"		  padding:0 0 20px;\r\n" + 
        				"		  font-size:100px;\r\n" + 
        				"		  color:rgb(244,71,107);\r\n" + 
        				"		}");
        		out.println(".login-clean form .form-control \r\n" + 
        				"		{\r\n" + 
        				"		  background:#f7f9fc;\r\n" + 
        				"		  border:none;\r\n" + 
        				"		  border-bottom:1px solid #dfe7f1;\r\n" + 
        				"		  border-radius:0;\r\n" + 
        				"		  box-shadow:none;\r\n" + 
        				"		  outline:none;\r\n" + 
        				"		  color:inherit;\r\n" + 
        				"		  text-indent:8px;\r\n" + 
        				"		  height:42px;\r\n" + 
        				"		}");
        		out.println(".login-clean form .btn-primary \r\n" + 
        				"		{\r\n" + 
        				"		  background:#f4476b;\r\n" + 
        				"		  border:none;\r\n" + 
        				"		  border-radius:4px;\r\n" + 
        				"		  padding:11px;\r\n" + 
        				"		  box-shadow:none;\r\n" + 
        				"		  margin-top:26px;\r\n" + 
        				"		  text-shadow:none;\r\n" + 
        				"		  outline:none !important;\r\n" + 
        				"		}");
        		out.println(".login-clean form .btn-primary:hover, .login-clean form "
        				+ ".btn-primary:active \r\n" + 
        				"		{\r\n" + 
        				"		  background:#eb3b60;\r\n" + 
        				"		}");
        		out.println(".login-clean form .btn-primary:active \r\n" + 
        				"		{\r\n" + 
        				"		  transform:translateY(1px);\r\n" + 
        				"		}");
        		out.println(".login-clean form .forgot \r\n" + 
        				"		{\r\n" + 
        				"		  display:block;\r\n" + 
        				"		  text-align:center;\r\n" + 
        				"		  font-size:12px;\r\n" + 
        				"		  color:#6f7a85;\r\n" + 
        				"		  opacity:0.9;\r\n" + 
        				"		  text-decoration:none;\r\n" + 
        				"		}");
        		out.println(".login-clean form .forgot:hover, .login-clean form "
        				+ ".forgot:active \r\n" + 
        				"		{\r\n" + 
        				"		  opacity:1;\r\n" + 
        				"		  text-decoration:none;\r\n" + 
        				"		}");
        		out.println("</style>");
        		out.println("</head>");
        		out.println("<body>");
        		
        		//***********************User Information Printed******************************
        		
        		out.println("<div class=\"login-clean\">");
        		//logout form
        		out.println("<form action=\"logout\" method=\"post\">");
        		out.println("<input type=\"submit\" value=\"Logout\" />");
        		out.println("</form>");
        		//beginning of form that contains all data on this page
        		//this form will call the upload2 servlet
        		out.println("<form method=\"post\" action=\"upload2\" "
        				+ "enctype=\"multipart/form-data\">");
        		out.println("<div class=\"illustration\"><i "
        				+ "class=\"icon ion-ios-navigate\"></i></div>");
        		out.println("<div class=\"form-group\"> <b>Name: </b>" 
        				+ data.getString("name") + "</div>");
        		out.println("<div class=\"form-group\"> <b>Field of Study: </b>" 
        				+ data.getString("field_of_study") + "</div>");
        		out.println("<div class=\"form-group\"> <b>Address: </b>" 
        				+ data.getString("address") + "</div>");
        		out.println("<div class=\"form-group\"> <b>Phone Number: </b>" 
        				+ data.getString("phone_number") + "</div>");
        		out.println("<div class=\"form-group\"> <b>Working Organization: </b>" 
        				+ data.getString("working_organization") + "</div>");
        		out.println("<div class=\"form-group\"> <b>Job Title: </b>" 
        				+ data.getString("job_title") + "</div>");
        		
        		//***************************Select file ************************************
        		out.println("<div class=\"form-group\">");
        		//file input
        		out.println("<input type=\"file\" name =\"file\" class=\"form-control-file\">");
        		out.println("</div>");
        		//description input
        		out.println("<div class=\"form-group\"><input class=\"form-control\" "
        				+ "type=\"text\" name=\"description\" placeholder=\"File Description\"></div>");
        		out.println("<div class=\"form-group\"><button class=\"btn btn-primary btn-block\" "
        				+ "name=\"upload\" type=\"Upload\">Upload File</button></div>");
        		
        		//*************************** Shows if a file is stored*****************************
        		out.println("<b>File Stored: </b>");
        		try {
        			Statement data_stmt=con.createStatement();
        			ResultSet data_rs = data_stmt.executeQuery("SELECT * "
        					+ "FROM data where username = '"+username+"' ");
        			//if there is a file, print the description
        			if(data_rs.next()) {out.println(data_rs.getString(9));}
        			else {out.println("None");}
        		}
        		catch(Exception p){System.out.println(p);}
        		//end of form and rest of page
        		out.println("</form>");
        		out.println("</div> ");
        		out.println("</body>");
        		out.println("</html>");
        	}
        	con.close();
        }
        catch(Exception se) {
            se.printStackTrace();
        }
    }
}
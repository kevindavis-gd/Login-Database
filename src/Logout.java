import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	//destroy session
    	session.invalidate();
        response.sendRedirect("index.html");
    }
}
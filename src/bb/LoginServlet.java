package bb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String str = "";
		str = form();
		
		response.getWriter().append(str);
	}
	
	public String form() {
		String str = "<br/><br/>";
		str = str + "<form action='RedirectionServlet' method='post'>";
		str = str + "<table border='1px' width='" + (2 * 100) + "px' align='center'>";
		
		//name
		str = str + "<tr>";
		str = str + "<td>" + "name" + "</td>";
		str = str + "<td>";
		str = str + "<input name='name' type='text' value=''/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		str = str + "<br/>";
		
		//password
		str = str + "<tr>";
		str = str + "<td>" + "password" + "</td>";
		str = str + "<td>";
		str = str + "<input name='psw' type='password' value=''/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		str = str + "<br/>";
		
		//type
		str = str + "<tr>";
		str = str + "<td>" + "student" + "</td>";
		str = str + "<td>";
		str = str + "<input name='type' type='radio' value='student'/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		str = str + "<tr>";
		str = str + "<td>" + "teacher" + "</td>";
		str = str + "<td>";
		str = str + "<input name='type' type='radio' value='teacher'/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		str = str + "<tr>";
		str = str + "<td>" + "fmanager" + "</td>";
		str = str + "<td>";
		str = str + "<input name='type' type='radio' value='fmanager'/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		str = str + "<tr>";
		str = str + "<td>" + "cmanager" + "</td>";
		str = str + "<td>";
		str = str + "<input name='type' type='radio' value='cmanager'/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		str = str + "<br/>";
		
		//submit
		str = str + "<tr>";
		str = str + "<td>" + "log in" + "</td>";
		str = str + "<td>";
		str = str + "<input type='submit' value='update'/>";
		str = str + "</td>";
		str = str + "</tr>";
		
		
		str = str + "</table>";
		str = str + "</form>";
		
		return str;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

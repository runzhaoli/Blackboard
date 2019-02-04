package bb;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.DBProcess;
import aa.TableProcess;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("welcome to student!!!");
		String table = "user";
		String name = request.getParameter("name").trim();
		String psw = request.getParameter("psw").trim();
		
		List<List<String>> list = DBProcess.read(table);
		List<String> str = null;
		for (int i = 0; i < list.size(); i++) {
			List<String> info = list.get(i);
			if(info.get(1).equals(name) && info.get(4).equals(psw)) {
				str = info;
				break;
			}
		}
		
		if(str != null) {
			for (int i = 0; i < str.size(); i++) {
				System.out.println(str.get(i) + "))))))))))))))");
			}
			
			String info = "";
			info = info + "<h1>hello " + name + "</h1>";
			info = info + getInfo();
			response.getWriter().append(info);
		}else {
			
		}
		
		
	}
	
	public String getInfo() {
		String table = "";
		String str = "";
		List<List<String>> list = null;
		
		table = "course";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		
		table = "register_course";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		
		//
		table = "assignment";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		
		table = "submit_assignment";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		
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

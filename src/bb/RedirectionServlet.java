package bb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.DBProcess;
import cc.User;

/**
 * Servlet implementation class RedirectionServlet
 */
@WebServlet("/RedirectionServlet")
public class RedirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirectionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String table = "user";
		String name = request.getParameter("name").trim();
		String psw = request.getParameter("psw").trim();

		List<List<String>> list = DBProcess.read(table);
		List<String> str = null;
		for (int i = 0; i < list.size(); i++) {
			List<String> info = list.get(i);
			if (info.get(1).equals(name) && info.get(4).equals(psw)) {
				str = info;
				break;
			}
		}

		if (str != null) {

			//
			for (int i = 0; i < str.size(); i++) {
				System.out.println(str.get(i));
			}
			User u = new User();
			u.setId(Integer.parseInt(str.get(0)));
			u.setString(name);
			//

			// response.getWriter().append("Hello " + u.toString() + "is going to your
			// page");

			String type = request.getParameter("type");
			if (type.equals("student")) {
				System.out.println("student");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentServlet");
				dispatcher.forward(request, response);
			} else if (type.equals("teacher")) {
				System.out.println("teacher");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/TeacherServlet");
				dispatcher.forward(request, response);
			} else if (type.equals("fmanager")) {
				System.out.println("fmanager");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/FManagerServlet");
				dispatcher.forward(request, response);
			} else if (type.equals("cmanager")) {
				System.out.println("cmanager");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/CManagerServlet");
				dispatcher.forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

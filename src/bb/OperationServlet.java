package bb;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.DBProcess;

/**
 * Servlet implementation class OperationServlet
 */
@WebServlet("/OperationServlet")
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		
		int flag = Integer.parseInt(type);
		if(flag == 1) {
			String table = request.getParameter("name");
			String number = request.getParameter("id");
			
			List<List<String>> list = null;
			list = DBProcess.read(table);
			List<String> info = list.get(Integer.parseInt(number));
			for (int i = 0; i < info.size(); i++) {
				System.out.println(info.get(i) + ")))))))))))))))))))");
			}
			
			DBProcess.delete(table, info.get(0), info.get(1));
		}else if(flag == 2) {
			
		}
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
//		String table = "";
		List<String> list = new LinkedList<>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String str = request.getParameter(name);
            list.add(str);
        }
		
		String[] info = new String[list.size()];
		for (int i = 1; i < list.size(); i++) {
			info[i - 1] = list.get(i);
			System.out.println(list.get(i) + ")))))))))))))))))))");
		}
		
		String table = list.get(0).trim();
		DBProcess.delete(table, info[0], info[1]);
		DBProcess.write(table, info);
		
	}

}

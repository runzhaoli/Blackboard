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
 * Servlet implementation class TesterServlet
 */
@WebServlet("/TesterServlet")
public class TesterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DBProcess.arrayTOstr(list)
		String table = "";
		String str = "";
		List<List<String>> list = null;
		//
		
		//
		table = "user";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		//
		table = "course";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		//
		table = "register_course";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		//
		table = "assignment";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		//
		table = "submit_assignment";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		//
		table = "scholarship";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		//
		table = "attach_scholarship";
		list = DBProcess.read(table);
		str = str + "<br/><br/>" + TableProcess.createTable(table, list);
		
		
		response.getWriter().append(str);
	}
	
	public String createTable(List<List<String>> list) {
		
		int row = list.size();
		int col = list.get(0).size();
		
		System.out.println(row + ": " + col);
		
		String[] info = new String[row * col];
		int num = 0;
		
		for (int i = 0; i < row; i++) {
			List<String> str = list.get(i);
			for (int j = 0; j < col; j++) {
				
				info[num] = str.get(j);
//				System.out.println(str.get(j));
				num++;
			}
			
		}
		
		return createTable(row, col, info);
	}
	
	
	public String createTable(int row, int col, String[] info) {
		
		int num = 0;
		
		String str = "";
		
		str = str + "<table border='1px' width='200px' height='150px' align='center'>";
		
		for (int i = 0; i < row; i++) {
			str = str + "<tr>";
			
			for (int j = 0; j < col; j++) {
				str = str + "<td>";
				
				str = str + info[num];
				num++;
				
				str = str + "</td>";
			}
			
			str = str + "</tr>";
		}
		
		str = str + "</table>";
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

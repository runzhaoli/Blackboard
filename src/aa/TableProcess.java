package aa;

import java.util.List;

public class TableProcess {
	
	
	public static String createTable(String tableName, List<List<String>> list) {
		
		String str = "";
		
		String[] metaInfo = DBProcess.metaData(tableName);
		
		str = createTable(tableName, list, metaInfo);
		
		str = str + updateOperation(tableName);
		
		return str;
	}
	
	public static String updateOperation(String tableName) {
		String[] metaInfo = DBProcess.metaData(tableName);
//		String[] info = new String[metaInfo.length + 1];
		
		String str = "<br/><br/>";
		str = str + "<form action='OperationServlet' method='post'>";
		str = str + "<input name='table' type='hidden' value='" + tableName + "'/>";
//		str = str + createTable(1, info.length, info);
		int col = metaInfo.length;
		str = str + "<table border='1px' width='" + (col * 100) + "px' align='center'>";
		
		for (int i = 0; i < metaInfo.length; i++) {
			str = str + "<td>";
			
			str = str + "<input name='parameter" + i + "' type='text' value='";
			str = str + metaInfo[i];
			str = str + "' />";
			
			str = str + "</td>";
		}
		
		str = str + "<td>";
		str = str + "<input type='submit' value='update'/>";
		str = str + "</td>";
		
		str = str + "</table>";
		
		str = str + "</form>";
		
		return str;
	}

	public static String createTable(String tableName, List<List<String>> list, String[] metaInfo) {

		int row = list.size();
		int col = list.get(0).size();

		System.out.println(row + ": " + col);

		String[] info = new String[(row + 1) * (col + 1)];
		int num = 0;
		
		for (int i = 0; i < metaInfo.length; i++) {
			info[num] = metaInfo[i];
			num++;
		}
		info[num] = "operation";
		num++;
		
		String path = "http://localhost:8080";
		path = "";
		for (int i = 0; i < row; i++) {
			List<String> str = list.get(i);
			for (int j = 0; j < col; j++) {

				info[num] = str.get(j);
				num++;
			}
			info[num] = "<a href='" + path + "OperationServlet?type=1&name=" +
					tableName + "&id=" + i + 
						"'>delete</a>";
			num++;
		}
		
		return createTable(row + 1, col + 1, info);
	}

	public static String createTable(int row, int col, String[] info) {

		int num = 0;

		String str = "";

		str = str + "<table border='1px' width='" + (col * 100) + "px' align='center'>";

		for (int i = 0; i < row; i++) {
			str = str + "<tr>";

			for (int j = 0; j < col - 2; j++) {
				int n = info[num].length() * 5;
//				n = 300;
				
				str = str + "<td width='" + n +  "'>";
				str = str + "<input type='text' value='";
				str = str + info[num];
				str = str + "' />";
				
				num++;

				str = str + "</td>";
			}
			//
			str = str + "<td>" + info[num] + "</td>";
			num++;
			str = str + "<td>" + info[num] + "</td>";
			num++;
			
			//

			str = str + "</tr>";
		}

		str = str + "</table>";
		return str;
	}

}

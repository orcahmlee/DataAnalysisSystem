package tw.andrew;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SaveFile")
public class SaveFile extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chartset = UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		LinkedList<HashMap<String, String>> dataLinkedMap = (LinkedList<HashMap<String, String>>)session.getAttribute("dataLinkedMap");
		StringBuffer sb = new StringBuffer();
		sb = sb.append("Machine").append(", ").append("Date").append(", ").append("Time").append(", ").append("Temperature").append(", ").append("Pressure").append(", ").append("FlowRate").append("\n");
		
		for (int i = 0; i < dataLinkedMap.size(); i++) {
			String machine = dataLinkedMap.get(i).get("machine");
			String date = dataLinkedMap.get(i).get("date");
			String time = dataLinkedMap.get(i).get("time");
			String temperature = dataLinkedMap.get(i).get("temperature");
			String pressure = dataLinkedMap.get(i).get("pressure");
			String flowrate = dataLinkedMap.get(i).get("flowrate");	
			sb = sb.append(machine).append(",").append(date).append(",").append(time).append(",").append(temperature).append(",").append(pressure).append(",").append(flowrate).append("\n");
		}
		
		String data = sb.toString();
		DateTime dt = new DateTime();
		String saveTime = dt.getSaveTime();
		String fileName = saveTime + ".csv";

		File record = new File("/Users/Andrew/Programming/Java/DataAnalysisSystem/Record", fileName);
		FileOutputStream fout = new FileOutputStream(record);
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		bout.write(data.getBytes());
		bout.flush();
		bout.close();

		// TODO Has to redirect to "somewhere page" after saving file.
		// VIEW
		// Send the request, response, and session(including the data) to the VIEW.
		RequestDispatcher rd = request.getRequestDispatcher("saveFile.jsp");
		rd.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

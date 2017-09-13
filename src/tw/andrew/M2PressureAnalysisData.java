package tw.andrew;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/M2PressureAnalysisData")
public class M2PressureAnalysisData extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chartset = UTF-8");
		PrintWriter out = response.getWriter();

		RealTimeFlotData rtfd = new RealTimeFlotData("M2", 10);
		String avgPressure = rtfd.getAvgPressure();
		String stdPressure = rtfd.getStdPressure();
		String cpkOfPressure = rtfd.getCpkOfPressure();
		String caOfPressure = rtfd.getCaOfPressure();
		String data = "{" + "\"avgPressure\"" + ":" + avgPressure + ", " 
					+ "\"stdPressure\"" + ":" + stdPressure + ", "
					+ "\"cpkOfPressure\"" + ":" + cpkOfPressure + ", "
					+ "\"caOfPressure\"" + ":" + caOfPressure +
					"}";

		out.println(data);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

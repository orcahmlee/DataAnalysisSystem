package tw.andrew;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/M4TemperatureRealTimeData")
public class M4TemperatureRealTimeData extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chartset = UTF-8");
		PrintWriter out = response.getWriter();

		RealTimeFlotData rtfd = new RealTimeFlotData("M4", 10);
		String realTimeTemperatureFlotData = rtfd.getRealTimeTemperatureFlotData();
		
		out.println(realTimeTemperatureFlotData);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

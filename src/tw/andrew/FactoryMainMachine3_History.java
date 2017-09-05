package tw.andrew;

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

@WebServlet("/FactoryMainMachine3_History")
public class FactoryMainMachine3_History extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chartset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// PREPARATION
		HttpSession session = request.getSession(false);
		if (session == null) {return;}
		String login = (String)session.getAttribute("login");
		String firstname = (String)session.getAttribute("firstname");
		String lastname = (String)session.getAttribute("lastname");
		String isApproval = (String)session.getAttribute("isApproval");
		
		LinkedList<HashMap<String, String>> dataLinkedMap;
		
		// MODEL
		// Get the parameters from the request.
		String equipment = "M3";
		String startDate = (String)request.getParameter("startDate");
		String endDate = (String)request.getParameter("endDate");
		String startHour = (String)request.getParameter("startHour");
		String endHour = (String)request.getParameter("endHour");
		String startMinute = (String)request.getParameter("startMinute");
		String endMinute = (String)request.getParameter("endMinute");
		
		
		// If the parameters are not null, creating an object to retrieve the data and set the data to the session.
		if (startDate != null && endDate != null && startHour != null && endHour != null && startMinute != null && endMinute != null) {
			HistoryFlotData hfd = new HistoryFlotData(equipment, startDate, endDate, startHour, endHour, startMinute, endMinute);
			String temperatureFlotData = hfd.getTemperatureFlotData(); // The data for "Run Chart"
			String pressureFlotData = hfd.getPressureFlotData(); // The data for "Run Chart"
			String flowRateFlotData = hfd.getFlowRateFlotData(); // The data for "Run Chart"
			String pDFOfTemperature = hfd.getPDFOfTemperature();
			String pDFOfPressure = hfd.getPDFOfPressure();
			String pDFOfFlowRate = hfd.getPDFOfFlowRate();
			String normalDistributionOfTemperature = hfd.getNormalDistributionOfTemperature();
			String normalDistributionOfPressure = hfd.getNormalDistributionOfPressure();
			String normalDistributionOfFlowRate = hfd.getNormalDistributionOfFlowRate();

			session.setAttribute("machine", equipment);
			session.setAttribute("temperatureFlotData", temperatureFlotData);
			session.setAttribute("pressureFlotData", pressureFlotData);
			session.setAttribute("flowRateFlotData", flowRateFlotData);
			session.setAttribute("pDFOfTemperature", pDFOfTemperature);
			session.setAttribute("pDFOfPressure", pDFOfPressure);
			session.setAttribute("pDFOfFlowRate", pDFOfFlowRate);
			session.setAttribute("normalDistributionOfTemperature", normalDistributionOfTemperature);
			session.setAttribute("normalDistributionOfPressure", normalDistributionOfPressure);
			session.setAttribute("normalDistributionOfFlowRate", normalDistributionOfFlowRate);
						
			dataLinkedMap = hfd.getDataLinkedMap();
			session.setAttribute("dataLinkedMap", dataLinkedMap);
		}
		// VIEW
		// Send the request, response, and session(including the data) to the VIEW.
		RequestDispatcher rd = request.getRequestDispatcher("factoryMainMachine3_History.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

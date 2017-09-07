package tw.andrew;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FactoryMainPage")
public class FactoryMainPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// PREPARATION
		HttpSession session = request.getSession(false);
		if (session == null) {return;}
		String login = (String)session.getAttribute("login");
		String firstname = (String)session.getAttribute("firstname");
		String lastname = (String)session.getAttribute("lastname");
		String isApproval = (String)session.getAttribute("isApproval");
		
		// MODEL		
		MainPageFlotData m1 = new MainPageFlotData("M1", 60);
		MainPageFlotData m2 = new MainPageFlotData("M2", 60);
		MainPageFlotData m3 = new MainPageFlotData("M3", 60);
		MainPageFlotData m4 = new MainPageFlotData("M4", 60);		
		
		String m1Temperature = m1.getPDFOfTemperature();
		String m1Pressure = m1.getPDFOfPressure();
		String m1FlowRate = m1.getPDFOfFlowRate();
		String m1NormalDistributionOfTemperature = m1.getNormalDistributionOfTemperature();
		String m1NormalDistributionOfPressure = m1.getNormalDistributionOfPressure();
		String m1NormalDistributionOfFlowRate = m1.getNormalDistributionOfFlowRate();
		
		String m2Temperature = m2.getPDFOfTemperature();
		String m2Pressure = m2.getPDFOfPressure();
		String m2FlowRate = m2.getPDFOfFlowRate();
		String m2NormalDistributionOfTemperature = m2.getNormalDistributionOfTemperature();
		String m2NormalDistributionOfPressure = m2.getNormalDistributionOfPressure();
		String m2NormalDistributionOfFlowRate = m2.getNormalDistributionOfFlowRate();
		
		String m3Temperature = m3.getPDFOfTemperature();
		String m3Pressure = m3.getPDFOfPressure();
		String m3FlowRate = m3.getPDFOfFlowRate();
		String m3NormalDistributionOfTemperature = m3.getNormalDistributionOfTemperature();
		String m3NormalDistributionOfPressure = m3.getNormalDistributionOfPressure();
		String m3NormalDistributionOfFlowRate = m3.getNormalDistributionOfFlowRate();
		
		String m4Temperature = m4.getPDFOfTemperature();
		String m4Pressure = m4.getPDFOfPressure();
		String m4FlowRate = m4.getPDFOfFlowRate();
		String m4NormalDistributionOfTemperature = m4.getNormalDistributionOfTemperature();
		String m4NormalDistributionOfPressure = m4.getNormalDistributionOfPressure();
		String m4NormalDistributionOfFlowRate = m4.getNormalDistributionOfFlowRate();
		
		session.setAttribute("m1Temperature", m1Temperature);
		session.setAttribute("m1Pressure", m1Pressure);
		session.setAttribute("m1FlowRate", m1FlowRate);
		session.setAttribute("m1NormalDistributionOfTemperature", m1NormalDistributionOfTemperature);
		session.setAttribute("m1NormalDistributionOfPressure", m1NormalDistributionOfPressure);
		session.setAttribute("m1NormalDistributionOfFlowRate", m1NormalDistributionOfFlowRate);

		session.setAttribute("m2Temperature", m2Temperature);
		session.setAttribute("m2Pressure", m2Pressure);
		session.setAttribute("m2FlowRate", m2FlowRate);
		session.setAttribute("m2NormalDistributionOfTemperature", m2NormalDistributionOfTemperature);
		session.setAttribute("m2NormalDistributionOfPressure", m2NormalDistributionOfPressure);
		session.setAttribute("m2NormalDistributionOfFlowRate", m2NormalDistributionOfFlowRate);

		session.setAttribute("m3Temperature", m3Temperature);
		session.setAttribute("m3Pressure", m3Pressure);
		session.setAttribute("m3FlowRate", m3FlowRate);
		session.setAttribute("m3NormalDistributionOfTemperature", m3NormalDistributionOfTemperature);
		session.setAttribute("m3NormalDistributionOfPressure", m3NormalDistributionOfPressure);
		session.setAttribute("m3NormalDistributionOfFlowRate", m3NormalDistributionOfFlowRate);

		session.setAttribute("m4Temperature", m4Temperature);
		session.setAttribute("m4Pressure", m4Pressure);
		session.setAttribute("m4FlowRate", m4FlowRate);
		session.setAttribute("m4NormalDistributionOfTemperature", m4NormalDistributionOfTemperature);
		session.setAttribute("m4NormalDistributionOfPressure", m4NormalDistributionOfPressure);
		session.setAttribute("m4NormalDistributionOfFlowRate", m4NormalDistributionOfFlowRate);
		
		// VIEW
		RequestDispatcher rd = request.getRequestDispatcher("factoryMainPage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

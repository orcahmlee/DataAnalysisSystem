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
		
		String m2Temperature = m2.getPDFOfTemperature();
		String m2Pressure = m2.getPDFOfPressure();
		String m2FlowRate = m2.getPDFOfFlowRate();
		
		String m3Temperature = m3.getPDFOfTemperature();
		String m3Pressure = m3.getPDFOfPressure();
		String m3FlowRate = m3.getPDFOfFlowRate();
		
		String m4Temperature = m4.getPDFOfTemperature();
		String m4Pressure = m4.getPDFOfPressure();
		String m4FlowRate = m4.getPDFOfFlowRate();
		
		session.setAttribute("m1Temperature", m1Temperature);
		session.setAttribute("m1Pressure", m1Pressure);
		session.setAttribute("m1FlowRate", m1FlowRate);

		session.setAttribute("m2Temperature", m2Temperature);
		session.setAttribute("m2Pressure", m2Pressure);
		session.setAttribute("m2FlowRate", m2FlowRate);

		session.setAttribute("m3Temperature", m3Temperature);
		session.setAttribute("m3Pressure", m3Pressure);
		session.setAttribute("m3FlowRate", m3FlowRate);

		session.setAttribute("m4Temperature", m4Temperature);
		session.setAttribute("m4Pressure", m4Pressure);
		session.setAttribute("m4FlowRate", m4FlowRate);
		
		// VIEW
		RequestDispatcher rd = request.getRequestDispatcher("factoryMainPage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

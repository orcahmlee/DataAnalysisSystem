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

@WebServlet("/FactoryMainMachine1_RealTime")
public class FactoryMainMachine1_RealTime extends HttpServlet {
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
		
		
		String data2 = "[[21:49:10,2], [21:49:20,6], [21:49:30,9], [21:49:40,12], [21:49:50,5], [21:50:00,8]]";
		session.setAttribute("data2", data2);
		
		
		// VIEW
		RequestDispatcher rd = request.getRequestDispatcher("factoryMainMachine_RealTime.jsp");
		rd.forward(request, response);

	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

@WebServlet("/FactoryMainMachine3_RealTime")
public class FactoryMainMachine3_RealTime extends HttpServlet {
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
		
		// VIEW
		RequestDispatcher rd = request.getRequestDispatcher("factoryMainMachine3_RealTime.jsp");
		rd.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

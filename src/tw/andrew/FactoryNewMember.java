package tw.andrew;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FactoryNewMember")
public class FactoryNewMember extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// Preparation
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		// Model
		try {
			FactoryMember member = new FactoryMember();
			member.setAccount(account);
			member.setPassword(password);
			member.setFirstname(firstname);
			member.setLasttname(lastname);
			boolean result = member.newInsert();

			// View
			request.setAttribute("result", result);;
			RequestDispatcher rd = request.getRequestDispatcher("factoryRegisterResult.jsp");
			rd.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.toString());
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

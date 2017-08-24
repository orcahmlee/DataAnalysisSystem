/*
 * CONTROLLER
 */
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
import javax.servlet.http.HttpSession;

@WebServlet("/FactoryMemberLogin")
public class FactoryMemberLogin extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// PREPARATION
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		// MODEL
		try {
			FactoryMember member = new FactoryMember();
			member.setAccount(account);
			member.setPassword(password);

			if (member.isValidMember()) {
				// Login Success
				String firstname = member.getFirstname();
				String lastname = member.getLastname();
				String isApproval = member.getIsApproval();

				session.setAttribute("login", "login");
				session.setAttribute("firstname", firstname);
				session.setAttribute("lastname", lastname);
				session.setAttribute("isApproval", isApproval);
				response.sendRedirect("FactoryMainPage");
				// TODO 傳送權限屬性，依照權限屬性來判斷可以碰觸哪些數據
			}else {
				// Login Failure
				session = request.getSession();
				session.setAttribute("login", "failure");
				session.setMaxInactiveInterval(60);
				//TODO session 時效好像無法在 60 秒內消失？
				response.sendRedirect("FactoryLogin");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

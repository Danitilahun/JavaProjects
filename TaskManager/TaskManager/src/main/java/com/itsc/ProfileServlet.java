package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();

	    // Retrieve user details from the session
	    int userId = (int) session.getAttribute("userId");
	    String username = (String) session.getAttribute("username");
	    String password = (String) session.getAttribute("password");

	    // Fetch user details based on the retrieved userId (You may use your DAO or other mechanisms)
	    // For demonstration purposes, let's create a sample user
	    User user = new User();
	    user.setId(userId);
	    user.setUsername(username);
	    user.setPassword(password);

	    // Set the user details in the request attribute
	    request.setAttribute("user", user);

	    // Forward to the profile.jsp page
	    request.getRequestDispatcher("profile.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String newUsername = request.getParameter("username");
        String newPassword = request.getParameter("password");

        // Assuming you have a method in your UserDao to update user information
        UserDAO.updateUserInfo(userId, newUsername, newPassword);

        // Redirect back to the profile page after updating
        response.sendRedirect("LogoutServlet");
    }

}

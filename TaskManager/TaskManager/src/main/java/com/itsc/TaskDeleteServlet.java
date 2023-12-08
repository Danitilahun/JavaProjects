package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TaskDeleteServlet
 */
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        // Delete the task (Assuming you have a method in TaskDAO for deleting a task by id)
        TaskDAO.deleteTask(taskId);

        // Redirect back to the task list page
        response.sendRedirect("TaskServlet");
    }

}

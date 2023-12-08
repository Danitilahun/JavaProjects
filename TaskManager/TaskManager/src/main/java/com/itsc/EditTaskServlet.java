package com.itsc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Servlet implementation class EditTaskServlet
 */
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int taskId = Integer.parseInt(request.getParameter("taskId"));

	    // Fetch the task details based on taskId
	    Task task = TaskDAO.getTaskById(taskId);

	    // Set the task details in request attributes
	    request.setAttribute("task", task);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
	    dispatcher.forward(request, response);
	}

       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
        String priority = request.getParameter("priority");

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        // Update the task in the database (Assuming you have a method in TaskDAO for updating a task)
        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setTitle(title);
        updatedTask.setDescription(description);
        updatedTask.setDueDate(dueDate);
        updatedTask.setPriority(priority);
        TaskDAO.updateTask(updatedTask);
        

        // Redirect back to the task list page after updating
        response.sendRedirect("TaskServlet");
    }

}

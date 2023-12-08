package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet implementation class TaskUpdateServlet
 */
public class TaskUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");
        String priority = request.getParameter("priority");

        // Fetch the existing task details
        Task existingTask = TaskDAO.getTaskById(taskId);

        // Update the task details
        existingTask.setTitle(title);
        existingTask.setDescription(description);
        existingTask.setDueDate(LocalDate.parse(dueDate));
        existingTask.setPriority(priority);

        // Save the updated task to the database
        TaskDAO.updateTask(existingTask);

        // Redirect back to the task list page
        response.sendRedirect("TaskServlet");
    }

}

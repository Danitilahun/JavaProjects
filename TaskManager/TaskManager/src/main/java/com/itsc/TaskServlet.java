package com.itsc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve userId from the session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        // Fetch tasks associated with the user
        List<Task> tasks = TaskDAO.getAllTasks(userId);

        // Set tasks in the request attribute
        request.setAttribute("tasks", tasks);
        


        // Forward the request to tasks.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("tasks.jsp");
        dispatcher.forward(request, response);
    }
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");
        String priority = request.getParameter("priority");
        
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        // Create a Task object and set its properties
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(LocalDate.parse(dueDate));
        task.setPriority(priority);
        task.setUserId(userId);
        task.setIs_completed(false);

        // Save the task to the database (Assuming you have a method to save tasks)
        TaskDAO.saveTask(task);
        
        List<Task> tasks = TaskDAO.getAllTasks(userId);

        // Set tasks in the request attribute
        request.setAttribute("tasks", tasks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("tasks.jsp");
        dispatcher.forward(request, response);
	}

}

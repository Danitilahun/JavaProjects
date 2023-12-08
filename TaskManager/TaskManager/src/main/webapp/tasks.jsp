<%@ page import="java.util.List" %>
<%@ page import="com.itsc.Task" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task List</title>
    <link rel="stylesheet" type="text/css" href="css/tasks.css">
</head>
<%@ include file="header.jsp" %>
<body>

    <div id="content">
        <!-- Live Search Input -->
        <label>Search by Title or Due Date:</label>
        <input type="text" id="searchInput" class="searchInput" oninput="filterTasks()" />

        <!-- Your existing JSP content -->
        <% 
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
        if (tasks == null || tasks.isEmpty()) { %>
            <p>You have no tasks. Start adding some!</p>
        <% } else { 
            // Sort tasks based on priority (assuming priority is a string)
            Collections.sort(tasks, (task1, task2) -> {
                // Custom order
                String[] priorityOrder = {"high", "middle", "low"};
                int index1 = Arrays.asList(priorityOrder).indexOf(task1.getPriority());
                int index2 = Arrays.asList(priorityOrder).indexOf(task2.getPriority());
                return Integer.compare(index1, index2);
            });
        %>
            <table id="tasksTable">
                <!-- Table Header -->
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Due Date</th>
                        <th>Priority</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Task task : tasks) { %>
                        <!-- Display Tasks -->
                        <tr id="taskRow<%= task.getId() %>">
                            <td><%= task.getTitle() %></td>
                            <td><%= task.getDescription() %></td>
                            <td><%= task.getDueDate() %></td>
                            <td><%= task.getPriority() %></td>
                            <td><%= task.getIs_completed() %>
							<td class="action-buttons">
							                <!-- Apply the styles to the buttons -->
				                <form action="UpdateTaskStatus" method="post" style="display: inline;" 
				                      <% if (!task.getIs_completed().equals("Completed")) { %> onsubmit="return confirm('Are you sure you want to mark this task as completed?');" <% } %>>
				                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
				                    <button class="complete" type="submit" <% if (task.getIs_completed().equals("Completed")) { %> disabled <% } %>>Mark Completed</button>
				                </form>
				                <form action="TaskDeleteServlet" method="get" style="display: inline;" onsubmit="return confirm('Are you sure you want to delete this task?');">
				                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
				                    <button class="delete" type="submit">Delete</button>
				                </form>
				                <div>
				                    <a href="EditTaskServlet?taskId=<%= task.getId() %>" <% if (task.getIs_completed().equals("Completed")) { %> style="pointer-events: none;" <% } %>>
				                        <button type="button" <% if (task.getIs_completed().equals("Completed")) { %> disabled <% } %>>Edit</button>
				                    </a>
				                </div>
				            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
        <button class="create" onclick="openOverlay()">Create Task</button>
    </div>

    <!-- Task Overlay -->
    <div id="overlay" onclick="closeOverlay(event)">
        <form id="taskForm" action="TaskServlet" method="post">
            <h2 id="overlayTitle">Create Task</h2>
             <label>Title: <input type="text" name="title"  required></label><br>
        <label>Description: <textarea name="description" required></textarea></label><br>
        <label>Due Date: <input type="date" name="dueDate" required></label><br>
        <label>Priority:
            <select name="priority" required>
                <option value="high" >High</option>
                <option value="middle">Middle</option>
                <option value="low">Low</option>
            </select>
        </label><br>
            <input type="submit" value="Create">
        </form>
    </div>

    <script>
        function openOverlay() {
            document.getElementById("overlay").style.display = "flex";
        }
        function closeOverlay(event) {
            console.log("clicked");
            var overlay = document.getElementById("overlay");
            if (event.target === overlay) {
                overlay.style.display = "none";
            }
        }


        function filterTasks() {
            var input, filter, table, tr, tdTitle, tdDate, i, txtValueTitle, txtValueDate;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("tasksTable");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                // Adjust the column indices for Title (Index 1) and Due Date (Index 3)
                tdTitle = tr[i].getElementsByTagName("td")[0];
                tdDate = tr[i].getElementsByTagName("td")[2];

                if (tdTitle && tdDate) {
                    txtValueTitle = tdTitle.textContent || tdTitle.innerText;
                    txtValueDate = tdDate.textContent || tdDate.innerText;

                    // Adjust the condition to check if either title or date contains the filter text
                    if (
                        txtValueTitle.toUpperCase().indexOf(filter) > -1 ||
                        txtValueDate.toUpperCase().indexOf(filter) > -1
                    ) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
       	function markCompleted(taskId) {
       	    console.log(taskId);
       	    var row = document.getElementById("taskRow" + taskId);
       	    if (row) {
       	        row.classList.add("completed-task");
       	    } else {
       	        console.error("Row with ID 'taskRow" + taskId + "' not found.");
       	    }
        }

    </script>

</body>
</html>

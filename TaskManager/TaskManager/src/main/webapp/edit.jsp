<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Task</title>
    <link rel="stylesheet" type="text/css" href="css/edit.css">
</head>
<%@ include file="header.jsp" %>
<body>
    <form action="EditTaskServlet" method="post">
        <h2 id="overlayTitle">Edit Task</h2>
        <label>Title: <input type="text" name="title" value="${task.title}" required></label><br>
        <label>Description: <textarea name="description" required>${task.description}</textarea></label><br>
        <label>Due Date: <input type="date" name="dueDate" value="${task.dueDate}" required></label><br>
        <label>Priority:
            <select name="priority" required>
                <option value="high" ${task.priority == 'high' ? 'selected' : ''}>High</option>
                <option value="middle" ${task.priority == 'middle' ? 'selected' : ''}>Middle</option>
                <option value="low" ${task.priority == 'low' ? 'selected' : ''}>Low</option>
            </select>
        </label><br>
        <input type="hidden" name="taskId" value="${task.id}">
        <input type="submit" value="Update">
    </form>
</body>
</html>

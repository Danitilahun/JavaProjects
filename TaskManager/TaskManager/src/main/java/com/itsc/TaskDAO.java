package com.itsc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private static final String INSERT_TASK_SQL = "INSERT INTO tasks (title, description, due_date, priority, is_completed, userid) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_TASKS_SQL = "SELECT * FROM tasks WHERE userid = ?";

    public static void saveTask(Task task) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(task.getDueDate()));
            preparedStatement.setString(4, task.getPriority());
            preparedStatement.setBoolean(5, false);
            preparedStatement.setInt(6, task.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> getAllTasks(int userId) {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS_SQL)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setDueDate(resultSet.getDate("due_date").toLocalDate());
                task.setPriority(resultSet.getString("priority"));
                task.setUserId(resultSet.getInt("userid"));
                task.setIs_completed(resultSet.getBoolean("is_completed"));
                
//                System.out.println(resultSet.getBoolean("is_completed"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    private static final String UPDATE_TASK_SQL = "UPDATE tasks SET title = ?, description = ?, due_date = ?, priority = ? WHERE id = ?";
    private static final String UPDATE_TASK_STATUS = "UPDATE tasks SET is_completed = ? WHERE id = ?";
    private static final String DELETE_TASK_SQL = "DELETE FROM tasks WHERE id = ?";

    public static void updateTask(Task task) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(task.getDueDate()));
            preparedStatement.setString(4, task.getPriority());
            preparedStatement.setInt(5, task.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateTaskStatus(int taskId) {
    	try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_STATUS)) {
    		preparedStatement.setBoolean(1, true);
    		preparedStatement.setInt(2, taskId);
    		preparedStatement.executeUpdate();
    		System.out.println(taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public static void deleteTask(int taskId) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_SQL)) {

            preparedStatement.setInt(1, taskId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static final String SELECT_TASK_BY_ID_SQL = "SELECT * FROM tasks WHERE id = ?";

    // Existing methods...

    public static Task getTaskById(int taskId) {
        Task task = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID_SQL)) {

            preparedStatement.setInt(1, taskId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setDueDate(resultSet.getDate("due_date").toLocalDate());
                task.setPriority(resultSet.getString("priority"));
                task.setUserId(resultSet.getInt("userid"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }
}

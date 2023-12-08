package com.itsc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    // Method to update username and password
    public static void updateUserInfo(int userId, String newUsername, String newPassword) {
        // Use DatabaseUtil class or your own database connection mechanism to get a connection
        try (Connection connection = DatabaseUtil.getConnection()) {
            // SQL update statement
            String updateQuery = "UPDATE users SET username = ?, password = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set parameters
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, newPassword);
                preparedStatement.setInt(3, userId);

                // Execute update
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
        }
    }
}

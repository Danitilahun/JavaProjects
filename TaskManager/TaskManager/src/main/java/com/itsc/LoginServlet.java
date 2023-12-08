package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "select * from users where username = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, name);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
                    String pwd = rs.getString("password");
                    int id = Integer.parseInt(rs.getString("id"));
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("userId", id);
                    session.setAttribute("password", pwd);
                    System.out.println(id);
                    response.sendRedirect("TaskServlet");
                    
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
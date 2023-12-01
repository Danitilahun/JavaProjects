import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewEmployeesServlet")
public class ViewEmployeesServlet extends HttpServlet {
	private final static String query = "select id,name,designation,salary from employees";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h2 class=\"text-primary\">Employees Data</h2></marquee>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","1310") ;PreparedStatement ps=con.prepareStatement(query);){
			
			
			ResultSet rs = ps.executeQuery();
			pw.println("<table class='table table-hover table-striped' style='margin:auto; width:800px;margin-top:100px;'>");
			pw.println("<tr>");
			pw.println("<th>Id</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Designation</th>");
			pw.println("<th>Salary</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");

			pw.println("</tr>");
			while (rs.next()) {
			    System.out.println("salary" + rs.getBigDecimal(4));
			    pw.println("<tr>");
			    pw.println("<td>" + rs.getInt(1) + "</td>");
			    pw.println("<td>" + rs.getString(2) + "</td>");
			    pw.println("<td>" + rs.getString(3) + "</td>");
			    pw.println("<td>" + rs.getBigDecimal(4) + "</td>");
			    pw.println("<td> <a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
			    pw.println("<td> <a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");


			    pw.println("</tr>");
			}
			pw.println("</table>");


				
							

			
			
			pw.println("</table>");
			
			
			
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+ se.getMessage() +"</h2>");
			// TODO Auto-generated catch block
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'> <button class='btn btn-outline-success'>Home</button></a>");
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
	}
	
}

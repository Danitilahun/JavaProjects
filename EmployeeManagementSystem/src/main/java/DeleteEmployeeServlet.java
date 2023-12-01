import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/deleteurl")
public class DeleteEmployeeServlet extends HttpServlet {
	private final static String query="delete from employees where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		int id=Integer.parseInt(req.getParameter("id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		try(Connection con=DriverManager.getConnection("jgittdbc:mysql://localhost:3306/employees","root","1310") ;PreparedStatement ps=con.prepareStatement(query)){
				
			ps.setInt(1, id);




			int count=ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto; width:300px;margin-top:100px;'> ");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Employee Deleted Succesfully</h2>");
			}
			else {
				pw.println("<h2 class='bg-danger text-light text-center'>Employee Not Deleted </h2>");

			}
			pw.println("<a href='home.html'> <button class='btn btn-outline-success'>Home</button></a>");
			pw.println("&nbsp; &nbsp");
			pw.println("<a href='ViewEmployeesServlet'> <button class='btn btn-outline-success'> View Employees</button></a>");

			pw.println("</div>");
			pw.close();
			
			
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+ se.getMessage() +"</h2>");
			// TODO Auto-generated catch block
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
	}
}

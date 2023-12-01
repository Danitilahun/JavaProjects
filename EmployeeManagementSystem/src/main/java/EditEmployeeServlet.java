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


@WebServlet("/editurl")
public class EditEmployeeServlet extends HttpServlet {
	private final static String query = "select name,designation,salary from employees where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		int id=Integer.parseInt(req.getParameter("id"));
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","1310") ;PreparedStatement ps=con.prepareStatement(query);){
			
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
            pw.println("<form action='edit?id="+id+"' method='post'>");
            pw.println("<table class='table table-hover table-striped'>");
            pw.println("<tr>");
            pw.println("<td>Name</td>");
            pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Email</td>");
            pw.println("<td><input type='text' name='designation' value='"+rs.getString(2)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Mobile</td>");
            pw.println("<td><input type='text' name='salary' value='"+rs.getBigDecimal(3)+"'></td>");
            pw.println("</tr>");
            
            pw.println("<tr>");
            pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
            pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");


				
							

			
			
			pw.println("</table>");
			
			
			
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+ se.getMessage() +"</h2>");
			// TODO Auto-generated catch block
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'> <button class='btn btn-outline-success'>Home</button></a>");
		pw.println("</div>");
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
	}
}

package com.EmployeeManagement;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Delete_Employee
 */
@WebServlet("/Delete_Employee")
public class Delete_Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter  writer=response.getWriter();
	
		try {
			
		int emp_id=Integer.parseInt(request.getParameter("emp_id"));
		String query="DELETE FROM employees WHERE emp_id=?";
		

		Class.forName("com.mysql.cj.jdbc.Driver");       
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "pass123");
		 PreparedStatement preparedstatement=connection.prepareStatement(query);
		  preparedstatement.setInt(1, emp_id);
		  
		  int row=preparedstatement.executeUpdate();
			 System.out.println(row+" deleted....");
			
			
		}catch(Exception e) {
			e.printStackTrace();
				
			}
		
		
		
		
		}

}

package com.EmployeeManagement;

import jakarta.servlet.ServletException;
import java.io.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		try {
			

		int emp_id=Integer.parseInt(request.getParameter("emp_id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		String designation=request.getParameter("designation");
		double salary=Double.parseDouble(request.getParameter("salary"));
		int experience=Integer.parseInt(request.getParameter("experience"));
	    String joining_date = request.getParameter("joining_date");
		String date_of_birth = request.getParameter("date_of_birth");

	
		
	  
        String gender = request.getParameter("gender");
        String mobile_no = request.getParameter("mobile_no");
        String address = request.getParameter("address");
        
        String query = "INSERT INTO employees " +
                "(emp_id, name, email, department, designation, salary, experience, joining_date, date_of_birth, gender, mobile_no, address) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/employee_management", "root", "pass123");
        PreparedStatement preparedstatement=connection.prepareStatement(query);
		 preparedstatement.setInt(1, emp_id);
        preparedstatement.setString(2, name);
        preparedstatement.setString(3, email);
        preparedstatement.setString(4, department);
        preparedstatement.setString(5, designation);
        preparedstatement.setDouble(6, salary);
        preparedstatement.setInt(7, experience);
        preparedstatement.setString(8, joining_date);
        preparedstatement.setString(9, date_of_birth);

        preparedstatement.setString(10, gender);
        preparedstatement.setString(11, mobile_no);
        preparedstatement.setString(12, address);
        
        
        
        int row=preparedstatement.executeUpdate();
		System.out.println(row+" inserted...");
		
		
		
		
        
        
        
        

        connection.close();
		
	}catch(Exception e) {
		e.printStackTrace(out);
		
	}
	}

}

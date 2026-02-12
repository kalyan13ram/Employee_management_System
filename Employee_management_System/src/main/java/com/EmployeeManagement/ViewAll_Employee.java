package com.EmployeeManagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/ViewAll_Employee")
public class ViewAll_Employee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewAll_Employee() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Employee List</title>");
        out.println("<style>");
        out.println("body {font-family: Arial, sans-serif; background: #f9fafc; margin: 0; padding: 0;}");
        out.println("h1 {background: #2563eb; color: white; padding: 15px; text-align: center; margin: 0;}");
        out.println("table {width: 90%; margin: 30px auto; border-collapse: collapse; box-shadow: 0 2px 6px rgba(0,0,0,0.1);}");
        out.println("th, td {border: 1px solid #ddd; padding: 10px; text-align: center;}");
        out.println("th {background-color: #2563eb; color: white;}");
        out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
        out.println("tr:hover {background-color: #e6f0ff;}");
        out.println(".no-data {text-align: center; padding: 20px; font-size: 18px; color: #555;}");
        out.println(".back-btn {display: block; width: 200px; margin: 20px auto; text-decoration: none; color: white; background: #2563eb; padding: 10px 15px; border-radius: 5px;}");
        out.println(".back-btn:hover {background: #1e40af;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>All Employee Details</h1>");

        try {
            // ✅ Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Database Connection (Change password if needed)
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee_management",
                    "root",
                    "pass123"  // ⚠️ Make sure there's no space at the end
            );

            // ✅ SQL Query
            String query = "SELECT emp_id, name, email,department,designation,salary,experience, joining_date, date_of_birth, gender, mobile_no, address FROM employees";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            boolean hasData = false;

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Department</th>");
            out.println("<th>Designation</th>");
            out.println("<th>Salary (₹)</th>");
            out.println("<th>Experience</th>");
            out.println("<th>Joining Date</th>");
            out.println("<th>DOB</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Mobile</th>");
            out.println("<th>Address</th>");
            out.println("</tr>");

            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("emp_id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("designation") + "</td>");
                out.println("<td>" + rs.getDouble("salary") + "</td>");
                out.println("<td>" + rs.getInt("experience") + "</td>");
                out.println("<td>" + rs.getString("joining_date") + "</td>");
                out.println("<td>" + rs.getString("date_of_birth") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getString("mobile_no") + "</td>");
                out.println("<td>" + rs.getString("address") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            if (!hasData) {
                out.println("<div class='no-data'>No employee records found in the database.</div>");
            }

            // ✅ Back Button
            out.println("<a href='ViewAll_Employee.html' class='back-btn'>⬅ Back</a>");
            out.println("<a href='AddEmployeeForm.html' class='back-btn'>➕ Add Employee</a>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<div class='no-data' style='color:red;'>Error fetching employees details: " + e.getMessage() + "</div>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}


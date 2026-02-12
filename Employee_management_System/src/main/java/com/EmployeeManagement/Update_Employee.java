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
import java.sql.PreparedStatement;

@WebServlet("/Update_Employee")
public class Update_Employee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Update_Employee() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String designation = request.getParameter("designation");
        String salaryStr = request.getParameter("salary");
        String mobile_no = request.getParameter("mobile_no");
        String address = request.getParameter("address");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee_management",
                    "root",
                    "pass123"
            );

            // Build dynamic SQL based on provided fields
            StringBuilder query = new StringBuilder("UPDATE employees SET ");
            boolean first = true;

            if (name != null && !name.isEmpty()) {
                query.append("name=?,");
            }
            if (email != null && !email.isEmpty()) {
                query.append("email=?,");
            }
            if (department != null && !department.isEmpty()) {
                query.append("department=?,");
            }
            if (designation != null && !designation.isEmpty()) {
                query.append("designation=?,");
            }
            if (salaryStr != null && !salaryStr.isEmpty()) {
                query.append("salary=?,");
            }
            if (mobile_no != null && !mobile_no.isEmpty()) {
                query.append("mobile_no=?,");
            }
            if (address != null && !address.isEmpty()) {
                query.append("address=?,");
            }

            // Remove last comma
            if (query.charAt(query.length() - 1) == ',') {
                query.deleteCharAt(query.length() - 1);
            }

            query.append(" WHERE emp_id=?");

            PreparedStatement ps = connection.prepareStatement(query.toString());
            int index = 1;

            if (name != null && !name.isEmpty()) ps.setString(index++, name);
            if (email != null && !email.isEmpty()) ps.setString(index++, email);
            if (department != null && !department.isEmpty()) ps.setString(index++, department);
            if (designation != null && !designation.isEmpty()) ps.setString(index++, designation);
            if (salaryStr != null && !salaryStr.isEmpty()) ps.setDouble(index++, Double.parseDouble(salaryStr));
            if (mobile_no != null && !mobile_no.isEmpty()) ps.setString(index++, mobile_no);
            if (address != null && !address.isEmpty()) ps.setString(index++, address);

            ps.setInt(index, emp_id);

            int updated = ps.executeUpdate();

            out.println("<!DOCTYPE html><html><head><title>Update Result</title>");
            out.println("<style>body{text-align:center;font-family:Arial;background:#f0f8ff;padding-top:50px;}h2{color:green;}</style>");
            out.println("</head><body>");

            if (updated > 0) {
                out.println("<h2>✅ Employee ID " + emp_id + " updated successfully!</h2>");
            } else {
                out.println("<h2 style='color:red;'>❌ Employee ID " + emp_id + " not found!</h2>");
            }

            out.println("<a href='Update_Employee.html'>🔁 Update Another</a><br>");
            out.println("<a href='ViewAll_Employee.html'>👀 View All Employees</a>");
            out.println("</body></html>");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}

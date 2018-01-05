package com.siri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try

		{

			response.setContentType("text/html");

			PrintWriter out = response.getWriter();

			try

			{

				Class.forName("com.mysql.jdbc.Driver").newInstance();

				String jdbcUrl = "jdbc:mysql://localhost:3306/egiants";

				String username = "root";

				String password = "admin";

				Connection connection = null;

				connection = DriverManager.getConnection(jdbcUrl, username, password);

				Statement statement = connection.createStatement();

				String x = request.getParameter("EmployeeID");

				String y = request.getParameter("EmployeeNAME");

				String sql = "insert into employee values('" + x + "','" + y + "')";

				statement.executeUpdate(sql);

				out.println("<h1> Record successfully inserted</h1>");

				RequestDispatcher rd = request.getRequestDispatcher("/form.html");

				rd.include(request, response);

			}

			catch (ClassNotFoundException | InstantiationException | IllegalAccessException cnfe)

			{

				out.println("class not found");

			}

		}

		catch (SQLException e)

		{

			throw new RuntimeException("Cannot connect the database!", e);

		}

	}
	

}

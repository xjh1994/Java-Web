package connPool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

public class ConnPool extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ConnPool() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * response.setContentType("text/html");
		 * response.setCharacterEncoding("GBK"); PrintWriter out =
		 * response.getWriter();
		 * 
		 * try { javax.naming.Context ctx = new javax.naming.InitialContext();
		 * javax.sql.DataSource ds =
		 * (javax.sql.DataSource)ctx.lookup("java:/comp/env/jdbc/mydb");
		 * 
		 * Connection conn = ds.getConnection();
		 * 
		 * PreparedStatement pstmt =
		 * conn.prepareStatement("SELECT name, author FROM books"); ResultSet rs
		 * = (ResultSet) pstmt.executeQuery(); StringBuilder table = new
		 * StringBuilder(); table.append("<table border='1'>");
		 * table.append("<tr><td>书名</td><td>作者</td></tr>"); while(rs.next()) {
		 * table.append("<tr><td>" + rs.getString("name") + "</td></tr>");
		 * table.append(rs.getString("author") + "</td></tr>"); }
		 * table.append("</table>"); out.println(table.toString());
		 * pstmt.close();
		 * 
		 * } catch(Exception e) { out.println(e.getMessage()); }
		 * 
		 * out.flush(); out.close();
		 */

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		response.setContentType("text/html");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.println("结果为：<br>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/java","root","");
			st = (Statement) conn.createStatement();
			rs = (ResultSet) st.executeQuery("select name,age from testinfo");
			while (rs.next()) {
				String name = rs.getString("name");
				String age = rs.getString("age");
				
				out.println("姓名：" + name + "年龄：" + age);
				out.println("<br>");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (st != null) {
					st.close();
					st = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

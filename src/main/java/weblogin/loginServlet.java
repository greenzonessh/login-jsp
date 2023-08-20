package weblogin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
//			
			String n=request.getParameter("txtName");
			String p=request.getParameter("txtPwd");
			if(n.equals("richard001") && p.equals("root"))
			{
				List<User> listUser = new ArrayList<User>();
				listUser.add(new User("S1", "Roma", "Dep1", 35));
				listUser.add(new User("S2", "Rani", "Dep1", 70));
				listUser.add(new User("S3", "Rosa", "Dep1", 60));
				listUser.add(new User("S4", "Dani", "Dep1", 90));
				listUser.add(new User("S5", "Domi", "Dep2", 30));
				listUser.add(new User("S6", "Supri", "Dep3", 32));
				listUser.add(new User("S7", "Sulis", "Dep3", 70));
				listUser.add(new User("S8", "Simon", "Dep3", 20));
				
				request.setAttribute("userID", "richard001");
				request.setAttribute("studentNameLogin", "Richard");
				request.setAttribute("listUser", listUser);
				
				//distinct department
				List<User> distinctDepartment = listUser.stream()
						.filter(distinctByKey(User::getDepartment))
						.collect(Collectors.toList());
				
				List<DepartMarksDTO> departMarks = new ArrayList<DepartMarksDTO>();
				
				for (User user : distinctDepartment) {
					List<User> distinctByDepartment = listUser.stream()
							.filter(c -> c.getDepartment().equals(user.getDepartment()))
							.collect(Collectors.toList());
					
					List<User> distinctByDepartmentMark = listUser.stream()
							.filter(d -> d.getDepartment().equals(user.getDepartment()) && d.getMark() >= 40)
							.collect(Collectors.toList());
					
					double countPass = (double) distinctByDepartmentMark.size() / (double) distinctByDepartment.size()*100;
					departMarks.add(new DepartMarksDTO(user.getDepartment(), distinctByDepartment.size(), countPass));
				}
				
				String tableHTML = "";
				
				for (User user : distinctDepartment) {
					List<User> distinctByDepartment = listUser.stream()
							.filter(c -> c.getDepartment().equals(user.getDepartment()))
							.collect(Collectors.toList());
					
					List<DepartMarksDTO> filterMarks = departMarks.stream()
							.filter(x -> x.getDepartName().equals(user.getDepartment()))
							.collect(Collectors.toList());
					
					int countIdx = 0;
					for (User byDepart : distinctByDepartment) {
						if (countIdx == 0) {
							tableHTML += "<tr>"
									+ "<td rowspan=" + filterMarks.get(0).getRowspan() + ">" + byDepart.getDepartment() + "</td>"
									+ "<td>" + byDepart.getStudentID() + "</td>"
									+ "<td>" + byDepart.getMark() + "</td>"
									+ "<td rowspan=" + filterMarks.get(0).getRowspan() + ">" + String.format("%.2f", filterMarks.get(0).getPass()) + "</td></tr>";
						} else {
							tableHTML += "<tr>"
									+ "<td>" + byDepart.getStudentID() + "</td>"
									+ "<td>" + byDepart.getMark() + "</td>"
									+ "</tr>";
						}
						
						countIdx++;
					}
				}
				
				request.setAttribute("tableHTML", tableHTML);
				RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
				rd.forward(request, response);
			} else {
				out.println("<font color=red size=18>Login Failed!!<br>");
				out.println("<a href=login.jsp>Try Again!!</a>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}

}

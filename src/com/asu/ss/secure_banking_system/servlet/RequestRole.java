package com.asu.ss.secure_banking_system.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ss.secure_banking_system.model.RoleEntity;
import com.asu.ss.secure_banking_system.model.UserEntity;
import com.asu.ss.secure_banking_system.service.RequestRoleService;

/**
 * Servlet implementation class RequestRole
 */
@WebServlet("/RequestRole")
public class RequestRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*UserEntity selectedUser = new UserEntity();
		selectedUser.setUserID(request.getParameter("userSelect"));
		RoleEntity selectedRole = new RoleEntity();
		selectedRole.setRoleID(Integer.parseInt(request.getParameter("roleSelect")));
		RequestRoleService rrsvc= new RequestRoleService();
		rrsvc.createRoleRequest(selectedRole, selectedUser);
		*/
		RequestDispatcher rd  = getServletContext().getRequestDispatcher("/CheckID.html");
		rd.forward(request, response);
	}

}

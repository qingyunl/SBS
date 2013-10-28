package com.asu.ss.secure_banking_system.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asu.ss.secure_banking_system.model.RoleEntity;
import com.asu.ss.secure_banking_system.model.RoleType;
import com.asu.ss.secure_banking_system.model.UserEntity;
import com.asu.ss.secure_banking_system.service.RequestRoleService;
import com.sbs.model.user.User;

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
		
		User selectedUser = new User();
		selectedUser.setUserID(request.getParameter("userSelect"));
		RoleType selectedRole = RoleType.valueOf(request.getParameter("roleSelect"));
		RequestRoleService rrsvc= new RequestRoleService();
		User requestingUser = new User();
		HttpSession session = request.getSession();
		requestingUser.setUserID((String)session.getAttribute("UserID"));
		rrsvc.createRoleRequest(requestingUser, selectedUser, selectedRole);
		
		//RequestDispatcher rd  = getServletContext().getRequestDispatcher("/CheckID.html");
		RequestDispatcher rd  = getServletContext().getRequestDispatcher("/admin_notif.html");
		rd.forward(request, response);
	}

}

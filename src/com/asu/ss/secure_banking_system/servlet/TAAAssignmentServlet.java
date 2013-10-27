package com.asu.ss.secure_banking_system.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ss.secure_banking_system.model.TAARequestEntity;
import com.asu.ss.secure_banking_system.service.TAAAssignmentService;
import com.sbs.model.user.User;

/**
 * Servlet implementation class TAAAssignmentServlet
 */
@WebServlet("/TAAAssignmentServlet")
public class TAAAssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TAAAssignmentServlet() {
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
		boolean isValidRequest= (Boolean)request.getSession().getAttribute("isValidTAARequest");
		
		if(isValidRequest==true)
		{
			TAARequestEntity taa = (TAARequestEntity)request.getSession().getAttribute("TAARequestToValidate");
			User user = new User();
			user.setUserID(request.getParameter("assignedUser"));
			TAAAssignmentService svc = new TAAAssignmentService();
			svc.assignTAAtoUser(taa, user);
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/taa_notification.html");
		rd.forward(request, response);
	}

}

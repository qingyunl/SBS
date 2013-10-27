package com.asu.ss.secure_banking_system.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ss.secure_banking_system.model.UserEntity;
import com.asu.ss.secure_banking_system.service.TAARequestService;
import com.sbs.model.user.User;

/**
 * Servlet implementation class TAARequest
 */
@WebServlet("/TAARequest")
public class TAARequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TAARequest() {
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
		// TODO Auto-generated method stub
		try{
		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String description = request.getParameter("description");
		TAARequestService taasvc = new TAARequestService();
		User ue = new User();
		ue.setUserID(request.getSession().getAttribute("UserID").toString());
		taasvc.getUserVerfied(username, dob, email);
		taasvc.createTAARequest(ue, description);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin_notif.html");
		rd.forward(request, response);
		}
		catch(Exception exception)
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin_notif.html");
			rd.forward(request, response);
			exception.printStackTrace();
		}
	}

}

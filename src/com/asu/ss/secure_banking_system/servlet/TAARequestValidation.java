package com.asu.ss.secure_banking_system.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asu.ss.secure_banking_system.model.TAARequestEntity;
import com.asu.ss.secure_banking_system.service.TAARequestService;

/**
 * Servlet implementation class TAARequestValidation
 */
@WebServlet("/TAARequestValidation")
public class TAARequestValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TAARequestValidation() {
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
		RequestDispatcher rd=null;		
		// TODO Auto-generated method stub
		try{

		TAARequestEntity taa =(TAARequestEntity) (request.getAttribute("TAARequestToValidate"));
		TAARequestService svc = new TAARequestService();
		HttpSession session = request.getSession();
		if(svc.checkIfValidTAARequest(taa))
		{
			session.setAttribute("isValidTAARequest", true);
		}
		else
		{
			session.setAttribute("isValidTAARequest", false);			
		}
		rd = getServletContext().getRequestDispatcher("/assign_taa.html");
		rd.forward(request, response);
		}
		catch(Exception exception)
		{
			rd = getServletContext().getRequestDispatcher("/assign_taa.html");
			rd.forward(request, response);
			exception.printStackTrace();
		}
	}

}

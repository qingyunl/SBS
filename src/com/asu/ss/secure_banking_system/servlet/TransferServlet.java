package com.asu.ss.secure_banking_system.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ss.secure_banking_system.model.TranConfResult;
import com.asu.ss.secure_banking_system.model.TransferService;
import com.asu.ss.secure_banking_system.model.TypeOfTransfer;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet(description = "Transfer money between Accounts", urlPatterns = { "/TransferServlet" })
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
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

		TranConfResult tranConfResult;
		TransferService transferService;
		request.removeAttribute("exception");
		try
		{
			String fromAccount = request.getParameter("fromAccount");
			if(fromAccount.isEmpty())
				throw new Exception("From Account must be entered!");
			
			String toAccount = request.getParameter("toAccount");
			if(toAccount.isEmpty())
				throw new Exception("To Account must be entered!");
			
			String amount = request.getParameter("amount");
			if(amount.isEmpty())
				throw new Exception("Amount must be entered!");
			
			double dAmount = Double.valueOf(amount);
			
			if(fromAccount.equalsIgnoreCase(toAccount))
				throw new Exception("From Account and To Account must be different!");
			
			transferService = new TransferService(fromAccount,toAccount,TypeOfTransfer.TRANSFER,dAmount,"SATYA1");
			transferService.fundTransferBetweenAccounts();
			
			tranConfResult = transferService.getTranConfResult();
			request.setAttribute("tranConfResult", tranConfResult);
			request.getRequestDispatcher("webpages/resultPage.jsp").forward(request, response);
			
		}
		catch(Exception e)
		{
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("webpages/Transfer.jsp").forward(request, response);
		}
	}

}

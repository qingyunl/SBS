package com.sbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;







import org.hibernate.Query;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;











import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sbs.model.combined.UserandOTP;
import com.sbs.model.notification.Notification;
import com.sbs.model.notification.NotificationList;
import com.sbs.model.notification.NotificationManager;
import com.sbs.model.otp.OneTimePasswd;
import com.sbs.model.transaction.Transaction;
import com.sbs.model.transaction.TransactionManager;
import com.sbs.model.user.User;
import com.sbs.model.user.UserManager;


@Controller("SBSUserController")
public class SBSUserController {
	
	//testing purpose
	@RequestMapping("/requestrole")
    public String requestrPage(Model model) {	
		User user = new User();
        model.addAttribute("user", user);
		//need LDAP server to check UserID
        return "request_role";
    }
	@RequestMapping("/admin_notif")
    public String adminnot(Model model) {	
//		User user = new User();
//        model.addAttribute("user", user);
		//need LDAP server to check UserID
        return "admin_notification";
    }
	
	@RequestMapping("/CheckID")
    public String IDcheck(Model model) {	
		User user = new User();
        model.addAttribute("user", user);
		//need LDAP server to check UserID
        return "CheckUserID";
    }
	@RequestMapping(value="/checkuserid", method = RequestMethod.POST)
	public String checkid(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception{
		String userID = UserManager.userIDCheck(user.getUserID());
		session.setAttribute("UserID", userID);
		
		if(userID==null)
		{
			return "CheckUserID";
		}
		//System.out.println(userID);
		else{
		return "loginPage"; 
		}
	}
	@RequestMapping(value="/loginFunction", method = RequestMethod.POST)
	public String loginf(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception{
		boolean b = UserManager.passwordcheck(user.getUserID(),"haha");
		
		
		if(b==false)
		{
			return "loginPage";
		}
		//System.out.println(userID);
		else{
			model.addAttribute("Emailid", UserManager.retrieiveUser((String)session.getAttribute("UserID")).getEmailid());
			model.addAttribute("Dob", UserManager.retrieiveUser((String)session.getAttribute("UserID")).getDOB());
			model.addAttribute("Contact",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getContact());
			model.addAttribute("Address",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getAddress());
			model.addAttribute("Firstname",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getFirstname());
			model.addAttribute("Lastname",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getLastname());
			model.addAttribute("SecureQ1",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ1());
			model.addAttribute("SecureQ2",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ2());
			model.addAttribute("SecureQ3",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ3());		
//			session.setAttribute("Emailid", UserManager.retrieiveUser((String)session.getAttribute("UserID")).getEmailid());
//			session.setAttribute("Date of Birth", UserManager.retrieiveUser((String)session.getAttribute("UserID")).getDOB());
//			session.setAttribute("Contact",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getContact());
//			session.setAttribute("Address",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getAddress());
//			session.setAttribute("Firstname",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getFirstname());
//			session.setAttribute("Lastname",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getLastname());
//			session.setAttribute("SecureQ1",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ1());
//			session.setAttribute("SecureQ2",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ2());
//			session.setAttribute("SecureQ3",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ3());
		return "Profile";
		}
	}
	@RequestMapping("/forgetPasswordPage")
	public String forget(@ModelAttribute("user") User user, Model model, HttpSession session){
		session.setAttribute("SecureQ1",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ1());
		session.setAttribute("SecureQ2",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ2());
		session.setAttribute("SecureQ3",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ3());
		return "forgetPassword";
	}
	
	@RequestMapping(value = "/forgetPassFunction", method = RequestMethod.POST)
	public String forgetpasswordfuntion(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception{
		String message = UserManager.forgetpasswordcheck((String)session.getAttribute("UserID"), user.getDOB(), user.getSecureA1(), user.getSecureA2(), user.getSecureA3());
		System.out.print(message);
		UserandOTP userandotp = new UserandOTP() ;
		model.addAttribute("userandotp",userandotp);
		if(message ==null)
		{
			OneTimePasswd otp = new OneTimePasswd();
			otp.setUserId((String)session.getAttribute("UserID"));
			otp.insertOTPCodeForUser();
			return "NewPasswordSetup";
		}
		else
		{
			return "forgetPassword";
		}
	}
	
	@RequestMapping(value = "/newpasswdfunction", method = RequestMethod.POST)
	public String newpassfunction(@ModelAttribute("userandotp") UserandOTP userandotp, Model model, HttpSession session,  HttpServletRequest request) throws Exception{
		
		OneTimePasswd otp = new OneTimePasswd();

		otp.setUserId((String) session.getAttribute("UserID"));
		boolean bo =  otp.checkTheUserEnteredOTPCodeNoException(userandotp.getOtpcode());
		if (!bo)
		{
			
			
			otp.insertOTPCodeForUser();
	    	String referer = request.getHeader("Referer");
	        return "redirect:"+referer;
		}
		else{
			
			User user = new User();
	        model.addAttribute("user", user);
			return "CheckUserID";
		}
		
		
	}
	
	
	
	@RequestMapping("/changecontactPage")
	public String changecPage(@ModelAttribute("user") User user, Model model){
		return "ChangeContactInfo";
	}
	
	@RequestMapping("/changesecureQAPage")
	public String changesQAPage(@ModelAttribute("user") User user, Model model){
		return "ChangeSecurityQ";
	}
	
	@RequestMapping("/addUser")
    public String rdraddUser(@ModelAttribute("user") User user,Model model) {	
        return "addUser";
    }
	
	@RequestMapping("/deleteUserPage")
    public String rdrdeleteFadd(@ModelAttribute("user") User use,Model model) {	
        return "deleteUser";
    }
	@RequestMapping("/transferUserPage")
	public String transfU(@ModelAttribute("user") User user, Model model){
		return "transferUser";
	}

	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteU(@ModelAttribute("user") User user, Model model){
		UserManager.deleteuser(user.getUserID());
		return "result";
	}
	
	@RequestMapping(value = "/transferuser", method = RequestMethod.POST)
	public String transfUse(@ModelAttribute("user") User user, Model model){
		UserManager.transferuser(user.getUserID(), user.getDeptID());
		return "result";
	}
	
	@RequestMapping(value = "/changecontactin", method = RequestMethod.POST)
	public String changeCon(@ModelAttribute("user") User user, Model model,HttpSession session){
		UserManager.changeContactFunction((String)session.getAttribute("UserID"), user.getContact(), user.getAddress());
//		model.addAttribute("Contact",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getContact());
//		model.addAttribute("Address",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getAddress());
		model.addAttribute("Emailid", UserManager.retrieiveUser((String)session.getAttribute("UserID")).getEmailid());
		model.addAttribute("Dateofbirth", UserManager.retrieiveUser((String)session.getAttribute("UserID")).getDOB());
		model.addAttribute("Contact",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getContact());
		model.addAttribute("Address",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getAddress());
		model.addAttribute("Firstname",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getFirstname());
		model.addAttribute("Lastname",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getLastname());
		model.addAttribute("SecureQ1",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ1());
		model.addAttribute("SecureQ2",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ2());
		model.addAttribute("SecureQ3",UserManager.retrieiveUser((String)session.getAttribute("UserID")).getSecureQ3());		
		System.out.println(UserManager.retrieiveUser((String)session.getAttribute("UserID")).getDOB());
		return "Profile";
	}

	@RequestMapping("/userinfo")
    public String userInfo(Model model) {
		User user = new User();
        model.addAttribute("user", user);
        return "userinfo";
    }
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String create(@ModelAttribute("user") User user, Model model,HttpServletRequest request) {
		String message = "";    	
//		System.out.println(user.getSecureQ1());
//		System.out.println(user.getSecureQ2());
//		System.out.println(user.getSecureQ3());
//		System.out.println(user.getSecureA1());
//		System.out.println(user.getSecureA2());
//		System.out.println(user.getSecureA3());
    	message = UserManager.createUser(user.getFirstname(), user.getLastname(), user.getUserID(), user.getEmailid(),
    			user.getAddress(), user.getContact(), user.getDOB(),user.getSecureQ1(), user.getSecureQ2(),
    			user.getSecureQ3(), user.getSecureA1(), user.getSecureA2(), user.getSecureA3(), 
    			user.getIdtype(), user.getIdNo(), user.getDeptID(), user.getRoleID());
    	
    	model.addAttribute("message", message);
    	String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }
	
	@RequestMapping("/External_TXN")
    public ModelAndView extTxn() {
		//List<Transaction> tList = TransactionManager.getAllTransactions();
		List<Transaction> tList = TransactionManager.getExtTransactions();
		ModelAndView mv = new ModelAndView("/External_TXN");
		mv.addObject("extTxnList", tList);
        return mv;
    }
	
	@RequestMapping("/Internal_TXN")
    public ModelAndView intTxn() {
		List<Transaction> tList = TransactionManager.getIntTransactions();
		ModelAndView mv = new ModelAndView("/Internal_TXN");
		mv.addObject("intTxnList", tList);
        return mv;
    }
	
	@RequestMapping("/System_Log")
    public ModelAndView sysLog() {	
		List<Transaction> tList = TransactionManager.getSystemLog();
		ModelAndView mv = new ModelAndView("/System_Log");
		mv.addObject("sysLogList", tList);
        return mv;
    }
	
	@RequestMapping("/notifications")
    public ModelAndView notifications() {	
		List<Notification> notList = NotificationManager.getNotifications("kdvyas");
		ModelAndView mv = new ModelAndView("/notifications");
		NotificationList nList = new NotificationList();
		nList.setNotifications(notList);
		mv.addObject("notificationList", nList);
        return mv;
    }
	
	@RequestMapping(value = "/notificationRequest", method = RequestMethod.POST)
    public String notificationRequest(@ModelAttribute("notificationList") NotificationList nList, HttpServletRequest request) {	
		//List<Notification> nList = NotificationManager.getNotifications("kdvyas");
		//ModelAndView mv = new ModelAndView("/notifications");
		//mv.addObject("notificationList", nList);
		for (Notification n : nList.getNotifications()) {
			if ("A".equals(n.getStatus()) || "D".equals(n.getStatus()))
			NotificationManager.updateNotification(n);
		}
		String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
	
	@RequestMapping(value = "/intTxnRequest", method = RequestMethod.POST)
    public String intTxnRequest(@RequestParam(value = "selectedId", required=true) int[] txnIds, HttpServletRequest request) {
        for (int txnId : txnIds) {
        	// get transaction for both the parties
        	Transaction t = TransactionManager.getTransaction(txnId);
        	if (t.getFromUserId() != null || !t.getFromUserId().equals("")) {
				Notification n = new Notification(t.getFromUserId(), "requester", "N", txnId);
				NotificationManager.createNotification(n);
        	}
        	if (t.getToUserId() != null || !t.getToUserId().equals("")) {
				Notification n = new Notification(t.getToUserId(), "requester", "N", txnId);
				NotificationManager.createNotification(n);
        	}
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

}

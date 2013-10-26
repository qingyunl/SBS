package com.sbs.model.combined;


import com.sbs.model.otp.OneTimePasswd;
import com.sbs.model.otp.OneTimePasswordEntity;
import com.sbs.model.user.User;



public class UserandOTP {
	User user;
	String otpcode;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOtpcode() {
		return otpcode;
	}
	public void setOtpcode(String otpcode) {
		this.otpcode = otpcode;
	}



}

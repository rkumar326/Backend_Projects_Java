package com.infy.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OTPUtility {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Integer sendOTP() {
		logger.info("Inside sendOTP() method of {}", this.getClass());
		double num = Math.random() * 1000000;
		Integer OTP = (int) num;
		return OTP;
	}

}
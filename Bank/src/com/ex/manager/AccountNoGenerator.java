package com.ex.manager;

public class AccountNoGenerator {
	private static final String BANK_CODE = "ABC";
	private static int ACCOUNT_NUM = 1000;
	
	public static String generateAccountNo() {
		return BANK_CODE + ACCOUNT_NUM++;
	}
}

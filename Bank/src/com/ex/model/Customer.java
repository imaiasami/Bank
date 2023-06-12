package com.ex.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private String ssn;
	private List<Account> accounts;
	
	public Customer(String name, String ssn) {
		this.name = name;
		this.ssn = ssn;
		accounts = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", ssn=" + ssn + ", accounts=" + accounts + "]";
	}

}

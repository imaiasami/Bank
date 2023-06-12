package com.ex.model;

import com.ex.exception.NotEnoughBalanceException;
import com.ex.manager.AccountNoGenerator;

public class Account {
	
	private String accountNo;
	private long balance;
		
	// getters, setters
	public Account() {
		accountNo = AccountNoGenerator.generateAccountNo();
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	public void deposit(long amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("입금액은 음수가 될 수 없습니다.");
		}
		this.balance += amount;
	}
	
	public void withdraw(long amount) throws NotEnoughBalanceException {
		if (amount < 0) {
			throw new IllegalArgumentException("출금액은 음수가 될 수 없습니다.");
		}
		if (balance < amount) {
			throw new NotEnoughBalanceException("잔고가 부족합니다.");			
		}
		this.balance -= amount;
	}
	
	public void transfer(Account toAccount, long amount) throws NotEnoughBalanceException {
		withdraw(amount);
		toAccount.deposit(amount);
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	
}

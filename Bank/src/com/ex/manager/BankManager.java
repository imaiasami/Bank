package com.ex.manager;

import java.util.ArrayList;
import java.util.List;

import com.ex.exception.DuplicateSsnException;
import com.ex.exception.NoSuchAccountException;
import com.ex.exception.NoSuchCustomerException;
import com.ex.exception.NotEnoughBalanceException;
import com.ex.model.Account;
import com.ex.model.Customer;

public class BankManager {
	private List<Customer> customers = new ArrayList<>();
	
	// 고객등록
	public boolean addCustomer(Customer customer) throws DuplicateSsnException {
		try {
			if (searchCustomer(customer.getSsn()) != null) {
				throw new DuplicateSsnException("주민등록번호 중복");
			}
			return customers.add(customer);						
		} catch (NoSuchCustomerException e) {
			return customers.add(customer);
		}
	}
	
	// 고객검색
	public Customer searchCustomer(String ssn) throws NoSuchCustomerException {
		for (Customer customer : customers) {
			if (customer.getSsn().equals(ssn)) {
				return customer;
			}
		}
		
		throw new NoSuchCustomerException("고객 정보가 없습니다.");
	}
	
	// 계좌생성
	public Account createAccount(Customer customer, long amount) {
		Account account = new Account();
		customer.addAccount(account);
		if (amount > 0) {
			account.deposit(amount);
		}
		return account;
	}
	
	// 계좌검색
	public Account searchAccount(String accountNo) throws NoSuchAccountException {
		for (Customer customer : customers) {
			for (Account account : customer.getAccounts()) {
				if (account.getAccountNo().equals(accountNo)) {
					return account;
				}
			}
		}
		
		throw new NoSuchAccountException("계좌정보가 없습니다.");
	}
	
	// 입금하기
	public Account deposit(String accountNo, long amount) {
		try {
			Account account = searchAccount(accountNo);
			account.deposit(amount);
			return account;			
		} catch (NoSuchAccountException e) {
			return null;			
		}
	}
		
	// 출금하기
	public Account withdraw(String accountNo, long amount) throws NoSuchAccountException, NotEnoughBalanceException {
		Account account = searchAccount(accountNo);
		account.withdraw(amount);
		return account;
	}
	
	// 이체하기
	public Account transfer(String fromAccountNo, String toAccountNo, long amount) throws NoSuchAccountException, NotEnoughBalanceException {
		Account fromAccount = searchAccount(fromAccountNo);
		Account toAccount = searchAccount(toAccountNo);
			
		fromAccount.transfer(toAccount, amount);
		return fromAccount;
	}
	
}

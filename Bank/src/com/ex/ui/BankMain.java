package com.ex.ui;

import java.util.*;

import com.ex.exception.DuplicateSsnException;
import com.ex.exception.NoSuchAccountException;
import com.ex.exception.NoSuchCustomerException;
import com.ex.exception.NotEnoughBalanceException;
import com.ex.manager.BankManager;
import com.ex.model.Account;
import com.ex.model.Customer;

public class BankMain {
	
	private Scanner scanner;
	private BankManager manager;
	
	public BankMain() {
		scanner = new Scanner(System.in);
		manager = new BankManager();
		while (true) {
			mainMenu();
			int input = scanner.nextInt();
			
			switch (input) {
				case 1: customerMenu(); break;
				case 2: accountMenu(); break;
				case 3: deposit(); break;
				case 4: withdraw(); break;
				case 5: transfer(); break;
				case 6: 
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				default: System.out.println("잘못 입력 했습니다.");
			}
		}
	}
	
	public void mainMenu() {
		System.out.println("[ ABC Bank ]");
		System.out.println("1. 고객관리");
		System.out.println("2. 계좌관리");
		System.out.println("3. 입금하기");
		System.out.println("4. 출금하기");
		System.out.println("5. 이체하기");
		System.out.println("6. 프로그램 종료");		
		System.out.print("선택> ");		
	}
	
	public void customerMenu() {
		while (true) {			
			System.out.println("[ 고객관리 ]");
			System.out.println("1. 고객등록");
			System.out.println("2. 고객검색");
			System.out.println("3. 상위매뉴로");
			System.out.print("선택> ");
			
			int input = scanner.nextInt();
			switch (input) {
				case 1: makeCustomer(); break;
				case 2: findCustomer(); break;
				case 3: return;
				default: System.out.println("다시 입력해 주세요");
			}
		}
	}
	
	public void accountMenu() {
		while (true) {			
			System.out.println("[ 계좌관리 ]");
			System.out.println("1. 계좌생성");
			System.out.println("2. 계좌검색");
			System.out.println("3. 상위매뉴로");
			System.out.print("선택> ");
			
			int input = scanner.nextInt();
			switch (input) {
				case 1: makeAccount(); break;
				case 2: findAccount(); break;
				case 3: return;
				default: System.out.println("다시 입력해 주세요");
			}
		}
	}
	
	// 고객등록
	public void makeCustomer() {
		System.out.println("[ 고객등록 ]");
		System.out.print("이름: ");
		String name = scanner.next();
		System.out.print("주민번호: ");
		String ssn = scanner.next();
		
		Customer customer = new Customer(name, ssn);
		try {
			if (manager.addCustomer(customer)) {
				System.out.println("고객등록 성공");
			}
		} catch (DuplicateSsnException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 고객검색
	public void findCustomer() {
		System.out.print("검색할 고객의 주민번호: ");
		String ssn = scanner.next();
		try {
			Customer searchCustomer = manager.searchCustomer(ssn);
			System.out.println(searchCustomer);
		} catch (NoSuchCustomerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 계좌생성
	public void makeAccount() {
		System.out.print("계좌를 생성할 고객의 주민번호: ");
		String ssn = scanner.next();
		try {
			Customer customer = manager.searchCustomer(ssn);
			
			System.out.print("입금할 금액: ");
			long amount = scanner.nextLong();
			Account createdAccount = manager.createAccount(customer, amount);
			System.out.print("생성한 계좌 정보: " + createdAccount);
		} catch (NoSuchCustomerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 계좌검색
	public void findAccount() {
		System.out.print("검색할 계좌번호: ");
		String accountNo = scanner.next();
		try {
			Account searchAccount = manager.searchAccount(accountNo);
			System.out.println(searchAccount);
		} catch (NoSuchAccountException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 입금하기
	public void deposit() {
		System.out.print("입금할 계좌번호: ");
		String accountNo = scanner.next();
		try {
			Account searchAccount = manager.searchAccount(accountNo);
			
			System.out.print("입금할 금액: ");
			long amount = scanner.nextLong();
			manager.deposit(searchAccount.getAccountNo(), amount);
			System.out.println("입금 후 계좌정보: " + searchAccount);
		} catch (NoSuchAccountException e1) {
			System.out.println(e1.getMessage());
		} catch (IllegalArgumentException e2) {
			System.out.println(e2.getMessage());
		}
	}
	
	// 출금하기
	public void withdraw() {
		System.out.print("출금할 계좌번호: ");
		String accountNo = scanner.next();
		try {
			Account searchAccount = manager.searchAccount(accountNo);
			
			System.out.print("출금할 금액: ");
			long amount = scanner.nextLong();
			manager.withdraw(searchAccount.getAccountNo(), amount);
			System.out.println("출금 후 계좌정보: " + searchAccount);
		} catch (NoSuchAccountException e1) {
			System.out.println(e1.getMessage());
		} catch (NotEnoughBalanceException e2) {
			System.out.println(e2.getMessage());
		} catch (IllegalArgumentException e3) {
			System.out.println(e3.getMessage());
		}
	}
	
	// 이체하기
	public void transfer() {
		System.out.print("출금할 계좌번호: ");
		String fromAccountNo = scanner.next();
		System.out.print("이체할 계좌번호: ");
		String toAccountNo = scanner.next();
		System.out.print("이체 금액: ");
		long amount = scanner.nextLong();
		
		try {
			manager.transfer(fromAccountNo, toAccountNo, amount);
		} catch (NoSuchAccountException e) {
			System.out.println(e.getMessage());
		} catch (NotEnoughBalanceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new BankMain();
	}
}

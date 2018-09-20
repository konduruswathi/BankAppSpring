package com.capgemini.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.exceptions.LowBalanceException;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		//ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	
		BankAccountController bankAccountController=
			context.getBean("bankAccountController",BankAccountController.class);
	System.out.println(bankAccountController.getBalance(1234));
	try {
bankAccountController.fundTransfer(1236, 1234, 5000);
System.out.println(bankAccountController.getBalance(1236));
	
bankAccountController.withdraw(1236, 1000);
System.out.println(bankAccountController.getBalance(1236));
	
	bankAccountController.deposit(1235, 20000);
System.out.println(bankAccountController.getBalance(1235));
	
}catch(LowBalanceException e) {
	System.out.println(e.getMessage());
	}

}
}

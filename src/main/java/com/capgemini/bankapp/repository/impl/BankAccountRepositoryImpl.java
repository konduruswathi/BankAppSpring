package com.capgemini.bankapp.repository.impl;

import java.util.HashSet;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccounRepository;

public class BankAccountRepositoryImpl implements BankAccounRepository {
private HashSet<BankAccount> bankAccounts;

	public BankAccountRepositoryImpl() {
	super();
	bankAccounts=new HashSet<>();
	bankAccounts.add(new BankAccount(1234,"swathi","SAVING",30000));
	bankAccounts.add(new BankAccount(1235,"nikitha","SAVING",40000));
	bankAccounts.add(new BankAccount(1236,"vasantha","CURRENT",50000));
}
@Override
	public double getBalance(long accountId) {
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId()==accountId) {
				return bankAccount.getAccountBalance();
		}
			
			}
		return -1;
		
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId()==accountId)
			{
				bankAccount.setAccountBalance(newBalance);
				return true;
			}
		}
		return false;
	}
}
	

package com.capgemini.bankapp.service.impl;

import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.repository.BankAccounRepository;
import com.capgemini.bankapp.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {
	
private BankAccounRepository  bankAccountRepository;

public void setBankAccountRepository(BankAccounRepository bankAccountRepository) {
	this.bankAccountRepository = bankAccountRepository;
}
	@Override
	public double getBalance(long accountId) {
		
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		
		double balance = bankAccountRepository.getBalance(accountId);
		if(balance != -1) {
			if(balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			}
			else 
				throw new LowBalanceException("You don't have sufficient fund");
		}
		return balance;
	}
	@Override
	public double deposit(long accountId, double amount) {
		
		double balance = bankAccountRepository.getBalance(accountId);
		if(balance != -1) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException {
		
		double balance = withdraw(fromAccount, amount);
		if(balance != -1) {
			if (deposit(toAccount, amount) == -1) {
				deposit(fromAccount, amount);
				return false;
			}
			return true;
		}
		return false;
	}

}


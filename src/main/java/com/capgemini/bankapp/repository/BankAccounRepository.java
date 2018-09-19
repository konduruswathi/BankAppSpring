package com.capgemini.bankapp.repository;

public interface BankAccounRepository {
public double getBalance(long accountId);
public boolean updateBalance(long accountId, double newBalance);


}

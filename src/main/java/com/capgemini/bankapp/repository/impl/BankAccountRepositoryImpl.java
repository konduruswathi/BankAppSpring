package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccounRepository;
import com.capgemini.bankapp.util.DataBaseUtil;
@Repository
public class BankAccountRepositoryImpl implements BankAccounRepository {
//private HashSet<BankAccount> bankAccounts;
@Autowired
DataBaseUtil dbutil;
	public BankAccountRepositoryImpl() {
	super();
	/*bankAccounts=new HashSet<>();
	bankAccounts.add(new BankAccount(1234,"swathi","SAVING",30000));
	bankAccounts.add(new BankAccount(1235,"nikitha","SAVING",40000));
	bankAccounts.add(new BankAccount(1236,"vasantha","CURRENT",50000));*/
}
	@Override
	public double getBalance(long accountId) {
		String query = "SELECT balance FROM bankaccount WHERE account_id = ?";
		
		try (Connection connection = dbutil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setLong(1, accountId);
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					
					return result.getDouble(1);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		return accountId;
		
	}
	@Override
	public boolean updateBalance(long accountId, double amount) {
		String query1 = "UPDATE bankaccount SET balance= ? WHERE account_id = ?";;
		try (Connection connection =dbutil.getConnection();
				PreparedStatement statement1 = connection.prepareStatement(query1)){
				statement1.setDouble(1, amount);
				statement1.setLong(2, accountId);
			
				if(statement1.executeUpdate()!=0) {
					return true;
				}
			}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	}


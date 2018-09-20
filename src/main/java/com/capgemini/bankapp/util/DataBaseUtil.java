package com.capgemini.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:configuration.properties")

public class DataBaseUtil {
@Autowired
	private Environment Envi;
public Connection getConnection()
{
	Connection connection = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connection = DriverManager.getConnection(Envi.getProperty("dburl"), Envi.getProperty("user"), Envi.getProperty("password"));
	
		if(connection!=null)
			System.out.println("connected");
	}
	catch(ClassNotFoundException |SQLException e) {
		e.printStackTrace();
}
		return connection;
	
}
	
}

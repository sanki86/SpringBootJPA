package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.app.Customer;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class CustomerRepo {
	// List<Customer> customers;
	Connection con = null;

	public CustomerRepo() {
		/*
		 * customers =new ArrayList<Customer>();
		 * 
		 * Customer customer=new Customer(); customer.setId(200);
		 * customer.setName("Sankari"); customer.setPoints(700);
		 * 
		 * Customer customer1 = new Customer(); customer1.setId(90);
		 * customer1.setName("Arush"); customer1.setPoints(900);
		 * 
		 * customers.add(customer); customers.add(customer1);
		 */

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String url = "jdbc:sqlserver://DESKTOP-DLUT4SB\\SQLEXPRESS;databaseName=sanki;integratedSecurity=true";

			Properties props = new Properties();

			con = DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	List<Customer> customers = new ArrayList<Customer>();

	public List<Customer> getCustomers() {

		String sql = "Select * from customer";

		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Customer a1 = new Customer();
				a1.setId(rs.getInt(1));
				a1.setName(rs.getString(2));
				a1.setPoints(rs.getInt(3));
				a1.setCustNo(rs.getInt(4));

				customers.add(a1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customers;
	}

	public void createCustomer(Customer a1) {
		List<Customer> customers = getCustomers();

		for (Customer a : customers) {
			System.out.println("Inside to create customer");

			if (a.getCustNo() != a1.getCustNo()) {

				String sql = "INSERT INTO customer(Name,Points,CustNo)" + " values (?,?,?)";
				try {
					// Statement st = con.createStatement();
					PreparedStatement preparedStatement = con.prepareStatement(sql);
					// preparedStatement.setInt(1,a1.getId());
					preparedStatement.setString(1, a1.getName());
					preparedStatement.setInt(2, a1.getPoints());
					preparedStatement.setInt(3, a1.getCustNo());
					preparedStatement.executeUpdate();
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public Customer getCustomer(int custNo) {
		List<Customer> customers = getCustomers();
		for (Customer a : customers) {
			if (a.getCustNo() == custNo) {
				return a;
			}
		}
		return new Customer();

	}

}

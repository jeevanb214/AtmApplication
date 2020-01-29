package com.jeevan.service;

import java.util.List;

import com.jeevan.model.Customer1;

public interface CustomerService {
	
	
	public void addCustomer(Customer1 customer);	
	public List<Customer1> getAllCustomers();
	public Customer1 getBalanceByid(Integer id);
	
	

}

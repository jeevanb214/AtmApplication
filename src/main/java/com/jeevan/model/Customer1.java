package com.jeevan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Customer1 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer acNo;
	
	@Column
	private String name;

	@Column
	private Integer age;
	
	@Column
	private String city;
	
	@Column
	private Integer balance;
	
	@Column
	private Integer pin;

	public Integer getAcNo() {
		return acNo;
	}

	public void setAcNo(Integer acNo) {
		this.acNo = acNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	
	
	
	
}

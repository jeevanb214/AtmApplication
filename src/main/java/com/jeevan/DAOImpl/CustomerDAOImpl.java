package com.jeevan.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeevan.DAO.CustomerDAO;
import com.jeevan.model.Customer1;

public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void addCustomer(Customer1 customer) {
		System.out.println("i am in addCustomer Dao");
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.save(customer);
		ts.commit();
		session.close();
	}

	public List<Customer1> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer1> cl = new ArrayList<Customer1>();

		System.out.println("i am in getAllCustomers Dao");
		Session session = sessionFactory.openSession();
		// Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from Customer1");
		cl = query.list();
		session.close();
		// ts.commit();

		return cl;
	}

	public Customer1 getBalanceByid(Integer id) {
		System.out.println("in getBalanceByid DAO ");
		Session session = sessionFactory.openSession(); // this is need to be close after but where as if we use below
														// we don.t need to do that
		// Session session=sessionFactory.getCurrentSession();//We need to configure in
		// the hibernate config files
		Transaction ts = session.beginTransaction();
		Customer1 c = (Customer1) session.get(Customer1.class, id);

		System.out.println("c.getBalance()" + c.getBalance());

		System.out.println("c.getAcNo()  :" + c.getAcNo());
		return c;

	}

	public boolean verifyAccountByPin(Integer pin, Integer accNum) {
		System.out.println("I am in verifyAccountByPin DAO::: " + accNum + " " + pin);
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Customer1 c = (Customer1) session.get(Customer1.class, accNum);
		if (pin.equals(c.getPin())) {
			return true;
		} else
			return false;

	}

	public Integer withdraw(Integer accNum, Integer ammount) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Customer1 c = (Customer1) session.get(Customer1.class, accNum);
		Integer beforeAmmount = c.getBalance();
		if ((beforeAmmount - ammount) < 0)
			return 0;
		else {
			c.setBalance(beforeAmmount - ammount);
			return beforeAmmount - ammount;
		}

	}

	public void deposit(int accNum, int ammount) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Customer1 c = (Customer1) session.get(Customer1.class, accNum);
		Integer beforeAmmount = c.getBalance();
		c.setBalance(beforeAmmount + ammount);
		
	}

}

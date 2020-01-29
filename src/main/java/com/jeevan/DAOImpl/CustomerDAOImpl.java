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
		List<Customer1> cl=new ArrayList<Customer1>();
		
		System.out.println("i am in getAllCustomers Dao");
		Session session = sessionFactory.openSession();
		//Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from Customer1");
		cl = query.list();
		session.close();
		//ts.commit();

		return cl;
	}

}

package com.ajcode.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajcode.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//inject session factory
	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		//get current hibernate session
		//create a query
		//execute the query and get result list
		//return the results
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		//saveOrUpdate, if the primary key id for theCustomer is already there it will update, else(for new object) it will insert
		//for this we need to add hidden id field in the jsp form to set the id
		
		session.saveOrUpdate(theCustomer);
	}

	@Override  
	public Customer getCustomer(int theId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = session.get(Customer.class, theId);
		
		session.delete(theCustomer);		
	}

}

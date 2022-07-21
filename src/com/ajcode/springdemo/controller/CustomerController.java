package com.ajcode.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ajcode.springdemo.entity.Customer;
import com.ajcode.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the customer service
	//spring will scan for the implementation class that implements the customer DAO interface and inject it here
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listcustomers(Model theModel) {
		//get the customers from service
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add those customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create new model attribute to bind customer data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		//get the customer from database
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set the customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to out form
		//as we got the customer with values in theCustomer object
		//while loading the customer-form, the form will auto populate the values as the path="firstName" calls the getter methods implicitly in the jsp form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId, Model theModel) {
		//get the customer from database
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}

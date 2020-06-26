package com.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.app.Customer;
import com.app.CustomerRepo;

@Path("/hello")
public class MyRestResource extends Application {

	CustomerRepo repo = new CustomerRepo();

	@Path("/customers")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getCustomers() {

		System.out.println("Called in");

		return repo.getCustomers();

	}

	@GET
	@Path("/customer/{custNo}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })

	public Customer getCustomer(@PathParam("custNo") int custNo) {

		System.out.println("to get one customer");

		return repo.getCustomer(custNo);

	}

	@Path("/newCustomer")
	@POST
	public void createCustomer(Customer a1) {
		System.out.println("Customer is " + a1);

		repo.createCustomer(a1);

	}
}

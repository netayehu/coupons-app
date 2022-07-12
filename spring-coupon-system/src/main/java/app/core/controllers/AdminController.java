package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.couponSystemException;
import app.core.services.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/api/ADMINISTRATOR")
public class AdminController {

	@Autowired
	AdminService service = new AdminService();

	@PostMapping("/add-company")
	public int addCompany(@RequestBody Company company, @RequestHeader String token)  {
		try {
			return service.addCompany(company);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}

	@PutMapping("/update-company")
	public void updateCompany(@RequestBody Company company, @RequestHeader String token){
		try {
			this.service.updateCompany(company);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/delete-company/{companyId}")
	public void deleteCompany(@PathVariable int companyId, @RequestHeader String token){
		try {
			service.deleteCompany(companyId);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-all-companies")
	public List<Company> getAllCompanies(@RequestHeader String token) throws ResponseStatusException {
		try {
			List<Company> companies = service.getAllCompanies();
			return companies;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-one-company/{companyId}")
	public Company getOneCompany(@PathVariable int companyId, @RequestHeader String token){
		try {
			Company company = service.getOneCompany(companyId);
			return company;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping("/add-customer")
	public int addCustomer(@RequestBody Customer customer, @RequestHeader String token){
		try {
			int id = service.addCustomer(customer);
			return id;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}

	}

	@PutMapping("/update-customer")
	public void updateCustomer(@RequestBody Customer customer, @RequestHeader String token){
		try {
			service.updateCustomer(customer);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@DeleteMapping("/delete-customer/{customerId}")
	public void deleteCustomer(@PathVariable int customerId, @RequestHeader String token){
		try {
			service.deleteCustomer(customerId);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-all-customers")
	public List<Customer>  getAllCustomers(@RequestHeader String token) throws ResponseStatusException {

		List<Customer> customers = service.getAllCustomers();
		return customers;

	}

	@GetMapping("/get-one-customer/{customerId}")
	public Customer getOneCustomer(@PathVariable int customerId, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			Customer customer = service.getOneCustomer(customerId);
			return customer;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

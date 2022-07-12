package app.core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.couponSystemException;

@Service
@Transactional
public class AdminService extends ClientService {

	private final String adEmail = "admin@admin.com";
	private final String adPassword = "admin";

	@Override
	public boolean login(String email, String password) throws couponSystemException {

		return email.equals(this.adEmail) && password.equals(this.adPassword);
	}

	public int addCompany(Company company) throws couponSystemException {

		Optional<Company> opt = this.companyRepo.findByName(company.getName());
		if (opt.isPresent()) {
			throw new couponSystemException("addCompany faild -company name already exist");
		}
		Optional<Company> opt1 = this.companyRepo.findByEmail(company.getEmail());
		if (opt1.isPresent()) {
			throw new couponSystemException("addCompany faild -company email already exist");
		}
		company = this.companyRepo.save(company);
		return company.getId();
	}

	public void updateCompany(Company company) throws couponSystemException {
		Optional<Company> opt = this.companyRepo.findById(company.getId());
		if (opt.isPresent()) {
			Company com = opt.get();
			if (com.getName().equals(company.getName())) {
				this.companyRepo.save(company);
			} else {
				throw new couponSystemException("update faild - can not update company name");
			}
		} else {
			throw new couponSystemException("update faild -company id " + company.getId() + "not found");
		}

	}

	public void deleteCompany(int companyId) throws couponSystemException {
		Optional<Company> opt = this.companyRepo.findById(companyId);
		if (opt.isPresent()) {
			this.companyRepo.deleteById(companyId);
		} else {
			throw new couponSystemException("deleteCompany faild -company id " + companyId + "not found");
		}

	}

	public List<Company> getAllCompanies() {

		return this.companyRepo.findAll();
	}

	public Company getOneCompany(int companyId) throws couponSystemException {
		Optional<Company> opt = this.companyRepo.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new couponSystemException("getOneCompany faild -company id" + companyId + "not found");
		}
	}

	public int addCustomer(Customer customer) throws couponSystemException {
		Optional<Customer> opt = this.customerRepo.findByEmail(customer.getEmail());
		if (opt.isEmpty()) {
			customer = this.customerRepo.save(customer);
			return customer.getId();
		} else {
			throw new couponSystemException(
					"addCustomer faild -customer id" + customer.getId() + "already exist or null");
		}
	}

	public void updateCustomer(Customer customer) throws couponSystemException {
		Optional<Customer> opt = this.customerRepo.findById(customer.getId());
		if (opt.isPresent()) {
			this.customerRepo.save(customer);
		} else {
			throw new couponSystemException("updateCustomer faild -customer id" + customer.getId() + "not found");
		}

	}

	public void deleteCustomer(int customerId) throws couponSystemException {
		Optional<Customer> opt = this.customerRepo.findById(customerId);
		if (opt.isPresent()) {
			this.customerRepo.deleteById(customerId);
		} else {
			throw new couponSystemException("deleteCustomer faild -customer id" + customerId + "not found");
		}

	}

	public List<Customer> getAllCustomers() {

		return this.customerRepo.findAll();
	}

	public Customer getOneCustomer(int customerId) throws couponSystemException {
		Optional<Customer> opt = this.customerRepo.findById(customerId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new couponSystemException("getOneCustomer faild -company id" + customerId + "not found");
		}
	}
	
	public void deleteExpaierdCoupons() throws couponSystemException{
		this.couponRepo.deleteByEndDateIsBefore(LocalDate.now());
	}

}

package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Customer;
import app.core.exceptions.couponSystemException;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
	
	Optional<Customer> findByEmail(String customerEmail)throws couponSystemException;
	
	Optional<Customer> findByEmailAndPassword(String CompanyEmail, String CompanyPassword)throws couponSystemException;
		
	boolean existsByCouponsId(Integer couponId)throws couponSystemException;
	



}

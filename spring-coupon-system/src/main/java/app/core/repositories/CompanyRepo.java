package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;
import app.core.exceptions.couponSystemException;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

	Optional<Company> findByName(String CompanyName)throws couponSystemException;

	Optional<Company> findByEmail(String CompanyEmail)throws couponSystemException;
	
	Optional<Company> findByEmailAndPassword(String CompanyEmail, String CompanyPassword)throws couponSystemException;

}

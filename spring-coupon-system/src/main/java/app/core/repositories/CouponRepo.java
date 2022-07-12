package app.core.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.exceptions.couponSystemException;

public interface CouponRepo extends JpaRepository<Coupon,Integer> {
	
	
	List<Coupon> findByCompanyId(Integer companyId)throws couponSystemException;
	
	List<Coupon> findByCompanyIdAndCategory(Integer companyId, Category category)throws couponSystemException;
	
	List<Coupon> findByCompanyIdAndPriceLessThanEqual(Integer companyId, Double maxPrice)throws couponSystemException;
	
	List<Coupon> findByCustomersId(Integer customerId)throws couponSystemException;
	
	List<Coupon> findByCustomersIdAndPriceLessThanEqual(Integer customerId,Double maxPrice)throws couponSystemException;
	
	List<Coupon> findByCustomersIdAndCategory(Integer customerId,Category category)throws couponSystemException;
	
	boolean existsByTitleAndCompanyId(String couponTitle, Integer companyId)throws couponSystemException;
		
	boolean existsByIdAndCompanyId(Integer couponId, Integer companyId)throws couponSystemException;

	void deleteByEndDateIsBefore(LocalDate date)throws couponSystemException;


}

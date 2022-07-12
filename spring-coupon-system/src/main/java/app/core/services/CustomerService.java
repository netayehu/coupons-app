package app.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.couponSystemException;

@Service
@Transactional

public class CustomerService extends ClientService {

	 

	@Override
	public boolean login(String email, String password) throws couponSystemException {
		Optional<Customer> opt = this.customerRepo.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			return true;
		} else {
			return false;
		}
	}


	public void PurchaseCoupon(Coupon coupon, int customerId) throws couponSystemException {
		Optional<Coupon> opt = this.couponRepo.findById(coupon.getId());
		if (opt.isEmpty()) {
			throw new couponSystemException("PurchaseCoupon faild - coupon" + coupon.getId() + "not found");
		}
		if (this.customerRepo.existsByCouponsId(coupon.getId())) {
			throw new couponSystemException("PurchaseCoupon faild - purchase already exist");
		}
		coupon=opt.get();
		if(coupon.getAmount()>0) {
			Customer customer = this.customerRepo.findById(customerId).get();
			customer.addCoupon(coupon);
			coupon.setAmount(coupon.getAmount()-1);
		} else {
			throw new couponSystemException("PurchaseCoupon faild - coupon is out of stock");
		}

	}

	public Coupon getOneCoupon(int couponId, int companyId ) throws couponSystemException {
		Optional<Coupon> opt = this.couponRepo.findById(couponId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new couponSystemException("getOneCoupon faild -coupon id " + couponId + "not found");
		}
	}
	
	
	public List<Coupon> getAllCoupons() throws couponSystemException {
		return this.couponRepo.findAll();
	}
	
	public List<Coupon> getAllCustomerCoupons(int customerId) throws couponSystemException {
		return this.couponRepo.findByCustomersId(customerId);
	}
	
	public List<Coupon> getCustomerCouponsByCategory(Category category, int customerId) throws couponSystemException {
		return this.couponRepo.findByCustomersIdAndCategory(customerId, category);
	}

	public List<Coupon> getCustomerCouponsByMaxPrice(double MaxPrice, int customerId) throws couponSystemException {

		return this.couponRepo.findByCustomersIdAndPriceLessThanEqual(customerId, MaxPrice);
	}

	public Customer getCustomerDetails(int customerId) throws couponSystemException {

		Optional<Customer> opt = this.customerRepo.findById(customerId);
		return opt.get();

	}

}

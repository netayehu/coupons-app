package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.couponSystemException;
import app.core.jwt.util.JwtUtil;
import app.core.services.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/CUSTOMER")
public class CustomerController {

	@Autowired
	CustomerService service = new CustomerService();
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping
	public void PurchaseCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			int customerId = jwtUtil.extractClient(token).clentId;
			service.PurchaseCoupon(coupon, customerId);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}
	
	
	@GetMapping("/get-one-coupon/{couponId}")
	public Coupon getOneCoupon(@PathVariable int couponId, @RequestHeader String token ) {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			Coupon coupon = service.getOneCoupon(couponId, companyId);
			return coupon;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/get-all-customer-coupons")
	public List<Coupon> getAllCustomerCoupons(@RequestHeader String token) {
		try {
			int customerId = jwtUtil.extractClient(token).clentId;
			List<Coupon> coupons = service.getAllCustomerCoupons(customerId);
			return coupons;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-all-coupons")
	public List<Coupon> getAllCoupons(@RequestHeader String token) throws ResponseStatusException {
		try {
			List<Coupon> coupons = service.getAllCoupons();
			return coupons;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-all-coupons-category/{category}")
	public List<Coupon> getCustomerCouponsByCategory(@PathVariable Category category, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			int customerId = jwtUtil.extractClient(token).clentId;
			List<Coupon> coupons = service.getCustomerCouponsByCategory(category, customerId);
			return coupons;

		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-all-coupons-maxprice/{maxPrice}")
	public List<Coupon> getCustomerCouponsByMaxPrice(@PathVariable double maxPrice, @RequestHeader String token) {
		try {
			int customerId = jwtUtil.extractClient(token).clentId;
			List<Coupon> coupons = service.getCustomerCouponsByMaxPrice(maxPrice, customerId);
			return coupons;

		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping
	public Customer getCustomerDetails(@RequestHeader String token) {
		try {
			int customerId = jwtUtil.extractClient(token).clentId;
			Customer customer = service.getCustomerDetails(customerId);
			return customer;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

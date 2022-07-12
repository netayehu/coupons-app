package app.core.controllers;

import java.util.List;
import java.util.Optional;

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

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.couponSystemException;
import app.core.jwt.util.JwtUtil;
import app.core.services.CompanyService;

@CrossOrigin
@RestController
@RequestMapping("/api/COMPANY")
public class CompanyController {

	@Autowired
	CompanyService service = new CompanyService();

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/add-coupon")
	public int addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			return service.addCoupon(coupon, companyId);
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

	@PutMapping("/update-coupon")
	public void updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token)  {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			service.updateCoupon(coupon, companyId);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@DeleteMapping("/delete-coupon/{couponId}")
	public void deleteCoupon(@PathVariable  int couponId, @RequestHeader String token) {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			service.deleteCoupon(couponId, companyId);
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/get-all-company-coupons")
	public List<Coupon> getAllCompanyCoupons(@RequestHeader String token) {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			List<Coupon> coupons = service.getAllCompanyCoupons(companyId);
			return coupons;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-company-coupons-category/{category}")
	public List<Coupon> getCompanyCouponsByCategory(@PathVariable Category category, @RequestHeader String token){
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			List<Coupon> coupons = service.getCompanyCouponsByCategory(category, companyId);
			return coupons;
		} catch (couponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get-company-coupons-maxPrice/{maxPrice}")
	public  List<Coupon>  getCompanyCouponsByMaxPrice(@PathVariable double maxPrice, @RequestHeader String token)
			throws ResponseStatusException {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			List<Coupon> coupons = service.getCompanyCouponsByMaxPrice(maxPrice, companyId);
			return coupons;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping
	public Company getCompanyDetails(@RequestHeader String token) throws ResponseStatusException {
		try {
			int companyId = jwtUtil.extractClient(token).clentId;
			Company company = service.getCompanyDetails(companyId);
			return company;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

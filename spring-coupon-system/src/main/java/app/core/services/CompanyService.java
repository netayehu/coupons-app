package app.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.couponSystemException;

@Service
@Transactional

public class CompanyService extends ClientService {

	@Override
	public boolean login(String email, String password) throws couponSystemException {
		Optional<Company> opt = this.companyRepo.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public int addCoupon(Coupon coupon, int companyId) throws couponSystemException {
		if (this.couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
			throw new couponSystemException(
					"addCoupon faild - coupon " + coupon.getId() + " title in this company already exist");
		} else {
			Optional<Company> opt = companyRepo.findById(companyId);
			Company company = opt.get();
			company.addCoupon(coupon);
			return this.couponRepo.save(coupon).getId();

		}
	}

	public void updateCoupon(Coupon coupon, int companyId) throws couponSystemException {

		if (this.couponRepo.existsByIdAndCompanyId(coupon.getId(), companyId)) {
			Optional<Company> opt = companyRepo.findById(companyId);
			Company company = opt.get();
			coupon.setCompany(company);
			this.couponRepo.save(coupon);

		} else {
			throw new couponSystemException(
					"updateCoupon faild - coupon" + coupon.getId() + "can not update id or company");

		}

	}

	public void deleteCoupon(int couponId, int companyId) throws couponSystemException {
		Optional<Coupon> opt = this.couponRepo.findById(couponId);
		if (opt.isPresent()) {
			this.couponRepo.deleteById(couponId);
		} else {
			throw new couponSystemException("deleteCoupon faild -coupon id" + couponId + "not found");
		}

	}

	public List<Coupon> getAllCompanyCoupons(int companyId) throws couponSystemException {

		return this.couponRepo.findByCompanyId(companyId);

	}

	public List<Coupon> getCompanyCouponsByCategory(Category category, int companyId) throws couponSystemException {

		return this.couponRepo.findByCompanyIdAndCategory(companyId, category);

	}

	public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice, int companyId) throws couponSystemException {

		return this.couponRepo.findByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);

	}

	public Company getCompanyDetails(int companyId) throws couponSystemException {
		Optional<Company> opt = this.companyRepo.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new couponSystemException("getCompanyDetails faild -company id " + companyId + "not found");
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

}

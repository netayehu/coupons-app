package app.core.threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.exceptions.couponSystemException;
import app.core.services.AdminService;

@Component
public class JobSchedualed {
	
	@Autowired
	private AdminService adminService;

	@Scheduled(cron="0 0 * * * *")
	public void job() throws couponSystemException {

		try {
			System.out.println("job works");
			this.adminService.deleteExpaierdCoupons();
			

		} catch (couponSystemException e) {
			e.getMessage();
		}

	}

}

package app.core.threads;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.core.exceptions.couponSystemException;
import app.core.services.AdminService;

//@Component
public class CouponExpirationDailyJob implements Runnable {

	private boolean quit;
	@Autowired
	private AdminService adminService=new AdminService();
	private Thread thread = new Thread(this, "CouponExpirationDailyJob");

	@Override
	public void run() {

		while (!quit) {
			try {
				this.adminService.deleteExpaierdCoupons(); 
				System.out.println("delete all expired coupons");
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException | couponSystemException e) {
				System.out.println("job interrupted and will stop");
				break;

			}
		}

	}
	
	@PostConstruct
	public void startJob() {
		this.thread.start();
		System.out.println("daily job started");
	}

	@PreDestroy
	public void stopJob() {
		this.quit = true;
		this.thread.interrupt();
		System.out.println("daily job stoped");
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}


}

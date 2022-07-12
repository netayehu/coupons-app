package app.core;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.couponSystemException;
import app.core.login.LoginManager;
import app.core.login.clientType.ClientType;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

//@Component
//public class TestAll  implements CommandLineRunner{

//	@Autowired
//	LoginManager loginManager;
//	
//	
//	public void testAll() throws couponSystemException {
//
//		try {
//
//			System.out.println("======= testAll start");
//			doAdminTest();
//			doCompanyTest();
//			doCustomerTest();
//
//			System.out.println("============= testAll done");
//		} catch (Exception e) {
//			throw new couponSystemException("TestAll faild - " + e);
//
//		} finally {
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				e.getMessage();
//			}
//
//		}
//
//	}
//
//	private void doAdminTest() {
//		try {
//
//			AdminService service = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINSTRATOR);
//
//			if (service !=null) {
//
//				System.out.println("=========== admin test start");
//				Company company = new Company(0,"rrr", "rrr@mail.com", "rrrPass",null);
//				int a=service.addCompany(company);
//				Company company1 = new Company(a,"rrr", "nnn@mail.com", "nnnPass",null);
//				Company company2 = new Company(0,"aaa", "aaa@mail.com", "aaaPass",null);
//				service.addCompany(company2);
//				Company company3 = new Company(0,"bbb", "bbb@mail.com", "bbbPass",null);
//				service.addCompany(company3);
//
//				service.updateCompany(company1);
//				service.deleteCompany(a);
//				System.out.println(service.getAllCompanies());
//				System.out.println(service.getOneCompany(a+1));
//				
//				Customer customer = new Customer(0,"Dani", "Levi", "dani@mail", "daniPass",null);
//				int b=service.addCustomer(customer);
//				Customer customer1 = new Customer(b,"Dani", "Levi", "Levi@mail", "LeviPass",null);
//				service.updateCustomer(customer1);
//				service.deleteCustomer(b);
//				Customer customer2 = new Customer(0, "aaa", "aaa", "aaa@mail", "aaaPass",null);
//				service.addCustomer(customer2);
//				
//				System.out.println(service.getAllCustomers());
//				System.out.println(service.getOneCustomer(b+1));
//				System.out.println("======== admin test done");
//
//			} else {
//				System.out.println("login faild");
//			}
//		} catch (couponSystemException e) {
//			System.err.println("error- " + e.getMessage());
//			e.printStackTrace();
//		}
//
//	}
//
//	private void doCompanyTest() {
//
//		try {
//
//			CompanyService service = (CompanyService) loginManager.login("aaa@mail.com", "aaaPass", ClientType.COMPANY);
//			
//
//
//			if (service !=null) {
//				System.out.println("=========== company test start");
//				Coupon coupon = new Coupon(0, null,  Category.ELECTRICITY,"rrr", "rrr", LocalDate.of(2022, 1, 1),
//						LocalDate.of(2022, 12, 31), 30, 20.0, "image",null);
//				int a=service.addCoupon(coupon);
//				Coupon coupon1 = new Coupon(a, null,  Category.ELECTRICITY,"rrr", "rrr", LocalDate.of(2022, 1, 1),
//						LocalDate.of(2022, 12, 31), 50, 100.0, "image1",null);
//				service.updateCoupon(coupon1);
//				Coupon coupon2 = new Coupon(0, null, Category.FOOD, "sweet", "sugar",
//						LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), 30, 20.0, "image2",null);
//				service.addCoupon(coupon2);
//				service.deleteCoupon(a);
//				System.out.println(service.getAllCompanyCoupons());
//				System.out.println(service.getCompanyCouponsByCategory(Category.FOOD));
//				System.out.println(service.getCompanyCouponsByMaxPrice(30));
//				System.out.println(service.getCompanyDetails());
//				System.out.println("=========== company test done");
//			} else {
//				System.out.println("login faild");
//			}
//		} catch (couponSystemException e) {
//			System.err.println("error- " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	private void doCustomerTest() {
//
//		try {
//
//			CustomerService service = (CustomerService) loginManager.login("aaa@mail", "aaaPass", ClientType.CUSTOMER);
//
//			
//			if (service !=null) {
//				System.out.println("============ customer test start");
//				Coupon coupon = new Coupon(2, null,  Category.ELECTRICITY,"lll", "ggg", LocalDate.of(2022, 1, 1),
//						LocalDate.of(2022, 12, 31), 30, 20.0, "image",null);
//				service.PurchaseCoupon(coupon);
//				System.out.println(service.getAllCustomerCoupons());
//				System.out.println(service.getCustomerCouponsByCategory(Category.FOOD));
//				System.out.println(service.getCustomerCouponsByMaxPrice(30));
//				System.out.println(service.getCustomerDetails());
//				System.out.println("=========== customer test done");
//			} else {
//				System.out.println("login faild");
//			}
//		} catch (couponSystemException e) {
//			System.err.println("error- " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		testAll();
//	}
//
//}

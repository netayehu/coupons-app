package app.core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.core.exceptions.couponSystemException;
import app.core.login.clientType.ClientType;
import app.core.services.ClientService;

@Component
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	

	public ClientService login(String email, String password, ClientType clientType) throws couponSystemException{
		
		
		ClientService service;
		
		switch (clientType) {
		case ADMINISTRATOR:
			service=ctx.getBean("adminService", ClientService.class);
			break;
		case COMPANY:
			service=ctx.getBean("companyService", ClientService.class);
			break;
		case CUSTOMER:
			service=ctx.getBean("customerService", ClientService.class);
			break;
		default:
			return null;
		}
		if (service.login(email,password)) {
			return service;
		}
		return null;
	}
		
	

}

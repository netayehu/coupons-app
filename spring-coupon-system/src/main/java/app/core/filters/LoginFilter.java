package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.jwt.util.JwtUtil;
import app.core.jwt.util.JwtUtil.ClientDetails;
import app.core.login.clientType.ClientType;

public class LoginFilter implements Filter {

	private JwtUtil jwtUtil;

	public LoginFilter(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "*");
		resp.addHeader("Access-Control-Allow-Methods", "*");
		
		String token = req.getHeader("token");
		String path = req.getRequestURI();
		System.out.println("get in do filter");
		
		if (token == null && req.getMethod().equals("OPTIONS")) {
			System.out.println("this is preflight request: " + req.getMethod());
			chain.doFilter(request, response);
			return;
		}
		
		ClientType client = jwtUtil.extractClient(token).getClientType();

		System.out.println("===== LOGIN FILTER TOKEN: " + token);


		try {
			if (!jwtUtil.isTokenExpired(token)) {
				if (path.contains(client.toString())) {
					System.out.println("===== LOGIN FILTER: " + client.toString());
					chain.doFilter(request, response);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "not logged in");
		}
		
		resp.sendError(HttpStatus.UNAUTHORIZED.value(), "not logged in");
		

	}
}

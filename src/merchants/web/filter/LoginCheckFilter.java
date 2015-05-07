package merchants.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;

public class LoginCheckFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (ActionContext.getContext().getSession().get("user") == null) {
			System.out.println("user==null");
			// req.getRequestDispatcher("/canvassGroup/front/hRPerson_loginUI").forward(request,
			// response);
			res.sendRedirect("/canvassGroup/front/hRPerson_loginUI");
		} else {
			System.out.println("user!=null");
			chain.doFilter(req, res);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

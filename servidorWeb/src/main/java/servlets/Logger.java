package servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import ua_parser.Parser;
import ua_parser.Client;

@WebFilter("/*")
public class Logger implements Filter {
	private Parser parser = new Parser();
    public Logger() { }
	public void destroy() { }

	private String parseBrowser(HttpServletRequest request) {
		Client agent = parser.parse(request.getHeader("User-Agent"));
		return agent.userAgent.family;
	}
	private String parseOS(HttpServletRequest request) {
		Client agent = parser.parse(request.getHeader("User-Agent"));
		return agent.os.family;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(request.getRemoteAddr()+((HttpServletRequest) request).getRequestURI().toString()+
				parseBrowser((HttpServletRequest)request)+parseOS((HttpServletRequest)request));
		//futura funcion para loggear(request.getRemoteAddr(),((HttpServletRequest) request).getRequestURI().toString(),
			//	parseBrowser((HttpServletRequest)request),parseOS((HttpServletRequest)request));
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Starting logger...");
	}

}

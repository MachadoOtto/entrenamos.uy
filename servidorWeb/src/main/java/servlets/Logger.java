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

import datatypes.DtFecha;
import models.LaFabricaWS;
import tools.Cnv;
import ua_parser.Parser;
import webservices.LogEntryWS;
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
		chain.doFilter(request, response);
		LogEntryWS e = new LogEntryWS();
		e.setSo(parseOS((HttpServletRequest)request));
		e.setBrowser(parseBrowser((HttpServletRequest)request));
		e.setIp(request.getRemoteAddr());
		e.setDate(Cnv.fecha(new DtFecha()));
		String uri = request.getScheme() + "://" +   					// "http" + "://
	             request.getServerName() +       						// "myhost"
	             ":" +                           						// ":"
	             request.getServerPort() +      						 // "8080"
	             ((HttpServletRequest) request).getRequestURI() +       // "/people"
	             ( (((HttpServletRequest) request).getQueryString()!=null) ? ("?" +                           						// "?"
	             ((HttpServletRequest) request).getQueryString()) : "");       // "lastname=Fox&age=30"
		e.setUrl(uri);
		LaFabricaWS.getInstance().obtenerIContentController().sendReport(e);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Starting logger...");
	}

}

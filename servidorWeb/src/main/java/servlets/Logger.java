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

import models.LaFabricaWS;
import ua_parser.Parser;
import ua_parser.Client;

@WebFilter("/*")
public class Logger implements Filter {
	private Parser parser = new Parser();
	public static int index=0;
	public static String [] entries = new String[Integer.parseInt(ConfigListener.logthreshold)*6];
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
		entries[index++] = request.getRemoteAddr();
		entries[index++] = request.getLocalAddr();
		entries[index++] = String.valueOf(request.getLocalPort());
		entries[index++] = ((HttpServletRequest) request).getRequestURI().toString();
		entries[index++] = parseBrowser((HttpServletRequest)request);
		entries[index++] = parseOS((HttpServletRequest)request);
		if(index==Integer.parseInt(ConfigListener.logthreshold)*6-1) {
			LaFabricaWS.getInstance().obtenerIContentController().sendReports(entries);
			index=0;
			entries = new String[Integer.parseInt(ConfigListener.logthreshold)*6];
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Starting logger...");
	}

}

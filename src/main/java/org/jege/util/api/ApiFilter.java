package org.jege.util.api;

import java.io.IOException;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jege.util.message.MessagesClient;

@WebFilter("/api/*")
public class ApiFilter implements Filter {
    
    @Inject
    private Instance<MessagesClient> messagesInstance;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing needed
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Only HttpServletRequest requests are supported");
        }
        
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.addHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
        httpResponse.addHeader("Access-Control-Max-Age", "1728000");
        
        if("OPTIONS".equals(httpRequest.getMethod())) {
            chain.doFilter(request, httpResponse);
        } else {
            ServletOutputStream output = httpResponse.getOutputStream();
            output.print("{");
            output.print("\"status\":\""+httpResponse.getStatus()+"\",");
            output.print("\"body\":");
            output.flush();
            
            try {
                chain.doFilter(request, httpResponse);
            } catch(Exception e) {
                
            }
            
            MessagesClient messages = messagesInstance.get();
            output.print(",");
            output.print("\"messages\":"+messages.getJson());
            output.print("}");
            output.flush();
            
            if(ApiConfiguration.PARAM_VALUE_TRUE.equals(request.getParameter(ApiConfiguration.PARAM_SUPPRESS_RESPONSE_CODE))) {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }
    
    @Override
    public void destroy() {
        // Nothing to do
    }
}

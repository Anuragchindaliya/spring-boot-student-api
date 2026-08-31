package in.anurag.crudSpingBootDemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

//this filter added through configuration in FilterConfig
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String requestId = UUID.randomUUID().toString();
            httpResponse.setHeader("X-Request-ID", requestId);
            System.out.println("X-Request-ID : " + requestId);
            System.out.println("Incoming Request : " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
            chain.doFilter(request, response);
            System.out.println("Response status : " + httpResponse.getStatus());

            //you can't update body, for this we can use ContentCachingResponseWrapper
//            httpResponse.getWriter().write("update body by logging filter");

    }
}

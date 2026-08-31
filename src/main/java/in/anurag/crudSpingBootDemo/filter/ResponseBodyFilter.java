package in.anurag.crudSpingBootDemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Order(2)
public class ResponseBodyFilter implements Filter {

    //this only update response body of /api/notification endpoint
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        if(!uri.equals("/api/notification")){
           chain.doFilter(request,response);
           return;
        }
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);
        chain.doFilter(request,responseWrapper);
        byte[] originalBodyBytes = responseWrapper.getContentAsByteArray();
        responseWrapper.resetBuffer();
        String originalBody = new String(originalBodyBytes);
        String modifiedBody =
                """
                {
                    "originalResponse":%s,
                    "appName":"Student Managment System"
                }        
                """.formatted(originalBody);

        System.out.println("original body : "+originalBody);
        System.out.println("modified body : "+modifiedBody);
        responseWrapper.setHeader("content-type","application/json");
        responseWrapper.getWriter().write(modifiedBody);
        responseWrapper.copyBodyToResponse();
    }
}

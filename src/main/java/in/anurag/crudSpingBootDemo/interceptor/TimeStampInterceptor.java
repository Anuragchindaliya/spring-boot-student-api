package in.anurag.crudSpingBootDemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
@Component
public class TimeStampInterceptor implements HandlerInterceptor {
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("TimeStampInterceptor called - "+ LocalDateTime.now());
    }
}

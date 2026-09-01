package in.anurag.crudSpingBootDemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
    // it will get called before controller executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("PreHandle called - Header interceptor");
        String logMessage = """
                Incoming Request--------
                HTTP Method: %s
                Request URI: %s
                Request Parameters : %s
                Client IP: %s
                Token Header: %s
                """.formatted(request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                request.getRemoteAddr(),
                request.getHeader("token")
        );
        System.out.println(logMessage);

        // in rest api handleMethod is controller
        if (handler instanceof HandlerMethod handlerMethod) {

            String controllerName = handlerMethod.getBeanType().getName();
            String methodName = handlerMethod.getMethod().getName();

            System.out.println("Controller name " + controllerName);
            System.out.println("Method name : " + methodName);
        }
        return true;
    }

    // it will get call after controller fully executed
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        System.out.println("postHandle called - Header interceptor");
    }

    // it will get called after response is sent to client
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterCompletion called - header interceptor");
        System.out.println("Response status: "+response.getStatus());
        if (ex != null) {
            System.out.println("Exception is " + ex.getMessage());
        }
        response.setHeader("from", "custom filter");
    }
}
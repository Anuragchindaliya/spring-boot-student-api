package in.anurag.crudSpingBootDemo.configuration;

import in.anurag.crudSpingBootDemo.interceptor.HeaderInterceptor;
import in.anurag.crudSpingBootDemo.interceptor.TimeStampInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public HeaderInterceptor headerInterceptor;
    public TimeStampInterceptor timeStampInterceptor;
    public WebConfig(HeaderInterceptor interceptor, TimeStampInterceptor timeStampInterceptor){
        this.headerInterceptor = interceptor;
        this.timeStampInterceptor = timeStampInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // in afterCompletion order will work in reverse order
        registry.addInterceptor(headerInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/students/*","/api/public/**").order(1);
        registry.addInterceptor(timeStampInterceptor).order(2);
    }
}

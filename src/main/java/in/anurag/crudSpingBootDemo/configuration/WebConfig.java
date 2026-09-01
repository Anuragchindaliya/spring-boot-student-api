package in.anurag.crudSpingBootDemo.configuration;

import in.anurag.crudSpingBootDemo.interceptor.HeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public HeaderInterceptor headerInterceptor;
    public WebConfig(HeaderInterceptor interceptor){
        this.headerInterceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(headerInterceptor);
    }
}

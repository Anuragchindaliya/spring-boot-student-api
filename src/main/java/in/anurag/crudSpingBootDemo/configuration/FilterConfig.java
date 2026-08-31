package in.anurag.crudSpingBootDemo.configuration;

import in.anurag.crudSpingBootDemo.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    //this logging filter only work for /server-info endpoint
    @Bean
    public FilterRegistrationBean<LoggingFilter> getLoggingFilter(){
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/server-info");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}

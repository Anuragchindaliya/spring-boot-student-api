package in.anurag.crudSpingBootDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationPointcuts {

    @Pointcut("within(in.anurag.crudSpingBootDemo.controller..*)")
    public void controllerLayer(){

    }
    @Pointcut("within(in.anurag.crudSpingBootDemo.service..*)")
    public void serviceLayer(){

    }
    @Pointcut("execution(public * *(..))")
    public void publicMethod(){

    }
    @Pointcut("serviceLayer() && publicMethod()")
    public void publicServiceMethod(){

    }
}

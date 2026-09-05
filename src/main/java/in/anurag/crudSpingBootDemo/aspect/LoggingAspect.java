package in.anurag.crudSpingBootDemo.aspect;

import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import in.anurag.crudSpingBootDemo.entity.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// how AOP would be working under the hood
// beforeLogic();
//  try{
//        Object result = targetMethod();
//        afterReturningLogic(result);
//        return result;
//    }catch(Throwable exception){
//        afterThrowingLogic(exception);
//        throw exception;
//    }finally{
//        afterLogic();
//    }

@Component
@Aspect
public class LoggingAspect {
    // Advice is @Before, @After, @AfterReturning, @AfterThrowing, @Around and method implementation
    // pointcut is execution(String in.anurag.crudSpingBootDemo.service.SchoolService)

    // we can use @Before for example checking authorization access and validation
    @Before("execution(* in.anurag.crudSpingBootDemo.service.SchoolService.createSchool(..))")
    public void logBeforeMethod(JoinPoint joinPoint){
        Object[] arr = joinPoint.getArgs();
        System.out.println("School is going to be created from aspect "+arr.toString());

    }
    @AfterReturning(value = "execution(* in.anurag.crudSpingBootDemo.service.SchoolService.createSchool(..))", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, CreateSchoolDTO result){
        Object[] args = joinPoint.getArgs();
        result.setName("Dummy name");
        result.setAge(244);
        System.out.println("Aspect logAfterReturningMethod is called with this args "+result);
    }
    @AfterThrowing(value = "execution(* in.anurag.crudSpingBootDemo.service.SchoolService.createSchool(..))", throwing = "exception")
    public void logAfterThrowMethod(Exception exception){
        String message = exception.getMessage();
        System.out.println("error class Name "+exception.getClass().getName() );
        System.out.println("error message "+message );
        System.out.println("Aspect logAfterThrowMethod is called!");
    }

    // this will be called even if there's an issue
    @After(value = "execution(* in.anurag.crudSpingBootDemo.service.SchoolService.createSchool(..))")
    public void logAfterMethod(){
        System.out.println("Aspect logAfterMethod is called!");
    }

    // here we can call target multiple times, and it should return what controller return type expect
    // we can control here the call of target method like we can skip target method call
    @Around(value = "execution(* in.anurag.crudSpingBootDemo.service.SchoolService.createSchool(..))")
    public CreateSchoolDTO logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Aspect starting around Before target method is called! "+joinPoint.getSignature().getName());
        try{
            CreateSchoolDTO student = (CreateSchoolDTO) joinPoint.proceed();
            student.setAge(786);
            student.setName("Again change");
            System.out.println("Aspect around Execution successfully!");
            System.out.println("Aspect around After target method is called!");
            return student;
        } catch (Exception e) {
            System.out.println("Aspect around execution failed -> error "+e.getMessage());
            throw e;
        }finally {
            System.out.println("Aspect around Execution Completed!");
        }
    }

    @Around(value = "execution(* in.anurag.crudSpingBootDemo.service.SchoolService.dummyMethod(..))")
    public Object logAfterDummyMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] arr = joinPoint.getArgs();

        String originalString = (String) arr[0];
        String modifiedfiedString = originalString.toUpperCase();
        Object[] modifiedArr = {
                modifiedfiedString
        };
        System.out.println("Aspect logAfterDummyMethod is called! for dummyMethod "+modifiedfiedString);
        // we can call multiple times 
        String returnType  = (String) joinPoint.proceed(arr);
        String returnType2  = (String) joinPoint.proceed(modifiedArr);

        returnType =  "First call Original String : "+returnType+": Second call Modified String: " +returnType2;

        return returnType;
    }
}

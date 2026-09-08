package in.anurag.crudSpingBootDemo.annotation;

import java.lang.annotation.*;

//marker annotation
//1. where the annotation can be applied
//2. how long the annotation will be available

//configured annotation,

//like target is meta annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackExecutionTime {
    long warnAfter() default 2000;
    String operation() default "";

}
// bean LifeCycle
// read bean definition
// instantiate object
// Inject dependencies
// run aware callbacks
// run BeanPostProcessors before initialization
// run initialization callbacks


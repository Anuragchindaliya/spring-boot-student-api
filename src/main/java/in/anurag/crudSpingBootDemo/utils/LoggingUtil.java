package in.anurag.crudSpingBootDemo.utils;

public class LoggingUtil {
    public static void logStart(String className, String methodName){
        System.out.println("Executing : "+ className+" : "+methodName);
    }
    public static void logEnd(String className, String methodName){
        System.out.println("Finishing : "+ className+" : "+methodName);
    }
}

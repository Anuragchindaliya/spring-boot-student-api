package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.annotation.TrackExecutionTime;
import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    public CreateSchoolDTO createSchool(CreateSchoolDTO schoolDTO){
        String m = "School is created";
        System.out.println(m);
//        throw new RuntimeException("Some error occurred");
        return schoolDTO;
    }
//    @Timestamp
    @TrackExecutionTime(warnAfter = 1000, operation = "Run Dummy method")
    public String dummyMethod(String s){
        String m = "dummyMethod is called from service";
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(m);
//        throw new RuntimeException("Some error occurred");
        return s;
    }
}

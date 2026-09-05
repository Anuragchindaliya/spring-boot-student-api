package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    public CreateSchoolDTO createSchool(CreateSchoolDTO schoolDTO){
        String m = "School is created";
        System.out.println(m);
//        throw new RuntimeException("Some error occurred");
        return schoolDTO;
    }
    public String dummyMethod(String s){
        String m = "dummyMethod is called from service";
        System.out.println(m);
//        throw new RuntimeException("Some error occurred");
        return s;
    }
}

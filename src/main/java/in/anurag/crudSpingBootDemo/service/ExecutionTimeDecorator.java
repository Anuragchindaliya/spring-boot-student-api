package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateTeacherRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateTeacherResponseDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ExecutionTimeDecorator implements TeacherService{
    private LoggingDecorator loggingDecorator;

    public ExecutionTimeDecorator(LoggingDecorator loggingDecorator) {
        this.loggingDecorator = loggingDecorator;
    }

    @Override
    public CreateTeacherResponseDTO createTeacher(CreateTeacherRequestDTO teacher) {
        long start = System.currentTimeMillis();
        loggingDecorator.createTeacher(teacher);
        long end = System.currentTimeMillis();
        System.out.println("execution time : "+(end-start));
        return null;
    }
}

package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateTeacherRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateTeacherResponseDTO;
import in.anurag.crudSpingBootDemo.utils.LoggingUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class LoggingDecorator implements TeacherService {
    private final TeacherServiceImpl teacherServiceImpl;
    public LoggingDecorator(TeacherServiceImpl teacherServiceImpl){
        this.teacherServiceImpl = teacherServiceImpl;
    }
    @Override
    public CreateTeacherResponseDTO createTeacher(CreateTeacherRequestDTO teacher) {
        //Logging related logic
        LoggingUtil.logStart("TeacherService","createTeacher");
        CreateTeacherResponseDTO teacherResponse = teacherServiceImpl.createTeacher(teacher);
        LoggingUtil.logEnd("TeacherService","createTeacher");
        return  teacherResponse;
    }
}

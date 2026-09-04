package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateTeacherRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateTeacherResponseDTO;
import org.springframework.stereotype.Service;

public interface TeacherService {
    CreateTeacherResponseDTO createTeacher(CreateTeacherRequestDTO teacher);
}

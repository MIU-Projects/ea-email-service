package emailservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegistrationGroupDTO {

    private List<StudentDTO> students = new ArrayList<>();
    private List<AcademicBlockDTO> academicBlocks = new ArrayList<>();

    public RegistrationGroupDTO() {
    }

    public RegistrationGroupDTO(List<StudentDTO> studentDTOS, List<AcademicBlockDTO> acadamicBlockDTOS) {
        this.students = studentDTOS;
        this.academicBlocks = acadamicBlockDTOS;
    }

    public void addStudentDTO(StudentDTO studentDTO){
        students.add(studentDTO);
    }

    public void addAcademicBlockDTO(AcademicBlockDTO acadamicBlockDTO){
        academicBlocks.add(acadamicBlockDTO);
    }



}

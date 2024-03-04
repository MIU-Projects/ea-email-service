package emailservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class AcademicBlockDTO {

    private String blockCode;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
//    @Enumerated
//    private Semester semester;

    public AcademicBlockDTO() {
    }

    public AcademicBlockDTO(String blockCode,
                            String name,
                            LocalDateTime startDate,
                            LocalDateTime endDate) {
        this.blockCode = blockCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

package emailservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEventDTO {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<RegistrationGroupDTO> registrationGroups = new ArrayList<>();


}

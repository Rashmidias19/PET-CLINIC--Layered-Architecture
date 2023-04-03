package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class OperationSchedule {
    private String OperationID;
    private String PetID;
    private String Date;
    private String Time;
    private String WorkTime;
    private String Shift;
    private String OT;
}

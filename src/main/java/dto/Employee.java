package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Employee {
    private String EmployeeID;
    private String Name;
    private String UserID;
    private String DOB;
    private String NIC;
    private int Age;
    private String Gender;
    private String address;
    private String Salary;
    private String Contact;
    private String email;

    public Employee(String string) {
        this.Name=string;
    }
}

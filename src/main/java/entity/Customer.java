package entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String CustomerID;
    private String CustTitle;
    private String CustName;
    private String NIC;
    private String DOB;
    private int age;
    private String Gender;
    private String contact;
    private String email;
    private String address;

}

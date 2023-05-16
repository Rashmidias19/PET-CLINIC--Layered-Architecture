package dto;


import lombok.*;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Pet {
 private String PetID;
 private String Name;
 private String CustomerID;
 private String Type;
 private String Breed;
 private String Gender;
 private String DOB;
 private int Age;
 private String address;
 private String contact;
 private Blob picture;

}

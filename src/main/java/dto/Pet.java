package dto;


import lombok.*;

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

 public Pet(String string, String string1) {
     this.CustomerID=string;
     this.contact=string1;
 }
}

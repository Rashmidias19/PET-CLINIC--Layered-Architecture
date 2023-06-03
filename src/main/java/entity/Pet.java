//package entity;
//
//import java.sql.Blob;
//
//public class Pet {
//    private String PetID;
//    private String Name;
//    private String CustomerID;
//    private String Type;
//    private String Breed;
//    private String Gender;
//    private String DOB;
//    private int Age;
//    private String address;
//    private String contact;
//    private Blob picture;
//
//    public Pet() {
//    }
//
//    public Pet(String petID, String name, String customerID, String type, String breed, String gender, String DOB, int age, String address, String contact, Blob picture) {
//        PetID = petID;
//        Name = name;
//        CustomerID = customerID;
//        Type = type;
//        Breed = breed;
//        Gender = gender;
//        this.DOB = DOB;
//        Age = age;
//        this.address = address;
//        this.contact = contact;
//        this.picture = picture;
//    }
//
//    public String getPetID() {
//        return PetID;
//    }
//
//    public void setPetID(String petID) {
//        PetID = petID;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getCustomerID() {
//        return CustomerID;
//    }
//
//    public void setCustomerID(String customerID) {
//        CustomerID = customerID;
//    }
//
//    public String getType() {
//        return Type;
//    }
//
//    public void setType(String type) {
//        Type = type;
//    }
//
//    public String getBreed() {
//        return Breed;
//    }
//
//    public void setBreed(String breed) {
//        Breed = breed;
//    }
//
//    public String getGender() {
//        return Gender;
//    }
//
//    public void setGender(String gender) {
//        Gender = gender;
//    }
//
//    public String getDOB() {
//        return DOB;
//    }
//
//    public void setDOB(String DOB) {
//        this.DOB = DOB;
//    }
//
//    public int getAge() {
//        return Age;
//    }
//
//    public void setAge(int age) {
//        Age = age;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getContact() {
//        return contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact;
//    }
//
//    public Blob getPicture() {
//        return picture;
//    }
//
//    public void setPicture(Blob picture) {
//        this.picture = picture;
//    }
//
//    @Override
//    public String toString() {
//        return "Pet{" +
//                "PetID='" + PetID + '\'' +
//                ", Name='" + Name + '\'' +
//                ", CustomerID='" + CustomerID + '\'' +
//                ", Type='" + Type + '\'' +
//                ", Breed='" + Breed + '\'' +
//                ", Gender='" + Gender + '\'' +
//                ", DOB='" + DOB + '\'' +
//                ", Age=" + Age +
//                ", address='" + address + '\'' +
//                ", contact='" + contact + '\'' +
//                ", picture=" + picture +
//                '}';
//    }
//}

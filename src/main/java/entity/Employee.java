//package entity;
//
//import java.sql.Blob;
//
//public class Employee {
//    private String EmployeeID;
//    private String Name;
//    private String UserID;
//    private String DOB;
//    private String NIC;
//    private int Age;
//    private String Gender;
//    private String address;
//    private String Salary;
//    private String Contact;
//    private String email;
//    private Blob picture;
//
//    public Employee() {
//    }
//
//    public Employee(String employeeID, String name, String userID, String DOB, String NIC, int age, String gender, String address, String salary, String contact, String email, Blob picture) {
//        EmployeeID = employeeID;
//        Name = name;
//        UserID = userID;
//        this.DOB = DOB;
//        this.NIC = NIC;
//        Age = age;
//        Gender = gender;
//        this.address = address;
//        Salary = salary;
//        Contact = contact;
//        this.email = email;
//        this.picture = picture;
//    }
//
//    public String getEmployeeID() {
//        return EmployeeID;
//    }
//
//    public void setEmployeeID(String employeeID) {
//        EmployeeID = employeeID;
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
//    public String getUserID() {
//        return UserID;
//    }
//
//    public void setUserID(String userID) {
//        UserID = userID;
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
//    public String getNIC() {
//        return NIC;
//    }
//
//    public void setNIC(String NIC) {
//        this.NIC = NIC;
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
//    public String getGender() {
//        return Gender;
//    }
//
//    public void setGender(String gender) {
//        Gender = gender;
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
//    public String getSalary() {
//        return Salary;
//    }
//
//    public void setSalary(String salary) {
//        Salary = salary;
//    }
//
//    public String getContact() {
//        return Contact;
//    }
//
//    public void setContact(String contact) {
//        Contact = contact;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
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
//        return "Employee{" +
//                "EmployeeID='" + EmployeeID + '\'' +
//                ", Name='" + Name + '\'' +
//                ", UserID='" + UserID + '\'' +
//                ", DOB='" + DOB + '\'' +
//                ", NIC='" + NIC + '\'' +
//                ", Age=" + Age +
//                ", Gender='" + Gender + '\'' +
//                ", address='" + address + '\'' +
//                ", Salary='" + Salary + '\'' +
//                ", Contact='" + Contact + '\'' +
//                ", email='" + email + '\'' +
//                ", picture=" + picture +
//                '}';
//    }
//}

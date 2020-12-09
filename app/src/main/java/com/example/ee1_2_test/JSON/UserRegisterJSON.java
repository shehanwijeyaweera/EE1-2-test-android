package com.example.ee1_2_test.JSON;

public class UserRegisterJSON {
    private String FirstName;
    private String LastName;
    private String Email;
    private  String Address;
    private int PhoneNo;
    private String Username;
    private String Password;

    public UserRegisterJSON(String firstName, String lastName, String email, String address, int phoneNo, String username, String password) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Address = address;
        PhoneNo = phoneNo;
        Username = username;
        Password = password;
    }

    public UserRegisterJSON() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

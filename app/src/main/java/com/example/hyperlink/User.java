package com.example.hyperlink;

public class User {
    public User(){

    }
    public User(String name, String phonenumber, String email, String password, String address) {
        Name = name;
        Phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    private String Name;
    private String Phonenumber;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String email;
    private String password;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Phonenumber='" + Phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

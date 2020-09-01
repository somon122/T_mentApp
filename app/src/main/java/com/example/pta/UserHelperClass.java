package com.example.pta;

public class UserHelperClass {

    String Fname,Uname,Phone,Password;


    public UserHelperClass() {
    }

    public UserHelperClass(String fname, String uname, String phone, String password) {
        Fname = fname;
        Uname = uname;
        Phone = phone;
        Password = password;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

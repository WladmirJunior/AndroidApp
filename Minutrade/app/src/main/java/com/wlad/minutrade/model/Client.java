package com.wlad.minutrade.model;

/**
 * Created by Wlad on 03/02/2016.
 */
public class Client {

    public String CPF;
    public String name;
    public String email;
    public String address;
    public String number1;
    public String number2;
    public String maritalStatus;

    public Client(String CPF, String name, String email, String address, String number1, String number2, String maritalStatus) {
        this.CPF = CPF;
        this.name = name;
        this.email = email;
        this.address = address;
        this.number1 = number1;
        this.number2 = number2;
        this.maritalStatus = maritalStatus;
    }

    public  Client(){}

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}

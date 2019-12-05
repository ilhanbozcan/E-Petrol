package com.example.e_petrol;

public class Users {
    private String namesurname,phone,gmail,fuel,car;
    public Users(String namesurname,String phone,String gmail,String fuel,String car ){
        this.namesurname=namesurname;
        this.phone=phone;
        this.gmail=gmail;
        this.fuel=fuel;
        this.car=car;
    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}

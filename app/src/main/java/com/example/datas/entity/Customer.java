package com.example.datas.entity;

public class Customer {


    private String email;
    private String name;
    private String password;
    private int id;
    private String phone;
    private String street;
    private String place;
    private int active;

    public Customer() {
    }



    public Customer(int id, String email, String name, String password, String phone, String street, String place, int active){
        this.email = email;
        this.name = name;
        this.password = password;
        this.id = id;
        this.phone = phone;
        this.street = street;
        this.place = place;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}



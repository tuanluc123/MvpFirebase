package com.maituanluc.basic1.data.model;

public class User {
    private String Id;
    private String Name;
    private String Phone;
    private String Gender;
    private String document;

    public User() {
    }

    public User(String id, String name, String phone, String gender, String document) {
        Id = id;
        Name = name;
        Phone = phone;
        Gender = gender;
        this.document = document;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Gender='" + Gender + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}

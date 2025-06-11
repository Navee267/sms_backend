package com.example.SMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private String gender;
    private String dob;
    private String className;
    private String email;
    private String phone;
    private double percentage;
    private String fileName;

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Student(int id, String name, String gender, String dob,String className, String email, String phone, double percentage, String fileName) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.className = className;
        this.email = email;
        this.phone = phone;
        this.percentage = percentage;
        this.fileName = fileName;
    }

    public Student() {
    }
}

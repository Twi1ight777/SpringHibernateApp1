package ru.start.springhibernate.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String fullName;

    @Min(value = 1900, message = "Age should be greater than 1900")
    private int yearOfBirth;

    //Конструктор по умолчанию нужен для Spring
    public Person() {

    }

    public Person(String fullName, int yearOfBirth){
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
    public int getId(){ return id;}
    public void setId(int id){ this.id = id;}

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

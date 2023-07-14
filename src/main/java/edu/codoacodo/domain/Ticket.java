package edu.codoacodo.domain;

import java.time.LocalDateTime;

public class Ticket {

    private String name;
    private String surname;
    private String email;
    private byte quantity;
    private String category; //Estudiante Trainee Junior
    private LocalDateTime registration;
    private int ID;

    public Ticket(String name, String surname, String email, byte quantity, String category) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.quantity = quantity;
        this.category = category;
        this.registration = LocalDateTime.now();
    }

    public Ticket(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuantity(byte quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", registration=" + registration +
                ", ID=" + ID +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public byte getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public int getID() {
        return ID;
    }
}

package edu.codoacodo.domain;

import java.time.LocalDateTime;

public class Orador {

    private String name;
    private String surname;
    private String comment;
    private LocalDateTime registration;
    private int ID;


    public Orador(String name, String surname, String comment) {
        this.name = name;
        this.surname = surname;
        this.comment = comment;
        this.registration = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Orador{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", comment='" + comment + '\'' +
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

    public String getComment() {
        return comment;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public int getID() {
        return ID;
    }
}

package com.NBA_Rankings_Scores_Project.Models;

public class PlayerModel extends Models {

    public enum Position{
        GUARD,
        SHOOTING_GUARD,
        SMALL_FORWARD,
        POWER_FORWARD,
        CENTER
    }

    private String surname;
    private Position position;
    private int number;
    private int age;
    private String nationality;
    private int ID;

    public PlayerModel(String name, String surname, Position position, int number, int age, String nationality, int ID) {
        super(name);
        this.surname = surname;
        this.position = position;
        this.number = number;
        this.age = age;
        this.nationality = nationality;
        this.ID = ID;
    }

    public String getSurname() {
        return surname;
    }

    public Position getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public int getID() {
        return ID;
    }
}

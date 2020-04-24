package com.NBA_Rankings_Scores_Project.Models;

/**
 * Model of the player that keeps the data of it
 * @see Models
 */
public class PlayerModel extends Models {

    /**
     * Enum of the position of the player
     */
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

    /**
     * Constructor that initialize the data members
     * @param name Name of the player
     * @param surname Surname of the player
     * @param position Position of the player
     * @param number Jersey number of the player
     * @param age Age of the player
     * @param nationality Noationality of the player
     * @param ID Team ID of the player necessary to do the link with the player statistics
     * @see Models
     */
    public PlayerModel(String name, String surname, Position position, int number, int age, String nationality, int ID) {
        super(name);
        this.surname = surname;
        this.position = position;
        this.number = number;
        this.age = age;
        this.nationality = nationality;
        this.ID = ID;
    }

    /**
     * Getter of the surname of the player
     * @return The surname of the player
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter of the position of the player
     * @return The position of the player
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter of the jersey number of the player
     * @return The jersey number of the player
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter of the age of the player
     * @return The age of the player
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter of the nationality of the player
     * @return The nationality of the player
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Getter of the team ID of the player
     * @return The team ID of the player
     */
    public int getID() {
        return ID;
    }
}

package com.NBA_Rankings_Scores_Project.Models;

/**
 * Model of the conference
 * @see Models
 */
public class Conference extends Models{
    /**
     * Constructor that call the Models constructor
     * @param name name of the conference
     */
    public Conference(String name) {
        super(name);
    }

    /**
     * Override toString function
     * @return name of the conference
     */
    @Override
    public String toString() {
        return getName();
    }
}

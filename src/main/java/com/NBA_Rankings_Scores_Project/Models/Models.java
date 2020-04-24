package com.NBA_Rankings_Scores_Project.Models;

/**
 * Models parent class
 */
public class Models {
    private String name;

    /**
     * Constructor that initialize the name
     * @param name Name of the model
     */
    public Models(String name){
        this.name = name;
    }

    /**
     * Getter of the name
     * @return Name of the model
     */
    public String getName() {
        return name;
    }
}

package com.NBA_Rankings_Scores_Project.Models;

/**
 * Model of a team that keeps the data of it
 * @see Models
 */
public class TeamModel extends Models {
    private String headCoach;

    /**
     * Constructor that initialize the data members of the team
     * @param name Name of the team
     * @param headCoach Name of the Head Coach of the team
     * @see Models
     */
    public TeamModel(String name, String headCoach){
        super(name);
        this.headCoach = headCoach;
    }

    /**
     * Getter of the Head Coach
     * @return The Head Coach's name of the team
     */
    public String getHeadCoach() {
        return this.headCoach;
    }
}

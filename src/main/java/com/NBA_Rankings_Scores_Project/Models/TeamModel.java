package com.NBA_Rankings_Scores_Project.Models;

public class TeamModel extends Models {
    private String headCoach;

    public TeamModel(String name, String headCoach){
        super(name);
        this.headCoach = headCoach;
    }

    public String getHeadCoach() {
        return this.headCoach;
    }
}

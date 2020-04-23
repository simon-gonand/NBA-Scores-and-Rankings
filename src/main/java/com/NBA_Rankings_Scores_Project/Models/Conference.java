package com.NBA_Rankings_Scores_Project.Models;

public class Conference extends Models{
    public Conference(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}

package com.NBA_Rankings_Scores_Project.Models;

public class PlayerStats {
    private Integer ID, minutes, points, rebounds, assists, steals, blocks, turnovers;
    private String fg, treePts, ft;

    public PlayerStats(int ID, int minutes, int points, int rebounds, int assists, int steals,
                       int blocks, int turnovers, String fg, String treePts, String ft) {
        this.ID = ID;
        this.minutes = minutes;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.fg = fg;
        this.treePts = treePts;
        this.ft = ft;
    }

    public PlayerStats(int ID, int minutes) {
        this.ID = ID;
        this.minutes = minutes;
        this.points = null;
        this.rebounds = null;
        this.assists = null;
        this.steals = null;
        this.blocks = null;
        this.turnovers = null;
        this.fg = null;
        this.treePts = null;
        this.ft = null;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getRebounds() {
        return rebounds;
    }

    public Integer getAssists() {
        return assists;
    }

    public Integer getSteals() {
        return steals;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public Integer getTurnovers() {
        return turnovers;
    }

    public String getFg() {
        return fg;
    }

    public String getTreePts() {
        return treePts;
    }

    public String getFt() {
        return ft;
    }
}

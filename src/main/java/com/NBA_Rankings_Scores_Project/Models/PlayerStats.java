package com.NBA_Rankings_Scores_Project.Models;

/**
 * Model that keeps the statistics of a player
 * @see Models
 */
public class PlayerStats extends Models{
    private Integer ID, minutes, points, rebounds, assists, steals, blocks, turnovers;
    private String fg, threePts, ft;

    /**
     * Constructor that initialize the data members
     * @param ID Team ID of the player to know which PlayerModel it is
     * @param minutes Time that the player spent to play
     * @param points Points that the player scored during the game
     * @param rebounds Rebounds that the player taked during the game
     * @param assists Assists that the player gived during the game
     * @param steals Steals that the player made during the game
     * @param blocks Blocks that the player made during the game
     * @param turnovers Turnovers that the player made during the game
     * @param fg Shoots that the player made and attempted during the game
     * @param threePts Three points shoots that the player made and attempted during the game
     * @param ft Free throws that the player made and attempted during the game
     * @see Models
     */
    public PlayerStats(int ID, int minutes, int points, int rebounds, int assists, int steals,
                       int blocks, int turnovers, String fg, String threePts, String ft) {
        super("playerStats" + ID);
        this.ID = ID;
        this.minutes = minutes;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.fg = fg;
        this.threePts = threePts;
        this.ft = ft;
    }

    /**
     * Constructor if the player didn't play during the game
     * @param ID Team ID of the player
     * @see Models
     */
    public PlayerStats(int ID) {
        super("playerStats" + ID);
        this.ID = ID;
        this.minutes = 0;
        this.points = null;
        this.rebounds = null;
        this.assists = null;
        this.steals = null;
        this.blocks = null;
        this.turnovers = null;
        this.fg = null;
        this.threePts = null;
        this.ft = null;
    }

    /**
     * Getter of the Team ID of the player
     * @return Team ID of the player
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Getter of the minutes of the player
     * @return Minutes of the player
     */
    public Integer getMinutes() {
        return minutes;
    }

    /**
     * Getter of the points of the player
     * @return Points of the player
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * Getter of the rebounds of the player
     * @return Rebounds of the player
     */
    public Integer getRebounds() {
        return rebounds;
    }

    /**
     * Getter of the assists of the player
     * @return Assists of the player
     */
    public Integer getAssists() {
        return assists;
    }

    /**
     * Getter of the steals of the player
     * @return Steals of the player
     */
    public Integer getSteals() {
        return steals;
    }

    /**
     * Getter of the blocks of the player
     * @return Blocks of the player
     */
    public Integer getBlocks() {
        return blocks;
    }

    /**
     * Getter of the turnovers of the player
     * @return Turnovers of the player
     */
    public Integer getTurnovers() {
        return turnovers;
    }

    /**
     * Getter of the field goal of the player
     * @return Field goal of the player
     */
    public String getFg() {
        return fg;
    }

    /**
     * Getter of the three points of the player
     * @return Three points of the player
     */
    public String getThreePts() {
        return threePts;
    }

    /**
     * Getter of the free throws of the player
     * @return Free throws of the player
     */
    public String getFt() {
        return ft;
    }
}

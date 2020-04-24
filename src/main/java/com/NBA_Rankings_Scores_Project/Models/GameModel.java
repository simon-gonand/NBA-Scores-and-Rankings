package com.NBA_Rankings_Scores_Project.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Model of a game that keeps the data of a it
 * @see Models
 */
public class GameModel extends Models{
    private Date date;
    private String winner, totScore, q1Score, q2Score, q3Score, q4Score;

    /**
     * Constructor that initialize all data members
     * @param name Name of the game
     * @param date Date of the game
     * @param winner Team winner of the game
     * @param totScore Score of the game
     * @param q1Score Score of the first quarter of the game
     * @param q2Score Score of the second quarter of the game
     * @param q3Score Score of the third quarter of the game
     * @param q4Score Score of the fourth quarter of the game
     */
    public GameModel(String name, String date, String winner, String totScore, String q1Score,
                     String q2Score, String q3Score, String q4Score) {
        super(name);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yy");
        try {
            this.date = sdf.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        this.winner = winner;
        this.totScore = totScore;
        this.q1Score = q1Score;
        this.q2Score = q2Score;
        this.q3Score = q3Score;
        this.q4Score = q4Score;
    }

    /**
     * Getter of the date of the game
     * @return The date of the game
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter of the winner of the game
     * @return The winner of the game
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Getter of the score of the game
     * @return The score of the game
     */
    public String getTotScore() {
        return totScore;
    }

    /**
     * Getter of the score of the first quarter of the game
     * @return The score of the first quarter of the game
     */
    public String getQ1Score() {
        return q1Score;
    }

    /**
     * Getter of the score of the second quarter of the game
     * @return The score of the second quarter of the game
     */
    public String getQ2Score() {
        return q2Score;
    }

    /**
     * Getter of the score of the third quarter of the game
     * @return The score of the third quarter of the game
     */
    public String getQ3Score() {
        return q3Score;
    }

    /**
     * Getter of the score of the fourth quarter of the game
     * @return The score of the fourth quarter of the game
     */
    public String getQ4Score() {
        return q4Score;
    }
}

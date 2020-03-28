package com.NBA_Rankings_Scores_Project.Models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameModel extends Models{
    private Date date;
    private String winner, totScore, q1Score, q2Score, q3Score, q4Score;

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

    public Date getDate() {
        return date;
    }

    public String getWinner() {
        return winner;
    }

    public String getTotScore() {
        return totScore;
    }

    public String getQ1Score() {
        return q1Score;
    }

    public String getQ2Score() {
        return q2Score;
    }

    public String getQ3Score() {
        return q3Score;
    }

    public String getQ4Score() {
        return q4Score;
    }
}

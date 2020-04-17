package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;

import javax.swing.*;
import java.awt.*;

public class GameView {
    private JPanel panel;
    private GameModel game;
    private TeamModel home, visitor;

    public GameView(JPanel panel, GameModel game, TeamModel home, TeamModel visitor){
        this.panel = panel;
        this.game = game;
        this.home = home;
        this.visitor = visitor;

        JPanel generalStats = new JPanel();
        JPanel otherStats = new JPanel();

        generalStats.setBounds(0,0, panel.getWidth(), panel.getHeight()/3 + 5);
        otherStats.setBounds(0, generalStats.getHeight(), panel.getWidth(), 2*panel.getHeight()/3 - 5);
        generalStats.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        generalStats.setLayout(null);
        otherStats.setLayout(null);

        this.panel.add(generalStats);
        this.panel.add(otherStats);

        fillGeneralStatsPanel(generalStats);
    }

    private void fillGeneralStatsPanel(JPanel generalStats){
        JLabel homeTeamLabel = new JLabel(home.getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.RIGHT);
        JLabel visitorTeamLabel = new JLabel (visitor.getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.LEFT);

        homeTeamLabel.setHorizontalTextPosition(JLabel.CENTER);
        homeTeamLabel.setVerticalTextPosition(JLabel.BOTTOM);
        visitorTeamLabel.setHorizontalTextPosition(JLabel.CENTER);
        visitorTeamLabel.setVerticalTextPosition(JLabel.BOTTOM);

        homeTeamLabel.setBounds(0, 0, generalStats.getWidth() - 10, 100);
        visitorTeamLabel.setBounds(10, 0, 300, 100);

        JLabel totScore = new JLabel(game.getTotScore(), JLabel.CENTER);
        totScore.setBounds(0, 15, generalStats.getWidth(), 50);
        totScore.setFont(new Font(totScore.getFont().getName(), Font.BOLD, 35));

        displayScores(generalStats, totScore);

        generalStats.add(homeTeamLabel);
        generalStats.add(visitorTeamLabel);
        generalStats.add(totScore);

    }

    private void displayScores(JPanel generalStats, JLabel totScore){
        JLabel q1 = new JLabel("Q1", JLabel.CENTER);
        q1.setBounds(0, totScore.getY() + totScore.getHeight() + 3, generalStats.getWidth(), 25);
        q1.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q1Score = new JLabel(game.getQ1Score(), JLabel.CENTER);
        q1Score.setBounds(0, q1.getY() + q1.getHeight() + 2, generalStats.getWidth(), 25);
        q1Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        JLabel q2 = new JLabel("Q2", JLabel.CENTER);
        q2.setBounds(0, q1Score.getY() + q1Score.getHeight() + 4, generalStats.getWidth(), 25);
        q2.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q2Score = new JLabel(game.getQ2Score(), JLabel.CENTER);
        q2Score.setBounds(0, q2.getY() + q2.getHeight() + 2, generalStats.getWidth(), 25);
        q2Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        JLabel q3 = new JLabel("Q3", JLabel.CENTER);
        q3.setBounds(0, q2Score.getY() + q2Score.getHeight() + 4, generalStats.getWidth(), 25);
        q3.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q3Score = new JLabel(game.getQ3Score(), JLabel.CENTER);
        q3Score.setBounds(0, q3.getY() + q3.getHeight() + 2, generalStats.getWidth(), 25);
        q3Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        JLabel q4 = new JLabel("Q4", JLabel.CENTER);
        q4.setBounds(0, q3Score.getY() + q3Score.getHeight() + 4, generalStats.getWidth(), 25);
        q4.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q4Score = new JLabel(game.getQ4Score(), JLabel.CENTER);
        q4Score.setBounds(0, q4.getY() + q4.getHeight() + 2, generalStats.getWidth(), 25);
        q4Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        generalStats.add(q1);
        generalStats.add(q2);
        generalStats.add(q3);
        generalStats.add(q4);
        generalStats.add(q1Score);
        generalStats.add(q2Score);
        generalStats.add(q3Score);
        generalStats.add(q4Score);
    }
}

package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.TeamController;
import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import java.awt.*;

public class TeamView {
    private JPanel panel;
    private TeamModel team;
    private TeamController teamController;
    private TreeSeasonInfo info;

    public TeamView(JPanel panel, TeamModel team, TreeSeasonInfo info, TreeGames games){
        this.panel = panel;
        this.team = team;
        this.teamController = new TeamController(team, games);
        this.info = info;

        panel.removeAll();

        JPanel generalInfos = new JPanel();
        JPanel otherStats = new JPanel();

        generalInfos.setBounds(0,0, panel.getWidth(), panel.getHeight()/4 + 5);
        otherStats.setBounds(0, generalInfos.getHeight(), panel.getWidth(), 3*panel.getHeight()/4 - 5);
        generalInfos.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        generalInfos.setLayout(new GridBagLayout());
        otherStats.setLayout(null);

        fillGeneralInfos(generalInfos);

        panel.add(generalInfos);
        panel.add(otherStats);
    }

    private void fillGeneralInfos(JPanel generalInfos){
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 4, 1, 1, 1,
                GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 10);
        JLabel logoName = new JLabel(team.getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.CENTER);
        logoName.setFont(new Font(logoName.getFont().getName(), Font.BOLD, 30));
        logoName.setVerticalTextPosition(JLabel.BOTTOM);
        logoName.setHorizontalTextPosition(JLabel.CENTER);
        generalInfos.add(logoName, constraints);

        // titles
        JLabel headCoachTitle = new JLabel("Head Coach", JLabel.CENTER);
        headCoachTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));
        JLabel resultTitle = new JLabel("Results", JLabel.CENTER);
        resultTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));
        JLabel rankingTitle = new JLabel("Ranking", JLabel.CENTER);
        rankingTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));
        JLabel conferenceTitle = new JLabel("Conference", JLabel.CENTER);
        conferenceTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.33;
        constraints.ipady = 0;
        generalInfos.add(headCoachTitle, constraints);
        ++constraints.gridx;
        constraints.weightx = 0.22;
        generalInfos.add(resultTitle, constraints);
        ++constraints.gridx;
        generalInfos.add(rankingTitle, constraints);
        ++constraints.gridx;
        generalInfos.add(conferenceTitle, constraints);

        // titles
        JLabel headCoachName = new JLabel(team.getHeadCoach(), JLabel.CENTER);
        JLabel results = new JLabel(teamController.calculateTeamResults(), JLabel.CENTER);
        JLabel ranking = new JLabel("Ranking", JLabel.CENTER);
        Conference conf = new Conference("Void Conference");
        try {
            conf = info.getConferenceOfATeam(team);
        } catch (Exception e){
            e.printStackTrace();
        }
        JLabel conference = new JLabel(conf.getName(), JLabel.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.33;
        constraints.ipady = 0;
        generalInfos.add(headCoachName, constraints);
        ++constraints.gridx;
        constraints.weightx = 0.22;
        generalInfos.add(results, constraints);
        ++constraints.gridx;
        generalInfos.add(ranking, constraints);
        ++constraints.gridx;
        generalInfos.add(conference, constraints);
    }
}

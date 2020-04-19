package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamListView {
    private JPanel panel;
    private TreeSeasonInfo treeSeasonInfo;

    public TeamListView(JPanel panel){
        this.panel = panel;
        panel.removeAll();

        Parser parser = new Parser("src/main/resources/Season_19_20.xml");
        this.treeSeasonInfo = parser.getTreeSeason();
        TreeGames games = parser.getTreeSeasonGames(treeSeasonInfo);

        final JPanel teamLists = new JPanel(new GridBagLayout());

        teamLists.setLayout(new GridBagLayout());

        addButton(treeSeasonInfo.getConferences().get(0), teamLists, 0, games);
        addButton(treeSeasonInfo.getConferences().get(1), teamLists, 1, games);
        JScrollPane scrollPane = new JScrollPane(teamLists, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,0, panel.getWidth(), panel.getHeight());

        panel.add(scrollPane);
    }

    private void addButton(Conference conference, JPanel teamLists, int column, TreeGames games){
        JLabel columnName = new JLabel(conference.getName() + " Conference", JLabel.CENTER);
        GridBagConstraints constraints = new GridBagConstraints(column, 0, 1, 1, 0.25, 0.25,
                GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0);
        teamLists.add(columnName, constraints);
        int i = 1;
        for (TeamModel team : treeSeasonInfo.getTeamByConference(conference)){
            JButton button = new JButton(team.getName(), new ImageIcon("src/main/resources/Icons/jersey.png"));
            constraints.gridy = i;
            teamLists.add(button, constraints);
            ++i;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TeamView(panel, team, treeSeasonInfo, games);
                    SwingUtilities.updateComponentTreeUI(panel);
                }
            });
        }
    }
}

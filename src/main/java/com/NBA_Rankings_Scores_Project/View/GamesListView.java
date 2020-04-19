package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class GamesListView{
    private JPanel panel;
    private TreeSeasonInfo info;
    private TreeGames treeGames;

    public GamesListView (JPanel panel){
        this.panel = panel;
        panel.removeAll();

        Parser parser = new Parser("src/main/resources/Season_19_20.xml");
        this.info = parser.getTreeSeason();
        this.treeGames = parser.getTreeSeasonGames(info);
        List<GameModel> games = treeGames.getAllGames();
        int yPosButton = 0;
        for (GameModel game : games) {
            displayGame(game, yPosButton);
            yPosButton += 100;
        }
    }

    private void displayGame(final GameModel game, int yPosButton){
        JButton button = new JButton(game.getTotScore());
        button.setBounds(0, yPosButton, panel.getWidth(), 100);
        button.setLayout(null);
        this.panel.add(button);

        final Map<String, TeamModel> teams = treeGames.getTeamsOfGame(game);

        JLabel logoLeft = new JLabel(teams.get("Visitor").getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.LEFT);
        JLabel logoRight = new JLabel(teams.get("Home").getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.RIGHT);

        logoLeft.setVerticalTextPosition(JLabel.BOTTOM);
        logoLeft.setHorizontalTextPosition(JLabel.CENTER);
        logoRight.setVerticalTextPosition(JLabel.BOTTOM);
        logoRight.setHorizontalTextPosition(JLabel.CENTER);

        logoLeft.setBounds(10, 0, 500, 100);
        logoRight.setBounds(0, 0, button.getWidth() - 10, 100);

        button.add(logoLeft);
        button.add(logoRight);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameView(panel, game, teams.get("Home"), teams.get("Visitor"), info, treeGames);
                SwingUtilities.updateComponentTreeUI(panel);
            }
        });
    }
}

package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * View that display all the games
 */
public class GamesListView{
    private JPanel panel;
    private TreeSeasonInfo info;
    private TreeGames treeGames;

    /**
     * Constructor that initialize the data members and call the function to display the game list button
     * @param panel Panel where the content will be displayed
     */
    public GamesListView (JPanel panel){
        this.panel = panel;
        panel.removeAll();

        Parser parser = new Parser("src/main/resources/Season_19_20.xml");
        this.info = parser.getTreeSeason();
        this.treeGames = parser.getTreeSeasonGames(info);

        List<GameModel> games = treeGames.getAllGames();
        panel.setLayout(new GridLayout(6, 1));
        for (GameModel game : games)
            displayGame(game);
    }

    /**
     * Display the game list buttons
     * @param game Game to display
     */
    private void displayGame(final GameModel game){
        JButton button = new JButton(game.getTotScore());
        BorderLayout buttonLayout = new BorderLayout();
        buttonLayout.preferredLayoutSize(this.panel);
        button.setLayout(buttonLayout);
        this.panel.add(button);

        final Map<String, TeamModel> teams = treeGames.getTeamsOfGame(game);

        JLabel logoLeft = new JLabel(teams.get("Visitor").getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.CENTER);
        JLabel logoRight = new JLabel(teams.get("Home").getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"), JLabel.CENTER);

        logoLeft.setVerticalTextPosition(JLabel.BOTTOM);
        logoLeft.setHorizontalTextPosition(JLabel.CENTER);
        logoRight.setVerticalTextPosition(JLabel.BOTTOM);
        logoRight.setHorizontalTextPosition(JLabel.CENTER);

        button.add(logoLeft,BorderLayout.LINE_START);
        button.add(logoRight, BorderLayout.LINE_END);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameView(panel, game, teams.get("Home"), teams.get("Visitor"), info, treeGames);
                SwingUtilities.updateComponentTreeUI(panel);
            }
        });
    }
}

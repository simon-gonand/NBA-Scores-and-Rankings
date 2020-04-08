package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Tree.Tree;
import com.NBA_Rankings_Scores_Project.View.GamesListView;

import javax.swing.*;
import java.awt.*;

public class System {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds(0,0,screenSize.width,screenSize.height);
        window.setTitle("NBA: Scores & Rankings");
        ImageIcon icon = new ImageIcon("src/main/resources/Icons/basketball.png");
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0,0,screenSize.width/5, screenSize.height);
        menuPanel.setBackground(Color.lightGray);

        JButton gamesScoreViewButton = new JButton("Games Scores");
        gamesScoreViewButton.setForeground(Color.white);
        gamesScoreViewButton.setBackground(Color.darkGray);
        gamesScoreViewButton.setBounds(0,0, menuPanel.getWidth(), 40);

        menuPanel.add(gamesScoreViewButton);

        window.add(menuPanel);

        new GamesListView(window);

        /*Parser p = new Parser("src/main/resources/Season_19_20.xml");
        Tree tree = p.getTreeSeason();
        tree.show();*/
    }
}
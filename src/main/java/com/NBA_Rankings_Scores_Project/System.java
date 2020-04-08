package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Tree.Tree;
import com.NBA_Rankings_Scores_Project.View.GamesListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class System {
    private static JFrame window;
    private static JPanel contentPanel;

    public static void main(String[] args) {
        window = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds(0,0,screenSize.width,screenSize.height);
        window.setTitle("NBA: Scores & Rankings");
        ImageIcon icon = new ImageIcon("src/main/resources/Icons/logo.png");
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);

        JPanel menuPanel = new JPanel();
        window.add(menuPanel);
        menuPanel.setLayout(null);
        menuPanel.setBounds(0,0,screenSize.width/5, screenSize.height);
        menuPanel.setBackground(Color.white);

        contentPanel = new JPanel();
        window.add(contentPanel);
        contentPanel.setLayout(null);
        contentPanel.setBounds(menuPanel.getWidth(), 0, 4*screenSize.width/5, screenSize.height);

        final JButton gamesScoreViewButton = new JButton("Games Scores", new ImageIcon("src/main/resources/Icons/basketball.png"));
        final JButton teamsViewButton = new JButton ("Teams             ", new ImageIcon("src/main/resources/Icons/jersey.png"));
        final JButton rankingsViewButton = new JButton("Rankings        ", new ImageIcon("src/main/resources/Icons/cup.png"));

        menuPanel.add(gamesScoreViewButton);
        menuPanel.add(teamsViewButton);
        menuPanel.add(rankingsViewButton);

        gamesScoreViewButton.setBackground(Color.white);
        gamesScoreViewButton.setBounds(0,0, menuPanel.getWidth(), 70);
        gamesScoreViewButton.setBorder(BorderFactory.createEmptyBorder());
        gamesScoreViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = gamesScoreViewButton.getBackground();
                gamesScoreViewButton.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                gamesScoreViewButton.setBackground(oldColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new GamesListView(contentPanel);
                teamsViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.white);
                gamesScoreViewButton.setBackground(Color.lightGray);
                oldColor = gamesScoreViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });

        teamsViewButton.setBackground(Color.white);
        teamsViewButton.setBounds(0,gamesScoreViewButton.getHeight(), menuPanel.getWidth(), 70);
        teamsViewButton.setBorder(BorderFactory.createEmptyBorder());
        teamsViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = teamsViewButton.getBackground();
                teamsViewButton.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                teamsViewButton.setBackground(oldColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // create teams list view
                gamesScoreViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.white);
                teamsViewButton.setBackground(Color.lightGray);
                oldColor = teamsViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });

        rankingsViewButton.setBackground(Color.white);
        rankingsViewButton.setBounds(0,gamesScoreViewButton.getHeight()*2, menuPanel.getWidth(), 70);
        rankingsViewButton.setBorder(BorderFactory.createEmptyBorder());
        rankingsViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = rankingsViewButton.getBackground();
                rankingsViewButton.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                rankingsViewButton.setBackground(oldColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // create teams list view
                gamesScoreViewButton.setBackground(Color.white);
                teamsViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.lightGray);
                oldColor = rankingsViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });


        /*Parser p = new Parser("src/main/resources/Season_19_20.xml");
        Tree tree = p.getTreeSeason();
        tree.show();*/
    }
}
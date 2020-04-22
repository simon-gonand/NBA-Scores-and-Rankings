package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.View.GamesListView;
import com.NBA_Rankings_Scores_Project.View.ResearchView;
import com.NBA_Rankings_Scores_Project.View.TeamListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class System {
    private static JFrame window;
    private static JPanel contentPanel;

    public static void main(String[] args) {

        window = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setTitle("NBA: Scores & Rankings");
        ImageIcon icon = new ImageIcon("src/main/resources/Icons/logo.png");
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel menuPanel = new JPanel();
        window.add(menuPanel);
        menuPanel.setLayout(null);
        menuPanel.setBounds(0,0,window.getWidth()/5, window.getHeight());
        menuPanel.setBackground(Color.white);
        menuPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,3, Color.black));

        contentPanel = new JPanel();
        window.add(contentPanel);
        contentPanel.setLayout(null);
        contentPanel.setBounds(menuPanel.getWidth(), 0, 4*window.getWidth()/5-13, window.getHeight()-36);

        final JButton gamesScoreViewButton = new JButton("Games Scores", new ImageIcon("src/main/resources/Icons/basketball.png"));
        final JButton teamsViewButton = new JButton ("Teams             ", new ImageIcon("src/main/resources/Icons/jersey.png"));
        final JButton rankingsViewButton = new JButton("Rankings        ", new ImageIcon("src/main/resources/Icons/cup.png"));
        final JButton researchViewButton = new JButton("Research        ", new ImageIcon("src/main/resources/Icons/research.png"));

        menuPanel.add(gamesScoreViewButton);
        menuPanel.add(teamsViewButton);
        menuPanel.add(rankingsViewButton);
        menuPanel.add(researchViewButton);

        gamesScoreViewButton.setBackground(Color.white);
        gamesScoreViewButton.setBounds(0,0, menuPanel.getWidth()-3, 100);
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
                researchViewButton.setBackground(Color.white);
                gamesScoreViewButton.setBackground(Color.lightGray);
                oldColor = gamesScoreViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });

        teamsViewButton.setBackground(Color.white);
        teamsViewButton.setBounds(0,gamesScoreViewButton.getHeight(), menuPanel.getWidth()-3, 100);
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
                new TeamListView(contentPanel);
                gamesScoreViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.white);
                researchViewButton.setBackground(Color.white);
                teamsViewButton.setBackground(Color.lightGray);
                oldColor = teamsViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });

        rankingsViewButton.setBackground(Color.white);
        rankingsViewButton.setBounds(0,gamesScoreViewButton.getHeight()*2, menuPanel.getWidth()-3, 100);
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
                researchViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.lightGray);
                oldColor = rankingsViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });

        researchViewButton.setBackground(Color.white);
        researchViewButton.setBounds(0,rankingsViewButton.getY() + rankingsViewButton.getHeight(), menuPanel.getWidth()-3, 100);
        researchViewButton.setBorder(BorderFactory.createEmptyBorder());
        researchViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = researchViewButton.getBackground();
                researchViewButton.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                researchViewButton.setBackground(oldColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new ResearchView(contentPanel);
                gamesScoreViewButton.setBackground(Color.white);
                teamsViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.white);
                researchViewButton.setBackground(Color.lightGray);
                oldColor = researchViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });
    }
}
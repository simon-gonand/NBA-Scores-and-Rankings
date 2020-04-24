package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.View.GamesListView;
import com.NBA_Rankings_Scores_Project.View.RankingsView;
import com.NBA_Rankings_Scores_Project.View.ResearchView;
import com.NBA_Rankings_Scores_Project.View.TeamListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main class System that create a window and the main panels (menu and contents), fill the menu panel as well
 */
public class System {
    private static JFrame window;

    /**
     * Main function
     * Create a window and the main panels (menu and content)
     * @param args
     */
    public static void main(String[] args) {

        window = new JFrame();

        window.setTitle("NBA: Scores & Rankings");
        ImageIcon icon = new ImageIcon("src/main/resources/Icons/logo.png");
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout windowLayout = new GridBagLayout();
        window.setLayout(windowLayout);
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set fullscreen window

        // Constraints for layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0; // row
        constraints.gridy = 0; // column
        constraints.weightx = 0.2;
        constraints.weighty = 1;

        // Menu panel
        JPanel menuPanel = new JPanel(new GridBagLayout());
        window.add(menuPanel, constraints);
        menuPanel.setBackground(Color.white);
        menuPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,3, Color.black));

        constraints.gridx = 1;
        constraints.weightx = 0.8;
        // Content panel
        JPanel contentPanel = new JPanel();
        window.add(contentPanel, constraints);

        fillMenuPanel(contentPanel, menuPanel);
    }

    /**
     * Method that fill the menuPanel
     * @param contentPanel Panel which will receive the content for the rest of the application
     * @param menuPanel Panel which will receive the buttons
     */
    private static void fillMenuPanel(JPanel contentPanel, JPanel menuPanel){
        final JButton gamesScoreViewButton = new JButton("Games Scores", new ImageIcon("src/main/resources/Icons/basketball.png"));
        final JButton teamsViewButton = new JButton ("Teams             ", new ImageIcon("src/main/resources/Icons/jersey.png"));
        final JButton rankingsViewButton = new JButton("Rankings        ", new ImageIcon("src/main/resources/Icons/cup.png"));
        final JButton researchViewButton = new JButton("Research        ", new ImageIcon("src/main/resources/Icons/research.png"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipady = 60;

        menuPanel.add(gamesScoreViewButton, constraints);
        constraints.gridy = 1;
        menuPanel.add(teamsViewButton, constraints);
        constraints.gridy = 2;
        menuPanel.add(rankingsViewButton, constraints);
        constraints.gridy = 3;
        menuPanel.add(researchViewButton, constraints);

        setGamesListButton(gamesScoreViewButton, teamsViewButton, rankingsViewButton, researchViewButton, contentPanel);
        setTeamsListButton(teamsViewButton, gamesScoreViewButton, rankingsViewButton, researchViewButton, contentPanel);
        setRankingsButton(rankingsViewButton, gamesScoreViewButton, teamsViewButton, researchViewButton, contentPanel);
        setResearchButton(researchViewButton, gamesScoreViewButton, teamsViewButton, rankingsViewButton, contentPanel);
    }

    /**
     * Set settings of the GamesListButton and add an MouseListener
     * @param gamesScoreViewButton Button which will be set
     * @param teamsViewButton,rankingsViewButton,researchViewButton Button which will be deactivate when the gamesScoreViewButton is active
     * @param contentPanel Panel which will display the GamesListView
     * @see GamesListView
     */
    private static void setGamesListButton (JButton gamesScoreViewButton, JButton teamsViewButton, JButton rankingsViewButton,
                                     JButton researchViewButton, JPanel contentPanel){
        gamesScoreViewButton.setFont(new Font(gamesScoreViewButton.getName(), Font.BOLD, 15));
        gamesScoreViewButton.setBackground(Color.white);
        gamesScoreViewButton.setBorder(BorderFactory.createEmptyBorder());
        gamesScoreViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            // When the mouse is over the button
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = gamesScoreViewButton.getBackground();
                gamesScoreViewButton.setBackground(Color.lightGray);
            }

            // When the mouse isn't over the button anymore
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                gamesScoreViewButton.setBackground(oldColor);
            }

            // When the user click on the button
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
    }

    /**
     * Set settings of the TeamsListButton and add an MouseListener
     * @param teamsViewButton Button which will be set
     * @param gamesScoreViewButton,rankingsViewButton,researchViewButton Button which will be deactivate when the teamsViewButton is active
     * @param contentPanel Panel which will display the TeamListView
     * @see TeamListView
     */
    private static void setTeamsListButton(JButton teamsViewButton, JButton gamesScoreViewButton, JButton rankingsViewButton,
                                           JButton researchViewButton, JPanel contentPanel){
        teamsViewButton.setFont(new Font(teamsViewButton.getName(), Font.BOLD, 15));
        teamsViewButton.setBackground(Color.white);
        teamsViewButton.setBorder(BorderFactory.createEmptyBorder());
        teamsViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            // When the mouse is over the button
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = teamsViewButton.getBackground();
                teamsViewButton.setBackground(Color.lightGray);
            }

            // When the mouse isn't over the button anymore
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                teamsViewButton.setBackground(oldColor);
            }

            // When the user click on the button
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
    }

    /**
     * Set settings of the RankingsButton and add an MouseListener
     * @param rankingsViewButton Button which will be set
     * @param gamesScoreViewButton,teamsViewButton,researchViewButton Button which will be deactivate when the rankingsViewButton is active
     * @param contentPanel Panel which will display the RankingsView
     * @see RankingsView
     */
    private static void setRankingsButton(JButton rankingsViewButton, JButton gamesScoreViewButton, JButton teamsViewButton,
                                          JButton researchViewButton, JPanel contentPanel){
        rankingsViewButton.setFont(new Font(rankingsViewButton.getName(), Font.BOLD, 15));
        rankingsViewButton.setBackground(Color.white);
        rankingsViewButton.setBorder(BorderFactory.createEmptyBorder());
        rankingsViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            // When the mouse is over the button
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = rankingsViewButton.getBackground();
                rankingsViewButton.setBackground(Color.lightGray);
            }

            // When the mouse isn't over the button anymore
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                rankingsViewButton.setBackground(oldColor);
            }

            // When the user click on the button
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new RankingsView(contentPanel);
                gamesScoreViewButton.setBackground(Color.white);
                teamsViewButton.setBackground(Color.white);
                researchViewButton.setBackground(Color.white);
                rankingsViewButton.setBackground(Color.lightGray);
                oldColor = rankingsViewButton.getBackground();
                SwingUtilities.updateComponentTreeUI(contentPanel);
            }
        });
    }

    /**
     * Set settings of the ResearchButton and add an MouseListener
     * @param researchViewButton Button which will be set
     * @param gamesScoreViewButton,teamsViewButton,rankingsViewButton Button which will be deactivate when the researchViewButton is active
     * @param contentPanel Panel which will display the ResearchView
     * @see ResearchView
     */
    private static void setResearchButton(JButton researchViewButton, JButton gamesScoreViewButton, JButton teamsViewButton,
                                          JButton rankingsViewButton, JPanel contentPanel){
        researchViewButton.setFont(new Font(researchViewButton.getName(), Font.BOLD, 15));
        researchViewButton.setBackground(Color.white);
        researchViewButton.setBorder(BorderFactory.createEmptyBorder());
        researchViewButton.addMouseListener(new MouseAdapter() {
            Color oldColor;
            // When the mouse is over the button
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                oldColor = researchViewButton.getBackground();
                researchViewButton.setBackground(Color.lightGray);
            }

            // When the mouse isn't over the button anymore
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                researchViewButton.setBackground(oldColor);
            }

            // When the user click on the button
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
package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.GameController;
import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GameView {
    private JPanel panel;
    private JScrollPane table;

    private GameModel game;
    private GameController gameController;
    private TreeSeasonInfo info;
    private TreeGames games;
    private TeamModel home, visitor;

    public GameView(JPanel panel, GameModel game, TeamModel home, TeamModel visitor,
                    TreeSeasonInfo info, TreeGames games){
        this.panel = panel;
        this.game = game;
        this.info = info;
        this.games = games;
        this.gameController = new GameController(game);
        this.home = home;
        this.visitor = visitor;
        panel.removeAll();

        JPanel generalStats = new JPanel();
        JPanel otherStats = new JPanel();

        generalStats.setBounds(0,0, panel.getWidth(), panel.getHeight()/3 + 5);
        otherStats.setBounds(0, generalStats.getHeight(), panel.getWidth(), 2*panel.getHeight()/3);
        generalStats.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        generalStats.setLayout(null);
        otherStats.setLayout(null);

        this.panel.add(generalStats);
        this.panel.add(otherStats);

        fillGeneralStatsPanel(generalStats);
        fillOtherStatsPanel(otherStats);
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

        displayScores(generalStats);

        generalStats.add(homeTeamLabel);
        generalStats.add(visitorTeamLabel);
    }

    private void displayScores(JPanel generalStats){
        JLabel totScore = new JLabel(game.getTotScore(), JLabel.CENTER);
        totScore.setBounds(0, 15, generalStats.getWidth(), 50);
        totScore.setFont(new Font(totScore.getFont().getName(), Font.BOLD, 35));

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

        generalStats.add(totScore);
        generalStats.add(q1);
        generalStats.add(q2);
        generalStats.add(q3);
        generalStats.add(q4);
        generalStats.add(q1Score);
        generalStats.add(q2Score);
        generalStats.add(q3Score);
        generalStats.add(q4Score);
    }

    private void fillOtherStatsPanel(final JPanel otherStats){
        final JButton visitorButton = new JButton("Visitor");
        final JButton homeButton = new JButton("Home");

        JLabel title = new JLabel("Statistics", JLabel.CENTER);
        title.setBounds(0, 5, otherStats.getWidth(), 40);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
        otherStats.add(title);

        final JPanel statsMenu = new JPanel(new GridBagLayout());
        statsMenu.setBounds(0, title.getY() + title.getHeight(), otherStats.getWidth(), 150);
        otherStats.add(statsMenu);
        final GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-25,0,0,0), 30, 25);

        JButton players = new JButton("Players");
        statsMenu.add(players, constraints);
        players.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table != null)
                    otherStats.remove(table);
                visitorButton.setVisible(true);
                homeButton.setVisible(true);
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridheight = 3;
                constraints.ipadx = 20;
                constraints.ipady = 15;
                constraints.anchor = GridBagConstraints.LINE_END;
                constraints.insets.top = 15;
                constraints.insets.bottom = 0;
                statsMenu.add(visitorButton, constraints);

                constraints.gridx = 1;
                constraints.anchor = GridBagConstraints.LINE_START;
                statsMenu.add(homeButton, constraints);

                visitorButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (table != null)
                            otherStats.remove(table);
                        table = fillPlayerStats(visitor, statsMenu, otherStats);
                        SwingUtilities.updateComponentTreeUI(otherStats);
                    }
                });
                homeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (table != null)
                            otherStats.remove(table);
                        table = fillPlayerStats(home, statsMenu, otherStats);
                        SwingUtilities.updateComponentTreeUI(otherStats);
                    }
                });
                SwingUtilities.updateComponentTreeUI(otherStats);
            }
        });

        constraints.gridx = 1;
        final JButton teams = new JButton("Teams");
        statsMenu.add(teams, constraints);
        teams.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statsMenu.remove(visitorButton);
                statsMenu.remove(homeButton);
                visitorButton.setVisible(false);
                homeButton.setVisible(false);
                if (table != null)
                    otherStats.remove(table);
                table = fillTeamsStats(statsMenu, otherStats);
                SwingUtilities.updateComponentTreeUI(otherStats);
            }
        });
    }

    private JScrollPane fillPlayerStats(TeamModel team, JPanel statsMenu, JPanel otherStats){
        String[] columnsName = {"Player", "Min", "Points", "Rebounds", "Assists", "FG",
                "3pt", "FT", "Steals", "Blocks", "TO"};

        Object[][] data = new Object[games.getPlayerStatsByTeam(game, team).size()][11];
        for (PlayerStats playerStats : games.getPlayerStatsByTeam(game, team)){
            Object[] tmp = {info.getPlayersByTeam(team).get(playerStats.getID()).getSurname(),
                    playerStats.getMinutes(),
                    playerStats.getPoints(),
                    playerStats.getRebounds(),
                    playerStats.getAssists(),
                    playerStats.getFg(),
                    playerStats.getThreePts(),
                    playerStats.getFt(),
                    playerStats.getSteals(),
                    playerStats.getBlocks(),
                    playerStats.getTurnovers()};
            data[playerStats.getID()] = tmp;
        }
        JTable stats = new JTable(new DefaultTableModel(data, columnsName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        // Set cell sizes
        stats.setRowHeight(25);
        stats.getColumnModel().getColumn(0).setPreferredWidth(150);
        stats.getColumnModel().getColumn(1).setPreferredWidth(20);
        stats.getColumnModel().getColumn(2).setPreferredWidth(20);
        stats.getColumnModel().getColumn(3).setPreferredWidth(20);
        stats.getColumnModel().getColumn(4).setPreferredWidth(20);
        stats.getColumnModel().getColumn(8).setPreferredWidth(20);
        stats.getColumnModel().getColumn(9).setPreferredWidth(20);
        stats.getColumnModel().getColumn(10).setPreferredWidth(20);

        // Set horizontal alignement to center for every cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        stats.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(stats);
        scrollPane.setBounds(0, statsMenu.getY() + statsMenu.getHeight(), otherStats.getWidth(), otherStats.getHeight() - (statsMenu.getY() + statsMenu.getHeight()+67));
        otherStats.add(scrollPane);
        return scrollPane;
    }

    private JScrollPane fillTeamsStats(JPanel statsMenu, JPanel otherStats){
        String[] columnsName = {"Team", "Points", "Rebounds", "Assists", "%Field Goal", "%3pt", "FT", "Steals", "Blocks", "Turnovers"};

        Object[][] data = new Object[2][10];
        Map<String, Object> homeStats = gameController.calculateTeamStats(games.getPlayerStatsByTeam(game, home));
        Object[] homeTmp = {home.getName(),
                homeStats.get("Points"),
                homeStats.get("Rebounds"),
                homeStats.get("Assists"),
                homeStats.get("FG"),
                homeStats.get("3pt"),
                homeStats.get("FT"),
                homeStats.get("Steals"),
                homeStats.get("Blocks"),
                homeStats.get("Turnovers")};
        data[0] = homeTmp;
        Map<String, Object> visitorStats = gameController.calculateTeamStats(games.getPlayerStatsByTeam(game, visitor));
        Object[] visitorTmp = { visitor.getName(),
                visitorStats.get("Points"),
                visitorStats.get("Rebounds"),
                visitorStats.get("Assists"),
                visitorStats.get("FG"),
                visitorStats.get("3pt"),
                visitorStats.get("FT"),
                visitorStats.get("Steals"),
                visitorStats.get("Blocks"),
                visitorStats.get("Turnovers")};

        data[1] = visitorTmp;

        JTable stats = new JTable(new DefaultTableModel(data, columnsName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        stats.setRowHeight(30);
        stats.getColumnModel().getColumn(0).setPreferredWidth(175);
        stats.getColumnModel().getColumn(2).setPreferredWidth(20);
        stats.getColumnModel().getColumn(3).setPreferredWidth(20);
        stats.getColumnModel().getColumn(7).setPreferredWidth(20);
        stats.getColumnModel().getColumn(8).setPreferredWidth(20);
        stats.getColumnModel().getColumn(9).setPreferredWidth(20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        stats.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(stats);
        scrollPane.setBounds(0, statsMenu.getY() + statsMenu.getHeight(), otherStats.getWidth(), otherStats.getHeight() - (statsMenu.getY() + statsMenu.getHeight()+67));
        otherStats.add(scrollPane);
        return scrollPane;
    }
}

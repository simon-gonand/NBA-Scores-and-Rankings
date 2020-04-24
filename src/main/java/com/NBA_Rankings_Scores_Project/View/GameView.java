package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.GameController;
import com.NBA_Rankings_Scores_Project.Controllers.TeamController;
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

/**
 * View that display the information and the statistics of a game
 */
public class GameView {
    private JPanel panel;
    private JScrollPane table;

    private GameModel game;
    private GameController gameController;
    private TreeSeasonInfo info;
    private TreeGames games;
    private TeamModel home, visitor;

    /**
     * Constructor which initialize the data members
     * @param panel Panel where the view will be displayed
     * @param game GameModel of the game that will be displayed
     * @param home Home TeamModel of the game
     * @param visitor Visitor TeamModel of the game
     * @param info Tree with the general information on the current season
     * @param games Tree with all the games and the statistics of the games
     */
    public GameView(JPanel panel, GameModel game, TeamModel home, TeamModel visitor,
                    TreeSeasonInfo info, TreeGames games){
        this.panel = panel;
        this.game = game;
        this.info = info;
        this.games = games;
        this.gameController = new GameController(game);
        this.home = home;
        this.visitor = visitor;
        this.panel.removeAll();

        this.panel.setLayout(new GridBagLayout());

        JPanel generalStats = new JPanel(new GridBagLayout());
        JPanel otherStats = new JPanel(new GridBagLayout());

        generalStats.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        this.panel.add(generalStats, constraints);
        constraints.gridy = 1;
        constraints.weighty = 1;
        this.panel.add(otherStats, constraints);

        fillGeneralStatsPanel(generalStats);
        fillOtherStatsPanel(otherStats);
    }

    /**
     * To fill the general information of the game
     * @param generalStats Panel where the info will be displayed
     */
    private void fillGeneralStatsPanel(JPanel generalStats){
        JPanel homeTeamPanel = new JPanel (new GridLayout(2,1));
        JPanel visitorTeamPanel = new JPanel (new GridLayout(2,1));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        generalStats.add(homeTeamPanel, constraints);
        constraints.gridx = 2;
        generalStats.add(visitorTeamPanel, constraints);

        JLabel homeTeamLabel = new JLabel(home.getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.RIGHT);
        JLabel homeTeamResult = new JLabel(new TeamController(home, games).calculateTeamResults(), JLabel.CENTER);
        homeTeamResult.setFont(new Font(homeTeamResult.getFont().getName(), Font.BOLD, 15));
        JLabel visitorTeamLabel = new JLabel (visitor.getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.LEFT);
        JLabel visitorTeamResult = new JLabel(new TeamController(visitor, games).calculateTeamResults(), JLabel.CENTER);
        visitorTeamResult.setFont(new Font(visitorTeamResult.getFont().getName(), Font.BOLD, 15));

        homeTeamLabel.setHorizontalTextPosition(JLabel.CENTER);
        homeTeamLabel.setVerticalTextPosition(JLabel.BOTTOM);
        visitorTeamLabel.setHorizontalTextPosition(JLabel.CENTER);
        visitorTeamLabel.setVerticalTextPosition(JLabel.BOTTOM);


        displayScores(generalStats, constraints);

        homeTeamPanel.add(homeTeamLabel);
        homeTeamPanel.add(homeTeamResult);
        visitorTeamPanel.add(visitorTeamLabel);
        visitorTeamPanel.add(visitorTeamResult);
    }

    /**
     * To displayed the scores
     * @param generalStats Panel where the scores will be displayed
     * @param constraints Constraints of the generalStats layout
     */
    private void displayScores(JPanel generalStats, GridBagConstraints constraints){
        JLabel totScore = new JLabel(game.getTotScore(), JLabel.CENTER);
        totScore.setFont(new Font(totScore.getFont().getName(), Font.BOLD, 30));

        JLabel q1 = new JLabel("Q1", JLabel.CENTER);
        q1.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q1Score = new JLabel(game.getQ1Score(), JLabel.CENTER);
        q1Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        JLabel q2 = new JLabel("Q2", JLabel.CENTER);
        q2.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q2Score = new JLabel(game.getQ2Score(), JLabel.CENTER);
        q2Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        JLabel q3 = new JLabel("Q3", JLabel.CENTER);
        q3.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q3Score = new JLabel(game.getQ3Score(), JLabel.CENTER);
        q3Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        JLabel q4 = new JLabel("Q4", JLabel.CENTER);
        q4.setFont(new Font(q1.getFont().getName(), Font.BOLD, 20));

        JLabel q4Score = new JLabel(game.getQ4Score(), JLabel.CENTER);
        q4Score.setFont(new Font(totScore.getFont().getName(), Font.PLAIN, 15));

        constraints.gridx = 1;
        generalStats.add(totScore, constraints);

        JPanel scores = new JPanel(new GridLayout(8,1));
        scores.add(q1);
        scores.add(q1Score);
        scores.add(q2);
        scores.add(q2Score);
        scores.add(q3);
        scores.add(q3Score);
        scores.add(q4);
        scores.add(q4Score);

        constraints.anchor = GridBagConstraints.PAGE_END;
        generalStats.add(scores, constraints);
    }

    /**
     * To fill the panel with the statistics
     * @param otherStats Panel where the statistics will be displayed
     */
    private void fillOtherStatsPanel(final JPanel otherStats){
        final JButton visitorButton = new JButton("Visitor");
        final JButton homeButton = new JButton("Home");

        GridBagConstraints otherStatsConstraints = new GridBagConstraints();
        otherStatsConstraints.gridx = 0;
        otherStatsConstraints.gridy = 0;
        otherStatsConstraints.anchor = GridBagConstraints.PAGE_START;
        otherStatsConstraints.fill = GridBagConstraints.HORIZONTAL;
        otherStatsConstraints.weighty = 1;
        otherStatsConstraints.weightx = 1;

        JLabel title = new JLabel("Statistics", JLabel.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
        otherStats.add(title, otherStatsConstraints);

        otherStatsConstraints.gridy = 1;
        final JPanel statsMenu = new JPanel(new GridBagLayout());
        otherStats.add(statsMenu, otherStatsConstraints);
        final GridBagConstraints statsMenuConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 30, 25);

        JButton players = new JButton("Players");
        statsMenu.add(players, statsMenuConstraints);
        otherStatsConstraints.gridy = 2;
        players.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table != null)
                    otherStats.remove(table);
                visitorButton.setVisible(true);
                homeButton.setVisible(true);
                statsMenuConstraints.gridx = 0;
                statsMenuConstraints.gridy = 1;
                statsMenuConstraints.gridheight = 3;
                statsMenuConstraints.ipadx = 20;
                statsMenuConstraints.ipady = 15;
                statsMenuConstraints.anchor = GridBagConstraints.PAGE_START;
                statsMenuConstraints.insets.top = 0;
                statsMenuConstraints.insets.bottom = 0;
                statsMenu.add(visitorButton, statsMenuConstraints);

                statsMenuConstraints.gridx = 1;
                statsMenuConstraints.anchor = GridBagConstraints.LINE_START;
                statsMenu.add(homeButton, statsMenuConstraints);

                visitorButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (table != null)
                            otherStats.remove(table);
                        table = fillPlayerStats(visitor);
                        otherStats.add(table, otherStatsConstraints);
                        SwingUtilities.updateComponentTreeUI(otherStats);
                    }
                });
                homeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (table != null)
                            otherStats.remove(table);
                        table = fillPlayerStats(home);
                        otherStats.add(table, otherStatsConstraints);
                        SwingUtilities.updateComponentTreeUI(otherStats);
                    }
                });
                SwingUtilities.updateComponentTreeUI(otherStats);
            }
        });

        statsMenuConstraints.gridx = 1;
        final JButton teams = new JButton("Teams");
        statsMenu.add(teams, statsMenuConstraints);
        teams.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statsMenu.remove(visitorButton);
                statsMenu.remove(homeButton);
                visitorButton.setVisible(false);
                homeButton.setVisible(false);
                if (table != null)
                    otherStats.remove(table);
                table = fillTeamsStats();
                otherStats.add(table, otherStatsConstraints);
                SwingUtilities.updateComponentTreeUI(otherStats);
            }
        });
    }

    /**
     * To fill the players's statistics of a team
     * @param team Team of the players
     * @return ScrollPane where the statistics are
     */
    private JScrollPane fillPlayerStats(TeamModel team){
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

        // Set horizontal alignment to center for every cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        stats.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(stats);
        return scrollPane;
    }

    /**
     * To fill the teams statistics of the game
     * @return ScrollPane where the statistics are
     */
    private JScrollPane fillTeamsStats(){
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
        return scrollPane;
    }
}

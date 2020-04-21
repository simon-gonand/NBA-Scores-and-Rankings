package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.PlayerController;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Tree.Tree;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class PlayerView {
    private JPanel panel;
    private PlayerModel player;
    private TreeSeasonInfo info;
    private TreeGames games;

    public PlayerView(JPanel panel, PlayerModel player, TreeSeasonInfo info, TreeGames games){
        this.panel = panel;
        this.player = player;
        this.info = info;
        this.games = games;
        panel.removeAll();

        JPanel generalInfos = new JPanel(new GridBagLayout());
        JPanel otherStats = new JPanel(new GridBagLayout());

        generalInfos.setBounds(0,0, panel.getWidth(), panel.getHeight() / 4);
        otherStats.setBounds(0,generalInfos.getHeight(), panel.getWidth(), 3*panel.getHeight() / 4);
        generalInfos.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        fillGeneralInfos(generalInfos);
        fillStats(otherStats);

        panel.add(generalInfos);
        panel.add(otherStats);
    }

    private void fillGeneralInfos(JPanel generalInfos){
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0);

        JLabel playerName = new JLabel(player.getName(), JLabel.CENTER);
        JLabel playerSurname = new JLabel(player.getSurname(), JLabel.CENTER);
        JLabel teamTitle = new JLabel("Team", JLabel.CENTER);
        JLabel positionTitle = new JLabel("Position", JLabel.CENTER);
        JLabel numberTitle = new JLabel("Number", JLabel.CENTER);
        JLabel ageTitle = new JLabel("Age", JLabel.CENTER);
        JLabel nationalityTitle = new JLabel("Nationality", JLabel.CENTER);
        JLabel position = new JLabel(player.getPosition().toString(), JLabel.CENTER);
        JLabel number = new JLabel(String.valueOf(player.getNumber()), JLabel.CENTER);
        JLabel age = new JLabel(String.valueOf(player.getAge()), JLabel.CENTER);
        JLabel nationality = new JLabel(player.getNationality(), JLabel.CENTER);

        Font fontNames = new Font(playerName.getFont().getName(), Font.BOLD, 30);
        playerName.setFont(fontNames);
        playerSurname.setFont(fontNames);

        Font fontTitle = new Font(teamTitle.getFont().getName(), Font.BOLD, 20);
        teamTitle.setFont(fontTitle);
        positionTitle.setFont(fontTitle);
        numberTitle.setFont(fontTitle);
        ageTitle.setFont(fontTitle);
        nationalityTitle.setFont(fontTitle);

        Font font = new Font(position.getFont().getName(), Font.PLAIN, 15);
        position.setFont(font);
        number.setFont(font);
        age.setFont(font);
        nationality.setFont(font);

        try{
            JLabel team = new JLabel(info.getTeamOfAPlayer(player).getName(), JLabel.CENTER);
            team.setFont(font);
            constraints.gridy = 2;
            generalInfos.add(team, constraints);
            constraints.gridy = 0;
        } catch(Exception e){
            e.printStackTrace();
        }

        generalInfos.add(playerName, constraints);
        constraints.gridx = 1;
        generalInfos.add(playerSurname, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets.top = 10;
        generalInfos.add(teamTitle, constraints);
        constraints.gridx = 1;
        generalInfos.add(positionTitle, constraints);
        constraints.gridx = 2;
        generalInfos.add(numberTitle, constraints);
        constraints.gridx = 3;
        generalInfos.add(ageTitle, constraints);
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.insets.top = 0;
        generalInfos.add(position, constraints);
        constraints.gridx = 2;
        generalInfos.add(number, constraints);
        constraints.gridx = 3;
        generalInfos.add(age, constraints);
        constraints.gridy = 3;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        generalInfos.add(nationalityTitle, constraints);
        constraints.gridy = 4;
        generalInfos.add(nationality, constraints);
    }

    private void fillStats(JPanel otherStats){
        String[] columnNames = {"Stats", "Values"};
        PlayerController playerController = new PlayerController(player, info, games);
        Map<String, String> playerSeasonStats = playerController.calculatePlayerSeasonStats();
        String[][] data = {
                {"Games Played", playerSeasonStats.get("GamesPlayed")},
                {"Minutes", playerSeasonStats.get("Minutes")},
                {"Points", playerSeasonStats.get("Points")},
                {"Rebounds", playerSeasonStats.get("Rebounds")},
                {"Assists", playerSeasonStats.get("Assists")},
                {"% Field Goal", playerSeasonStats.get("FG")},
                {"% Free Throws", playerSeasonStats.get("FT")},
                {"% 3-Points", playerSeasonStats.get("3pt")},
                {"Steals", playerSeasonStats.get("Steals")},
                {"Blocks", playerSeasonStats.get("Blocks")},
                {"Turnovers", playerSeasonStats.get("Turnovers")},
        };

        JTable tableStats = new JTable(new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tableStats.setRowHeight(40);
        tableStats.getColumnModel().getColumn(0).setMaxWidth(200);
        tableStats.getColumnModel().getColumn(1).setMaxWidth(100);
        DefaultTableCellRenderer valuesRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        valuesRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableStats.getColumnModel().getColumn(1).setCellRenderer(valuesRenderer);

        tableStats.setBackground(new Color(238,238,238));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableStats.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(tableStats, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,200,0,0), 0, 60);
        otherStats.add(scrollPane, constraints);
    }
}

package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.TeamController;
import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;

public class TeamView {
    private JPanel panel;
    private TeamModel team;
    private TeamController teamController;
    private TreeSeasonInfo info;

    public TeamView(JPanel panel, TeamModel team, TreeSeasonInfo info, TreeGames games){
        this.panel = panel;
        this.team = team;
        this.teamController = new TeamController(team, games);
        this.info = info;

        panel.removeAll();

        JPanel generalInfos = new JPanel();
        JPanel otherStats = new JPanel();

        generalInfos.setBounds(0,0, panel.getWidth(), panel.getHeight()/4 + 5);
        otherStats.setBounds(0, generalInfos.getHeight(), panel.getWidth(), 3*panel.getHeight()/4 - 5);
        generalInfos.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        generalInfos.setLayout(new GridBagLayout());
        otherStats.setLayout(new GridBagLayout());

        fillGeneralInfos(generalInfos);
        fillPlayers(otherStats);

        panel.add(generalInfos);
        panel.add(otherStats);
    }

    private void fillGeneralInfos(JPanel generalInfos){
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 4, 1, 1, 1,
                GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 10);
        JLabel logoName = new JLabel(team.getName(),
                new ImageIcon("src/main/resources/Icons/jersey.png"),
                JLabel.CENTER);
        logoName.setFont(new Font(logoName.getFont().getName(), Font.BOLD, 30));
        logoName.setVerticalTextPosition(JLabel.BOTTOM);
        logoName.setHorizontalTextPosition(JLabel.CENTER);
        generalInfos.add(logoName, constraints);

        // titles
        JLabel headCoachTitle = new JLabel("Head Coach", JLabel.CENTER);
        headCoachTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));
        JLabel resultTitle = new JLabel("Results", JLabel.CENTER);
        resultTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));
        JLabel rankingTitle = new JLabel("Ranking", JLabel.CENTER);
        rankingTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));
        JLabel conferenceTitle = new JLabel("Conference", JLabel.CENTER);
        conferenceTitle.setFont(new Font(headCoachTitle.getFont().getName(), Font.BOLD, 20));

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.33;
        constraints.ipady = 0;
        generalInfos.add(headCoachTitle, constraints);
        ++constraints.gridx;
        constraints.weightx = 0.22;
        generalInfos.add(resultTitle, constraints);
        ++constraints.gridx;
        generalInfos.add(rankingTitle, constraints);
        ++constraints.gridx;
        generalInfos.add(conferenceTitle, constraints);

        // titles
        JLabel headCoachName = new JLabel(team.getHeadCoach(), JLabel.CENTER);
        JLabel results = new JLabel(teamController.calculateTeamResults(), JLabel.CENTER);
        JLabel ranking = new JLabel("Ranking", JLabel.CENTER);
        Conference conf = new Conference("Void Conference");
        try {
            conf = info.getConferenceOfATeam(team);
        } catch (Exception e){
            e.printStackTrace();
        }
        JLabel conference = new JLabel(conf.getName(), JLabel.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.33;
        constraints.ipady = 0;
        generalInfos.add(headCoachName, constraints);
        ++constraints.gridx;
        constraints.weightx = 0.22;
        generalInfos.add(results, constraints);
        ++constraints.gridx;
        generalInfos.add(ranking, constraints);
        ++constraints.gridx;
        generalInfos.add(conference, constraints);
    }

    private void fillPlayers(JPanel otherStats){
        List<PlayerModel> players = info.getPlayersByTeam(team);
        Object[][] data = new Object[players.size()][3];
        for (int i = 0; i < players.size(); ++i){
            PlayerModel player = players.get(i);
            JButton button = new JButton(player.getName() + " " + player.getSurname());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println(player.getName());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(Color.BLUE);
                    Font font = button.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    button.setFont(font.deriveFont(attributes));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(Color.BLACK);
                    Font font = button.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    button.setFont(font.deriveFont(attributes));
                }
            });
            button.setBackground(Color.white);
            data[i][0] = button;
            data[i][1] = player.getNumber();
            data[i][2] = player.getPosition();
        }
        String[] columnsName = {"Players", "#", "Pos"};
        JTable playersTable = new JTable (new DefaultTableModel(data, columnsName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        playersTable.getColumn("Players").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton button = (JButton) value;
                return button;
            }
        });

        playersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playersTable.getRowHeight(); //get the row of the button

                /*Checking the row or column is valid or not*/
                if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                    Object value = playersTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        /*perform a click event*/
                        ((JButton)value).doClick();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int column = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playersTable.getRowHeight(); //get the row of the button

                if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                    Object value = playersTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mousePressed(e);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int column = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playersTable.getRowHeight(); //get the row of the button

                if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                    Object value = playersTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mouseReleased(e);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int column = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playersTable.getRowHeight(); //get the row of the button

                if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                    Object value = playersTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mouseEntered(e);
                        SwingUtilities.updateComponentTreeUI(playersTable);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                int column = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playersTable.getRowHeight(); //get the row of the button
                if (row <= playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= -1) {
                    Object value;
                    if (row == playersTable.getRowCount())
                        value = playersTable.getValueAt(row - 1, column);
                    else if (column == -1)
                        value = playersTable.getValueAt(row, column + 1);
                    else
                        value = playersTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mouseExited(e);
                        SwingUtilities.updateComponentTreeUI(playersTable);
                    }
                }
            }
        });
        playersTable.addMouseMotionListener(new MouseAdapter() {
            int column;
            int row;

            @Override
            public void mouseMoved(MouseEvent e) {
                int c = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the colomn of the button
                int r    = e.getY()/playersTable.getRowHeight(); //get the row of the button
                if (c != column || r != row){
                    if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                        Object value = playersTable.getValueAt(row, column);
                        if (value instanceof JButton) {
                            JButton button = ((JButton)value);
                            button.getMouseListeners()[1].mouseExited(e);
                            SwingUtilities.updateComponentTreeUI(playersTable);
                        }
                    }
                    row = r;
                    column = c;
                    if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                        Object value = playersTable.getValueAt(r, c);
                        if (value instanceof JButton) {
                            JButton button = ((JButton)value);
                            button.getMouseListeners()[1].mouseEntered(e);
                            SwingUtilities.updateComponentTreeUI(playersTable);
                        }
                    }
                }
            }


        });

        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);

        otherStats.add(playersTable, constraints);
    }
}

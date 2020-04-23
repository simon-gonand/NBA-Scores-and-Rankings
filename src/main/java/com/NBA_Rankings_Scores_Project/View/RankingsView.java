package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

public class RankingsView {
    private JPanel panel;
    private TreeSeasonInfo info;
    private TreeGames games;

    public RankingsView(JPanel panel){
        this.panel = panel;
        panel.removeAll();

        Parser parser = new Parser("src/main/resources/Season_19_20.xml");
        this.info = parser.getTreeSeason();
        this.games = parser.getTreeSeasonGames(this.info);

        JPanel rankingPanel = new JPanel(new GridBagLayout());
        rankingPanel.setBounds(0,0, this.panel.getWidth(), this.panel.getHeight());
        this.panel.add(rankingPanel);

        JLabel title = new JLabel("Rankings", JLabel.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
        GridBagConstraints constraints = new GridBagConstraints(0,0,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0);
        rankingPanel.add(title, constraints);

        JComboBox conferenceChoice = new JComboBox();
        conferenceChoice.addItem("West");
        conferenceChoice.addItem("East");
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.NONE;
        rankingPanel.add(conferenceChoice, constraints);
    }

    private void fillRankingPanel(JPanel rankingPanel, ArrayList<TeamModel> sortTeamRankin){
        Object[][] data = new Object[sortTeamRankin.size()][3];
        for (int i = 0; i < sortTeamRankin.size(); ++i){
            TeamModel team = sortTeamRankin.get(i);
            JButton button = new JButton(team.getName());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    button.setForeground(button.getForeground().darker());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    button.setForeground(button.getForeground().brighter());
                    new TeamView(panel, team, info, games);
                    SwingUtilities.updateComponentTreeUI(panel);
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
            data[i][1] = team.getHeadCoach();
            try {
                data[i][2] = info.getConferenceOfATeam(team).getName();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        String[] columnsName = {"Team", "Head Coach", "Conference"};
        JTable rankingTable = new JTable (new DefaultTableModel(data, columnsName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        rankingTable.getColumn("Team").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton button = (JButton) value;
                return button;
            }
        });

        rankingTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int column = rankingTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/rankingTable.getRowHeight(); //get the row of the button

                if (row < rankingTable.getRowCount() && row >= 0 && column < rankingTable.getColumnCount() && column >= 0) {
                    Object value = rankingTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mousePressed(e);
                        SwingUtilities.updateComponentTreeUI(rankingTable);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int column = rankingTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/rankingTable.getRowHeight(); //get the row of the button

                if (row < rankingTable.getRowCount() && row >= 0 && column < rankingTable.getColumnCount() && column >= 0) {
                    Object value = rankingTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mouseReleased(e);
                        SwingUtilities.updateComponentTreeUI(rankingTable);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int column = rankingTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/rankingTable.getRowHeight(); //get the row of the button

                if (row < rankingTable.getRowCount() && row >= 0 && column < rankingTable.getColumnCount() && column >= 0) {
                    Object value = rankingTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mouseEntered(e);
                        SwingUtilities.updateComponentTreeUI(rankingTable);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                int column = rankingTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/rankingTable.getRowHeight(); //get the row of the button
                if (row <= rankingTable.getRowCount() && row >= 0 && column < rankingTable.getColumnCount() && column >= -1) {
                    Object value;
                    if (row == rankingTable.getRowCount())
                        row = row -1;

                    if (column == -1)
                        column = column +1;

                    value = rankingTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mouseExited(e);
                        SwingUtilities.updateComponentTreeUI(rankingTable);
                    }
                }
            }
        });
        rankingTable.addMouseMotionListener(new MouseAdapter() {
            int column;
            int row;

            @Override
            public void mouseMoved(MouseEvent e) {
                int c = rankingTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the colomn of the button
                int r    = e.getY()/rankingTable.getRowHeight(); //get the row of the button
                if (c != column || r != row){
                    if (row < rankingTable.getRowCount() && row >= 0 && column < rankingTable.getColumnCount() && column >= 0) {
                        Object value = rankingTable.getValueAt(row, column);
                        if (value instanceof JButton) {
                            JButton button = ((JButton)value);
                            button.getMouseListeners()[1].mouseExited(e);
                            SwingUtilities.updateComponentTreeUI(rankingTable);
                        }
                    }
                    row = r;
                    column = c;
                    if (row < rankingTable.getRowCount() && row >= 0 && column < rankingTable.getColumnCount() && column >= 0) {
                        Object value = rankingTable.getValueAt(r, c);
                        if (value instanceof JButton) {
                            JButton button = ((JButton)value);
                            button.getMouseListeners()[1].mouseEntered(e);
                            SwingUtilities.updateComponentTreeUI(rankingTable);
                        }
                    }
                }
            }
        });

        rankingTable.setBackground(new Color(238,238,238));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        rankingTable.setDefaultRenderer(Object.class, centerRenderer);
        rankingTable.setRowHeight(30);
        rankingTable.getColumnModel().getColumn(0).setMaxWidth(300);
        rankingTable.getColumnModel().getColumn(1).setMaxWidth(200);
        rankingTable.getColumnModel().getColumn(2).setMaxWidth(200);

        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(25,60,0,0), 0, 0);

        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        rankingPanel.add(scrollPane, constraints);
    }
}
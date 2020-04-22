package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.PlayerController;
import com.NBA_Rankings_Scores_Project.Controllers.ResearchControllers;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResearchView {
    private JPanel panel;
    private TreeSeasonInfo info;
    private TreeGames games;
    private ResearchControllers controllers;

    public ResearchView(JPanel panel){
        this.panel = panel;
        Parser parser = new Parser("src/main/resources/Season_19_20.xml");
        this.info = parser.getTreeSeason();
        this.games = parser.getTreeSeasonGames(info);
        this.controllers = new ResearchControllers(this.info);
        panel.removeAll();

        fillPlayerResearch();
    }

    private void fillPlayerResearch(){
        JPanel researchPanel = new JPanel(new GridBagLayout());
        researchPanel.setBounds(0,this.panel.getHeight()/3, this.panel.getWidth() - 120, this.panel.getHeight()/3);
        this.panel.add(researchPanel);

        JLabel title = new JLabel("Search ", new ImageIcon("src/main/resources/Icons/research.png"), JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.LEFT);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
        GridBagConstraints constraints = new GridBagConstraints(1, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,250,0,0), 0, 0);
        researchPanel.add(title, constraints);

        JButton submit = new JButton("Search");
        constraints.gridy = 3;
        researchPanel.add(submit, constraints);

        JLabel name = new JLabel("Name",JLabel.CENTER);
        JLabel post = new JLabel("Post", JLabel.CENTER);
        JLabel team = new JLabel("Teams", JLabel.CENTER);
        JLabel nationality = new JLabel("Nationality", JLabel.CENTER);

        TextField nameTextField = new TextField("");
        nameTextField.setColumns(20);
        TextField teamTextField = new TextField("");
        teamTextField.setColumns(20);
        TextField nationalityTextField = new TextField("");
        nationalityTextField.setColumns(20);

        JComboBox postComboBox = new JComboBox();
        postComboBox.addItem("NONE");
        postComboBox.addItem(PlayerModel.Position.GUARD.toString());
        postComboBox.addItem(PlayerModel.Position.SHOOTING_GUARD.toString());
        postComboBox.addItem(PlayerModel.Position.SMALL_FORWARD.toString());
        postComboBox.addItem(PlayerModel.Position.POWER_FORWARD.toString());
        postComboBox.addItem(PlayerModel.Position.CENTER.toString());

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets.left = 0;
        constraints.weightx = 0.25;
        researchPanel.add(name, constraints);
        constraints.gridx = 1;
        constraints.weightx = 0.75;
        researchPanel.add(nameTextField, constraints);
        constraints.gridx = 2;
        constraints.weightx = 0.25;
        researchPanel.add(post, constraints);
        constraints.gridx = 3;
        constraints.weightx = 0.75;
        researchPanel.add(postComboBox, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 0.25;
        researchPanel.add(team, constraints);
        constraints.gridx = 1;
        constraints.weightx = 0.75;
        researchPanel.add(teamTextField, constraints);
        constraints.gridx = 2;
        constraints.weightx = 0.25;
        researchPanel.add(nationality, constraints);
        constraints.gridx = 3;
        constraints.weightx = 0.75;
        researchPanel.add(nationalityTextField, constraints);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fillResultPanel(controllers.doPlayerSearch(nameTextField.getText(), postComboBox.getSelectedItem().toString(),
                        teamTextField.getText(), nationalityTextField.getText()));
                SwingUtilities.updateComponentTreeUI(panel);
            }
        });
    }

    private void fillResultPanel(ArrayList<PlayerModel> results){
        this.panel.removeAll();
        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBounds(0,40, this.panel.getWidth(), this.panel.getHeight());
        this.panel.add(resultPanel);
        JButton returnToSearch = new JButton("Return", new ImageIcon("src/main/resources/Icons/back.png"));
        returnToSearch.setBounds(0,0, 160,40);
        returnToSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                fillPlayerResearch();
                SwingUtilities.updateComponentTreeUI(panel);
            }
        });
        this.panel.add(returnToSearch);
        if (results.isEmpty()){
            fillEmptyResultPanel(resultPanel);
        }
        else {
            fillPlayerResultTable(resultPanel, results);
        }
    }

    private void fillEmptyResultPanel(JPanel resultsPanel){
        JLabel label = new JLabel("No Results", JLabel.CENTER);
        GridBagConstraints constraints = new GridBagConstraints(0,0,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0);
        resultsPanel.add(label, constraints);
    }

    private void fillPlayerResultTable(JPanel resultPanel, List<PlayerModel> results){
        Object[][] data = new Object[results.size()][3];
        for (int i = 0; i < results.size(); ++i){
            PlayerModel player = results.get(i);
            JButton button = new JButton(player.getName() + " " + player.getSurname());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    button.setForeground(button.getForeground().darker());

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    button.setForeground(button.getForeground().brighter());
                    new PlayerView(panel, player, info, games);
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
            data[i][1] = Integer.valueOf(player.getNumber());
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
            public void mousePressed(MouseEvent e) {
                int column = playersTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playersTable.getRowHeight(); //get the row of the button

                if (row < playersTable.getRowCount() && row >= 0 && column < playersTable.getColumnCount() && column >= 0) {
                    Object value = playersTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        JButton button = ((JButton)value);
                        button.getMouseListeners()[1].mousePressed(e);
                        SwingUtilities.updateComponentTreeUI(playersTable);
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
                        SwingUtilities.updateComponentTreeUI(playersTable);
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
                        row = row -1;

                    if (column == -1)
                        column = column +1;

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

        playersTable.setBackground(new Color(238,238,238));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        playersTable.setDefaultRenderer(Object.class, centerRenderer);
        playersTable.setRowHeight(30);
        playersTable.getColumnModel().getColumn(0).setMaxWidth(200);
        playersTable.getColumnModel().getColumn(1).setMaxWidth(35);
        playersTable.getColumnModel().getColumn(2).setMaxWidth(200);

        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(25,60,0,0), 0, 0);

        JScrollPane scrollPane = new JScrollPane(playersTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        resultPanel.add(scrollPane, constraints);
    }
}

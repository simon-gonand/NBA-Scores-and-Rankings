package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Controllers.PlayerController;
import com.NBA_Rankings_Scores_Project.Controllers.ResearchControllers;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResearchView {
    private JPanel panel;
    private TreeSeasonInfo info;
    private ResearchControllers controllers;

    public ResearchView(JPanel panel){
        this.panel = panel;
        Parser parser = new Parser("src/main/resources/Season_19_20.xml");
        this.info = parser.getTreeSeason();
        this.controllers = new ResearchControllers(this.info);
        panel.removeAll();

        fillPlayerResearch();
    }

    private void fillPlayerResearch(){
        JPanel researchPanel = new JPanel(new GridBagLayout());
        researchPanel.setBounds(100,this.panel.getHeight()/3, this.panel.getWidth() - 120, this.panel.getHeight()/3);
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
            }
        });
    }

    public void fillResultPanel(ArrayList<PlayerModel> results){

    }
}

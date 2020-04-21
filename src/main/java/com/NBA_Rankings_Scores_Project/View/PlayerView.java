package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.PlayerModel;

import javax.swing.*;
import java.awt.*;

public class PlayerView {
    private JPanel panel;
    PlayerModel player;

    public PlayerView(JPanel panel, PlayerModel player){
        this.panel = panel;
        this.player = player;
        panel.removeAll();

        JPanel generalInfos = new JPanel(new GridBagLayout());
        JPanel otherStats = new JPanel(new GridBagLayout());

        generalInfos.setBounds(0,0, panel.getWidth(), panel.getHeight() / 4);
        otherStats.setBounds(0,0, panel.getWidth(), 3*panel.getHeight() / 4);
        generalInfos.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.black));

        panel.add(generalInfos);
        panel.add(otherStats);
    }
}

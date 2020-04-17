package com.NBA_Rankings_Scores_Project.View;

import com.NBA_Rankings_Scores_Project.Models.GameModel;

import javax.swing.*;

public class GameView {
    private JPanel panel;
    private GameModel game;

    public GameView(JPanel panel, GameModel game){
        this.panel = panel;
        this.game = game;
    }
}

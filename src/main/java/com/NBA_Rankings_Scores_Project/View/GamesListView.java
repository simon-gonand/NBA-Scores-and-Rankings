package com.NBA_Rankings_Scores_Project.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamesListView{
    private JPanel panel;

    public GamesListView (JPanel panel){
        this.panel = panel;

        final JButton button = new JButton("click");
        button.setBounds(panel.getWidth()/2, panel.getHeight()/2, 100, 40);

        this.panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button actionned");
            }
        });
    }
}

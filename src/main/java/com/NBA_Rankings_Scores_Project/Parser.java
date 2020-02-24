package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Models.*;
import com.NBA_Rankings_Scores_Project.Tree.Tree;
import com.NBA_Rankings_Scores_Project.Tree.TreeNode;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Parser {
    public Parser (){
        try {
            File inputFile = new File ("src/Datas/Season_19_20.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Season season = new Season(doc.getDocumentElement().getAttribute("name"));
            Tree tree = new Tree(season);
            NodeList confList = doc.getElementsByTagName("Conference");

            for (int i = 0; i < confList.getLength(); ++i){
                Node confNode = confList.item(i);
                if (confNode.getNodeType() == Node.ELEMENT_NODE){
                    Element confElement = (Element) confNode;
                    TreeNode conference = tree.add(tree.getRoot(), new Conference(confElement.getAttribute("name")));

                    NodeList teamsList = confElement.getElementsByTagName("Team");
                    for (int j = 0; j < confList.getLength(); ++j){
                        Node teamNode = teamsList.item(j);
                        if (teamNode.getNodeType() == Node.ELEMENT_NODE){
                            Element teamElement = (Element) teamNode;
                            TreeNode team = tree.add(conference, new TeamModel(teamElement.getAttribute("name")));

                            NodeList playersList = teamElement.getElementsByTagName("Player");
                            for (int k = 0; k < playersList.getLength(); ++k){
                                Node playerNode = playersList.item(k);
                                if (playerNode.getNodeType() == Node.ELEMENT_NODE){
                                    Element playerElement = (Element) playerNode;
                                    TreeNode player = tree.add(team, new PlayerModel(playerElement.getAttribute("name")));
                                }
                            }
                        }
                    }
                }
            }
           tree.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

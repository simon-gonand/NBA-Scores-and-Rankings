package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Models.*;
import com.NBA_Rankings_Scores_Project.Tree.Tree;
import com.NBA_Rankings_Scores_Project.Tree.TreeNode;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Parser {
    public File inputFile;
    public Parser (String fileName){
        inputFile = new File (fileName);
    }

    public Tree getTree() {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Season season = new Season(doc.getDocumentElement().getAttribute("name"));
            Tree tree = new Tree(season);

            tree = getConferences(doc, tree);

            return tree;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Tree(new Season("void season"));
    }

    private Tree getConferences(Document doc, Tree tree){
        NodeList confList = doc.getElementsByTagName("Conference");

        for (int i = 0; i < confList.getLength(); ++i) {
            Node confNode = confList.item(i);
            if (confNode.getNodeType() == Node.ELEMENT_NODE) {
                Element confElement = (Element) confNode;
                TreeNode conference = tree.add(tree.getRoot(), new Conference(confElement.getAttribute("name")));
                tree = getTeams(conference, confElement, tree);
            }
        }
        return tree;
    }

    private Tree getTeams (TreeNode conference, Element confElement, Tree tree){
        NodeList teamsList = confElement.getElementsByTagName("Team");
        for (int j = 0; j < teamsList.getLength(); ++j) {
            Node teamNode = teamsList.item(j);
            if (teamNode.getNodeType() == Node.ELEMENT_NODE) {
                Element teamElement = (Element) teamNode;
                TreeNode team = tree.add(conference, new TeamModel(teamElement.getAttribute("name")));
                tree = getPlayers(team, teamElement, tree);
            }
        }
        return tree;
    }

    private Tree getPlayers (TreeNode team, Element teamElement, Tree tree){
        NodeList playersList = teamElement.getElementsByTagName("Player");
        for (int k = 0; k < playersList.getLength(); ++k){
            Node playerNode = playersList.item(k);
            if (playerNode.getNodeType() == Node.ELEMENT_NODE){
                Element playerElement = (Element) playerNode;
                TreeNode player = tree.add(team, new PlayerModel(playerElement.getAttribute("name")));
            }
        }
        return tree;
    }
}

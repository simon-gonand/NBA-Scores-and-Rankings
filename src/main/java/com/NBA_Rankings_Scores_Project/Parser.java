package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Models.*;
import com.NBA_Rankings_Scores_Project.Tree.Tree;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeNode;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

public class Parser {
    public File inputFile;
    public Parser (String fileName){
        inputFile = new File (fileName);
    }

    public TreeSeasonInfo getTreeSeason() {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Season season = new Season(doc.getDocumentElement().getAttribute("name"));
            TreeSeasonInfo tree = new TreeSeasonInfo(season);

            tree = getConferences(doc, tree);

            return tree;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TreeSeasonInfo(new Season("void season"));
    }

    private TreeSeasonInfo getConferences(Document doc, TreeSeasonInfo tree) throws Exception {
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

    private TreeSeasonInfo getTeams (TreeNode conference, Element confElement, TreeSeasonInfo tree) throws Exception {
        NodeList teamsList = confElement.getElementsByTagName("Team");
        for (int j = 0; j < teamsList.getLength(); ++j) {
            Node teamNode = teamsList.item(j);
            if (teamNode.getNodeType() == Node.ELEMENT_NODE) {
                Element teamElement = (Element) teamNode;
                TreeNode team = tree.add(conference, new TeamModel(
                        teamElement.getAttribute("name"), teamElement.getAttribute("headCoach")));
                tree = getPlayers(team, teamElement, tree);
            }
        }
        return tree;
    }

    private PlayerModel.Position getPositionEnum(String positionName) throws Exception {
        if (positionName.equals("Guard"))
            return PlayerModel.Position.GUARD;
        else if (positionName.equals("Shooting Guard"))
            return PlayerModel.Position.SHOOTING_GUARD;
        else if (positionName.equals("Small Forward"))
            return PlayerModel.Position.SMALL_FORWARD;
        else if (positionName.equals("Power Forward"))
            return PlayerModel.Position.POWER_FORWARD;
        else if (positionName.equals("Center"))
            return PlayerModel.Position.CENTER;
        else
            throw new Exception("Unknown Position");
    }

    private TreeSeasonInfo getPlayers (TreeNode team, Element teamElement, TreeSeasonInfo tree) throws Exception {
        NodeList playersList = teamElement.getElementsByTagName("Player");
        for (int k = 0; k < playersList.getLength(); ++k){
            Node playerNode = playersList.item(k);
            if (playerNode.getNodeType() == Node.ELEMENT_NODE){
                Element playerElement = (Element) playerNode;
                try {
                    TreeNode player = tree.add(team, new PlayerModel(playerElement.getAttribute("name"),
                            playerElement.getAttribute("surname"),
                            getPositionEnum(playerElement.getAttribute("position")),
                            Integer.parseInt(playerElement.getAttribute("number")),
                            Integer.parseInt(playerElement.getAttribute("age")),
                            playerElement.getAttribute("nationality"),
                            Integer.parseInt(playerElement.getAttribute("indice"))));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return tree;
    }

    public TreeGames getTreeSeasonGames (TreeSeasonInfo treeSeason){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Season season = new Season(doc.getDocumentElement().getAttribute("name"));
            TreeGames tree = new TreeGames(season);

            tree = this.getTreeGames(doc, tree, treeSeason);

            return tree;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new TreeGames(new Season("void season"));
    }

    private TreeGames getTreeGames(Document doc, TreeGames tree, TreeSeasonInfo treeSeason) throws Exception {
        NodeList gamesList = doc.getElementsByTagName("Game");
        for (int i = 0; i < gamesList.getLength(); ++i){
            Node gameNode = gamesList.item(i);
            if (gameNode.getNodeType() == Node.ELEMENT_NODE){
                Element gameElement = (Element) gameNode;
                TreeNode gameTreeNode = tree.add(tree.getRoot(), new GameModel(gameElement.getAttribute("name"),
                        gameElement.getAttribute("date"),
                        gameElement.getAttribute("Winner"),
                        gameElement.getAttribute("totScore"),
                        gameElement.getAttribute("q1Score"),
                        gameElement.getAttribute("q2Score"),
                        gameElement.getAttribute("q3Score"),
                        gameElement.getAttribute("q4Score")));
                try {
                    getGameTeams(gameElement, gameTreeNode, tree, treeSeason);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return tree;
    }

    private TreeGames getGameTeams(Element gameElement, TreeNode gameNode, TreeGames tree, TreeSeasonInfo treeSeason) throws Exception {
        NodeList home = gameElement.getElementsByTagName("Home");
        TreeNode homeNode = null;
        if (home.item(0).getNodeType() == Node.ELEMENT_NODE) {
            Element homeElement = (Element) home.item(0);
            boolean teamFound = false;
            for (TeamModel teamModel : treeSeason.getTeams()){
                if (teamModel.getName().equals(homeElement.getAttribute("name"))) {
                    homeNode = tree.add(gameNode, teamModel);
                    teamFound = true;
                }
            }
            if (!teamFound) throw new Exception("Unknown Team");
            tree = this.getTreePlayersStats(homeElement, homeNode, tree);
        }

        NodeList visitor = gameElement.getElementsByTagName("Visitor");
        TreeNode visitorNode = null;
        if(visitor.item(0).getNodeType() == Node.ELEMENT_NODE){
            Element visitorElement = (Element) visitor.item(0);
            boolean teamFound = false;
            for (TeamModel teamModel : treeSeason.getTeams()) {
                if (teamModel.getName().equals(visitorElement.getAttribute("name"))) {
                    visitorNode = tree.add(gameNode, teamModel);
                    teamFound = true;
                }
            }
            if (!teamFound) throw new Exception("Unknown Team");
            tree = this.getTreePlayersStats(visitorElement, visitorNode, tree);
        }
        return tree;
    }

    private TreeGames getTreePlayersStats (Element teamElement, TreeNode teamNode, TreeGames tree){
        if (teamNode.equals(null)) throw new NullPointerException("Unknown team node");
        NodeList playersStatsList = teamElement.getElementsByTagName("PlayerStat");
        for (int i = 0; i < playersStatsList.getLength(); ++i){
            Node playerStatsNode = playersStatsList.item(i);
            if (playerStatsNode.getNodeType() == Element.ELEMENT_NODE){
                Element playerStatElement = (Element) playerStatsNode;
                if (playerStatElement.getAttribute("min").equals("0"))
                    tree.add(teamNode, new PlayerStats(Integer.parseInt(playerStatElement.getAttribute("indice")),
                            Integer.parseInt(playerStatElement.getAttribute("min"))));
                else
                    tree.add(teamNode, new PlayerStats(Integer.parseInt(playerStatElement.getAttribute("indice")),
                            Integer.parseInt(playerStatElement.getAttribute("min")),
                            Integer.parseInt(playerStatElement.getAttribute("points")),
                            Integer.parseInt(playerStatElement.getAttribute("rebounds")),
                            Integer.parseInt(playerStatElement.getAttribute("assists")),
                            Integer.parseInt(playerStatElement.getAttribute("steals")),
                            Integer.parseInt(playerStatElement.getAttribute("blocks")),
                            Integer.parseInt(playerStatElement.getAttribute("turnovers")),
                            playerStatElement.getAttribute("fg"),
                            playerStatElement.getAttribute("threePt"),
                            playerStatElement.getAttribute("ft")));
            }
        }

        return tree;
    }
}

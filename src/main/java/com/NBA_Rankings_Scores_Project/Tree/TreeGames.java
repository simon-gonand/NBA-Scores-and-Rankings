package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Models.Season;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeGames extends Tree {
    public TreeGames(Season season){
        super(season);
    }

    public ArrayList<GameModel> getAllGames(){
        ArrayList<GameModel> games = new ArrayList<GameModel>();
        for (TreeNode gameNode : this.getRoot().getChilds()){
            if(gameNode.getData().getClass().equals(GameModel.class))
                games.add((GameModel) gameNode.getData());
        }

        return games;
    }

    public HashMap<String, TeamModel> getTeamsOfGame(GameModel game){
        HashMap<String, TeamModel> teams = new HashMap<String, TeamModel>();
        for (TreeNode gameNode : this.getRoot().getChilds()){
            if (gameNode.getData().getClass().equals(GameModel.class)
                    && gameNode.getData().getName().equals(game.getName())) {
                GameModel gameModel = (GameModel) gameNode.getData();
                if (gameModel.getDate().equals(game.getDate())) {
                    if (gameNode.getChilds().get(0).getData().getClass().equals(TeamModel.class))
                        teams.put("Home", (TeamModel) gameNode.getChilds().get(0).getData());
                    if (gameNode.getChilds().get(1).getData().getClass().equals(TeamModel.class))
                        teams.put("Visitor", (TeamModel) gameNode.getChilds().get(1).getData());
                }
            }
        }

        return teams;
    }

    public ArrayList<PlayerStats> getPlayerStatsByTeam (GameModel game, TeamModel team){
        ArrayList<PlayerStats> playersStats = new ArrayList<PlayerStats>();
        for (TreeNode gameNode : this.getRoot().getChilds()){
            if (gameNode.getData().getClass().equals(GameModel.class)
                    && gameNode.getData().getName().equals(game.getName())) {
                GameModel gameModel = (GameModel) gameNode.getData();
                if (gameModel.getDate().equals(game.getDate())) {
                    for (TreeNode teamNode : gameNode.getChilds()){
                        if (teamNode.getData().getName().equals(team.getName())){
                            for (TreeNode playerStatsNode : teamNode.getChilds()){
                                if (playerStatsNode.getData().getClass().equals(PlayerStats.class))
                                    playersStats.add((PlayerStats) playerStatsNode.getData());
                            }
                        }
                    }
                }
            }
        }

        return playersStats;
    }
}

package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Models.Season;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tree with all the games and the statistics
 * @see Tree
 */
public class TreeGames extends Tree {
    /**
     * Constructor which call the constructor of Tree
     * @param season Season which will be the Tree root
     * @see Tree
     * @see Season
     */
    public TreeGames(Season season){
        super(season);
    }

    /**
     * To get all games of the season
     * @return ArrayList with all the games of the season
     * @see TreeNode
     * @see GameModel
     */
    public ArrayList<GameModel> getAllGames(){
        ArrayList<GameModel> games = new ArrayList<GameModel>();
        for (TreeNode gameNode : this.getRoot().getChildren()){
            if(gameNode.getData().getClass().equals(GameModel.class))
                games.add((GameModel) gameNode.getData());
        }
        return games;
    }

    /**
     * To get the teams of one game
     * @param game GameModel of the game which has the teams as children
     * @return HashMap with the teams of the game
     * @see TreeNode
     * @see GameModel
     * @see TeamModel
     */
    public HashMap<String, TeamModel> getTeamsOfGame(GameModel game){
        HashMap<String, TeamModel> teams = new HashMap<String, TeamModel>();
        for (TreeNode gameNode : this.getRoot().getChildren()){
            if (gameNode.getData().getClass().equals(GameModel.class)
                    && gameNode.getData().getName().equals(game.getName())) {
                GameModel gameModel = (GameModel) gameNode.getData();
                if (gameModel.getDate().equals(game.getDate())) {
                    if (gameNode.getChildren().get(0).getData().getClass().equals(TeamModel.class))
                        teams.put("Home", (TeamModel) gameNode.getChildren().get(0).getData());
                    if (gameNode.getChildren().get(1).getData().getClass().equals(TeamModel.class))
                        teams.put("Visitor", (TeamModel) gameNode.getChildren().get(1).getData());
                }
            }
        }
        return teams;
    }

    /**
     * To get the players statistics of a team in a game
     * @param game GameModel of the game which the players played into
     * @param team TeamModel of the team which has the statistics as children
     * @return ArrayList with the player stats
     * @see TreeNode
     * @see GameModel
     * @see TeamModel
     * @see PlayerStats
     */
    public ArrayList<PlayerStats> getPlayerStatsByTeam (GameModel game, TeamModel team){
        ArrayList<PlayerStats> playersStats = new ArrayList<PlayerStats>();
        for (TreeNode gameNode : this.getRoot().getChildren()){
            if (gameNode.getData().getClass().equals(GameModel.class)
                    && gameNode.getData().getName().equals(game.getName())) {
                GameModel gameModel = (GameModel) gameNode.getData();
                if (gameModel.getDate().equals(game.getDate())) {
                    for (TreeNode teamNode : gameNode.getChildren()){
                        if (teamNode.getData().getName().equals(team.getName())){
                            for (TreeNode playerStatsNode : teamNode.getChildren()){
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

    /**
     * To get all the games where a played
     * @param team TeamModel of the team which played those games
     * @return ArrayList of the games where the team played
     * @see TreeNode
     * @see TeamModel
     * @see GameModel
     */
    public ArrayList<GameModel> getAllGamesOfTeam(TeamModel team){
        ArrayList<GameModel> games = new ArrayList<GameModel>();
        for (TreeNode gameNode : this.getRoot().getChildren()){
            if(gameNode.getData().getClass().equals(GameModel.class)) {
                HashMap<String, TeamModel> teams = getTeamsOfGame((GameModel) gameNode.getData());
                if (teams.get("Home").equals(team) || teams.get("Visitor").equals(team))
                    games.add((GameModel) gameNode.getData());
            }
        }
        return games;
    }
}

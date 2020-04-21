package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import java.util.HashMap;
import java.util.Map;

public class PlayerController {
    private PlayerModel player;
    private TreeSeasonInfo info;
    private TreeGames games;

    public PlayerController(PlayerModel player, TreeSeasonInfo info, TreeGames games){
        this.player = player;
        this.info = info;
        this.games = games;
    }

    public Map<String, String> calculatePlayerSeasonStats(){
        Map<String, String> playerStats = new HashMap<String, String>();
        Map<String, Float> playerTotals = calculateTotals();
        playerStats.put("GamesPlayed", playerTotals.get("GamesPlayed").toString());
        String[] pointsAverage = Float.toString(playerTotals.get("Points") / playerTotals.get("GamesPlayed")).split("\\.");
        String[] reboundsAverage = Float.toString(playerTotals.get("Rebounds") / playerTotals.get("GamesPlayed")).split("\\.");
        String[] assistsAverage = Float.toString(playerTotals.get("Rebounds") / playerTotals.get("GamesPlayed")).split("\\.");
        String[] FGAverage = Float.toString(playerTotals.get("FGMade") * 100 / playerTotals.get("FGAttempts")).split("\\.");
        String[] FTAverage = Float.toString(playerTotals.get("FTMade") * 100 / playerTotals.get("FTAttempts")).split("\\.");
        String[] threePtAverage = Float.toString(playerTotals.get("3ptMade") * 100 / playerTotals.get("3ptAttempts")).split("\\.");
        String[] blocksAverage = Float.toString(playerTotals.get("Blocks") / playerTotals.get("GamesPlayed")).split("\\.");
        String[] stealsAverage = Float.toString(playerTotals.get("Steals") / playerTotals.get("GamesPlayed")).split("\\.");
        String[] turnoversAverage = Float.toString(playerTotals.get("Turnovers") / playerTotals.get("GamesPlayed")).split("\\.");

        playerStats.put("Points", pointsAverage[1].length() < 2 ?
                pointsAverage[0]+"."+pointsAverage[1].substring(0,pointsAverage[1].length()) :
                pointsAverage[0]+"."+pointsAverage[1].substring(0,1));
        playerStats.put("Rebounds", reboundsAverage[1].length() < 2 ?
                reboundsAverage[0]+"."+reboundsAverage[1].substring(0,reboundsAverage[1].length()) :
                reboundsAverage[0]+"."+reboundsAverage[1].substring(0,1));
        playerStats.put("Assists", assistsAverage[1].length() < 2 ?
                assistsAverage[0]+"."+assistsAverage[1].substring(0,assistsAverage[1].length()) :
                assistsAverage[0]+"."+assistsAverage[1].substring(0,1));
        playerStats.put("FG", FGAverage[1].length() < 2 ?
                FGAverage[0]+"."+FGAverage[1].substring(0,FGAverage[1].length()) :
                FGAverage[0]+"."+FGAverage[1].substring(0,1));
        playerStats.put("FT", FTAverage[1].length() < 2 ?
                FTAverage[0]+"."+FTAverage[1].substring(0,FTAverage[1].length()) :
                FTAverage[0]+"."+FTAverage[1].substring(0,1));
        playerStats.put("3pt", threePtAverage[1].length() < 2 ?
                threePtAverage[0]+"."+threePtAverage[1].substring(0,threePtAverage[1].length()) :
                threePtAverage[0]+"."+threePtAverage[1].substring(0,1));
        playerStats.put("Blocks", blocksAverage[1].length() < 2 ?
                blocksAverage[0]+"."+blocksAverage[1].substring(0,blocksAverage[1].length()) :
                blocksAverage[0]+"."+blocksAverage[1].substring(0,1));
        playerStats.put("Steals", stealsAverage[1].length() < 2 ?
                stealsAverage[0]+"."+stealsAverage[1].substring(0,stealsAverage[1].length()) :
                stealsAverage[0]+"."+stealsAverage[1].substring(0,1));
        playerStats.put("Turnovers", turnoversAverage[1].length() < 2 ?
                turnoversAverage[0]+"."+turnoversAverage[1].substring(0,turnoversAverage[1].length()) :
                turnoversAverage[0]+"."+turnoversAverage[1].substring(0,1));
        return playerStats;
    }

    private Map<String, Float> calculateTotals(){
        Map<String, Float> playerStats = new HashMap<String, Float>();
        float gamePlayed = 0;
        float totMinutes = 0.0f, totPoints = 0.0f, totRebounds = 0.0f, totAssists = 0.0f, totFGMade = 0.0f, totFGAttempts = 0.0f,
                totFTMade = 0.0f, totFTAttempts = 0.0f, totThreePtMade = 0.0f, totThreePtAttempts = 0.0f, totSteals = 0.0f,
                totBlocks = 0.0f, totTurnovers = 0.0f;
        try {
            for (GameModel gameModel : games.getAllGamesOfTeam(info.getTeamOfAPlayer(player))){
                PlayerStats stats = games.getPlayerStatsByTeam(gameModel, info.getTeamOfAPlayer(player)).get(player.getID());
                if (!stats.getMinutes().equals(0)){
                    ++gamePlayed;
                    totMinutes += stats.getMinutes();
                    totPoints += stats.getPoints();
                    totRebounds += stats.getRebounds();
                    totAssists += stats.getAssists();
                    totSteals += stats.getSteals();
                    totBlocks += stats.getBlocks();
                    totTurnovers += stats.getTurnovers();
                    String[] fg = stats.getFg().toString().split("/");
                    totFGMade += Float.valueOf(fg[0]);
                    totFGAttempts += Float.valueOf(fg[1]);
                    String[] ft = stats.getFt().toString().split("/");
                    totFTMade += Float.valueOf(ft[0]);
                    totFTAttempts += Float.valueOf(ft[1]);
                    String[] threePT = stats.getThreePts().toString().split("/");
                    totThreePtMade += Float.valueOf(threePT[0]);
                    totThreePtAttempts += Float.valueOf(threePT[1]);
                }
            }
            playerStats.put("GamesPlayed", gamePlayed);
            playerStats.put("Minutes", totMinutes);
            playerStats.put("Points", totPoints);
            playerStats.put("Rebounds", totRebounds);
            playerStats.put("Assists", totAssists);
            playerStats.put("FGMade", totFGMade);
            playerStats.put("FGAttempts", totFGAttempts);
            playerStats.put("FTMade", totFTMade);
            playerStats.put("FTAttempts", totFTAttempts);
            playerStats.put("3ptMade", totThreePtMade);
            playerStats.put("3ptAttempts", totThreePtAttempts);
            playerStats.put("Blocks", totBlocks);
            playerStats.put("Steals", totSteals);
            playerStats.put("Turnovers", totTurnovers);
        } catch (Exception e){
            e.printStackTrace();
        }
        return playerStats;
    }
}

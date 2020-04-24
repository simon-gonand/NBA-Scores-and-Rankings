package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller of a player that makes the calculation in order to display them in a the view
 */
public class PlayerController {
    private PlayerModel player;
    private TreeSeasonInfo info;
    private TreeGames games;

    /**
     * Constructor that initialize the data members
     * @param player PlayerModel of the controller
     * @param info TreeSeasonInfo of the general information of the season
     * @param games TreeGames of the games and the statistics of the season
     * @see PlayerModel
     * @see TreeSeasonInfo
     * @see TreeGames
     */
    public PlayerController(PlayerModel player, TreeSeasonInfo info, TreeGames games){
        this.player = player;
        this.info = info;
        this.games = games;
    }

    /**
     * Calculate the season stats of the player
     * @return Map with all the stats of the player
     * @see PlayerStats
     */
    public Map<String, String> calculatePlayerSeasonStats(){
        Map<String, String> playerStats = new HashMap<String, String>();
        Map<String, Float> playerTotals = calculateTotals();
        // if the player never played
        if (playerTotals.get("Minutes").equals(0.0f)){
            playerStats.put("GamesPlayed", "0");
            playerStats.put("Minutes", "0.0");
            playerStats.put("Points", "0.0");
            playerStats.put("Rebounds", "0.0");
            playerStats.put("Assists", "0.0");
            playerStats.put("FG", "0.0");
            playerStats.put("FT", "0.0");
            playerStats.put("3pt", "0.0");
            playerStats.put("Blocks", "0.0");
            playerStats.put("Steals", "0.0");
            playerStats.put("Turnovers", "0.0");
            return playerStats;
        }
        playerStats.put("GamesPlayed", playerTotals.get("GamesPlayed").toString().split("\\.")[0]);

        // Display only the first decimal
        playerStats.put("Minutes", getFirstDecimal(playerTotals.get("Minutes") / playerTotals.get("GamesPlayed")));
        playerStats.put("Points", getFirstDecimal(playerTotals.get("Points") / playerTotals.get("GamesPlayed")));
        playerStats.put("Rebounds", getFirstDecimal(playerTotals.get("Rebounds") / playerTotals.get("GamesPlayed")));
        playerStats.put("Assists", getFirstDecimal(playerTotals.get("Assists") / playerTotals.get("GamesPlayed")));
        playerStats.put("FG", getFirstDecimal(playerTotals.get("FGMade") * 100 / playerTotals.get("FGAttempts")));
        playerStats.put("FT", getFirstDecimal(playerTotals.get("FTMade") * 100 / playerTotals.get("FTAttempts")));
        playerStats.put("3pt", getFirstDecimal(playerTotals.get("3ptMade") * 100 / playerTotals.get("3ptAttempts")));
        playerStats.put("Blocks", getFirstDecimal(playerTotals.get("Blocks") / playerTotals.get("GamesPlayed")));
        playerStats.put("Steals", getFirstDecimal(playerTotals.get("Steals") / playerTotals.get("GamesPlayed")));
        playerStats.put("Turnovers", getFirstDecimal(playerTotals.get("Turnovers") / playerTotals.get("GamesPlayed")));

        return playerStats;
    }

    /**
     * Calculate the totals of the statistics (all points scored this season, all rebounds, all assists etc...)
     * @return Map with the totals
     */
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

    /**
     * To only get the first decimal of a float
     * @param number Float which will be return
     * @return the number string with only the first decimal
     */
    private String getFirstDecimal(Float number){
        String[] numberSplit = number.toString().split("\\.");
        if (numberSplit.length <= 1)
            return "0.0";
        else {
            return numberSplit[1].length() < 2 ?
                    numberSplit[0] + "." + numberSplit[1].substring(0, numberSplit[1].length()) :
                    numberSplit[0] + "." + numberSplit[1].substring(0, 1);
        }
    }
}

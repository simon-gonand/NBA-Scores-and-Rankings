package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller of a team that makes the calculation in order to display them in a view
 */
public class TeamController {
    private TeamModel team;
    private TreeGames treeGames;

    /**
     * Constructor that initialize the data members
     * @param team TeamModel of the controller
     * @param treeGames Tree with all games and statistics of the current season
     */
    public TeamController(TeamModel team, TreeGames treeGames){
        this.team = team;
        this.treeGames = treeGames;
    }

    /**
     * Getter of the TeamModel of the controller
     * @return TeamModel data member
     */
    public TeamModel getTeam() {
        return team;
    }

    /**
     * Calculate the team results
     * @return Game win and game lose
     */
    public String calculateTeamResults(){
        ArrayList<GameModel> games = treeGames.getAllGamesOfTeam(team);
        int win = 0, loose = 0;
        for (GameModel game : games){
            if (treeGames.getTeamsOfGame(game).get(game.getWinner()).equals(team))
                ++win;
            else
                ++loose;
        }
        return win + " - " + loose;
    }

    /**
     * Calculate the win percentage of the team
     * @return The win percentage
     */
    public String calculateWinPercentage(){
        String winLoose = calculateTeamResults();
        String[] results = calculateTeamResults().split(" - ");
        float win = Float.valueOf(results[0]);
        float loose = Float.valueOf(results[1]);
        float percentage = win * 100 / (win + loose);
        String[] winPercentageSplit = Float.toString(percentage).split("\\.");
        if (winPercentageSplit.length <= 1)
            return "0.0";
        return winPercentageSplit[1].length() < 2 ?
                winPercentageSplit[0]+"."+winPercentageSplit[1].substring(0,winPercentageSplit[1].length()) :
                winPercentageSplit[0]+"."+winPercentageSplit[1].substring(0,2);
    }

    /**
     * Calculate the season stats of the team
     * @return Map with all the season stats of the team
     */
    public Map<String, String> calculateTeamSeasonStats(){
        Map<String, Float> statsTotals = calculateTotals();
        Map<String, String> statsAverages = new HashMap<String, String>();

        statsAverages.put("Points", getFirstDecimal(statsTotals.get("Points") / treeGames.getAllGamesOfTeam(team).size()));
        statsAverages.put("OpposantPoints", getFirstDecimal(statsTotals.get("OpposantPoints") / treeGames.getAllGamesOfTeam(team).size()));
        statsAverages.put("Rebounds", getFirstDecimal(statsTotals.get("Rebounds") / treeGames.getAllGamesOfTeam(team).size()));
        statsAverages.put("Assists", getFirstDecimal(statsTotals.get("Assists") / treeGames.getAllGamesOfTeam(team).size()));
        statsAverages.put("FG", getFirstDecimal(statsTotals.get("FG") / treeGames.getAllGamesOfTeam(team).size()));
        statsAverages.put("FT", getFirstDecimal(statsTotals.get("FTMade") * 100 / statsTotals.get("FTAttempts")));
        statsAverages.put("3pt", getFirstDecimal(statsTotals.get("3pt") / treeGames.getAllGamesOfTeam(team).size()));

        return statsAverages;
    }

    /**
     * Calculate the totals of the statistics (all points scored this season, all rebounds, all assists etc...)
     * @return Map with the totals
     */
    private Map<String, Float> calculateTotals() {
        ArrayList<GameModel> games = treeGames.getAllGamesOfTeam(team);
        float totPoints = 0.0f, totOpposantPoints = 0.0f, totRebounds = 0.0f, totAssists = 0.0f, totFG = 0.0f, totFTMade = 0.0f,
                totFTAttempts = 0.0f, totThreePt = 0.0f;
        for (GameModel game : games) {
            GameController gameController = new GameController(game);
            totPoints += (Integer) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("Points");
            TeamModel opposantTeam = treeGames.getTeamsOfGame(game).get("Home").equals(team) ?
                    treeGames.getTeamsOfGame(game).get("Visitor") : treeGames.getTeamsOfGame(game).get("Home");
            totOpposantPoints += (Integer) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, opposantTeam)).get("Points");
            totRebounds += (Integer) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("Rebounds");
            totAssists += (Integer) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("Assists");
            totFG += Float.valueOf((String) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("FG"));
            String[] ft = gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("FT").toString().split("/");
            totFTMade += Float.valueOf(ft[0]);
            totFTAttempts += Float.valueOf(ft[1]);
            totThreePt += Float.valueOf((String) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("3pt"));
        }
        Map<String, Float> totals = new HashMap<String, Float>();
        totals.put("Points", totPoints);
        totals.put("OpposantPoints", totOpposantPoints);
        totals.put("Rebounds", totRebounds);
        totals.put("Assists", totAssists);
        totals.put("FG", totFG);
        totals.put("FTMade", totFTMade);
        totals.put("FTAttempts", totFTAttempts);
        totals.put("3pt", totThreePt);

        return totals;
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

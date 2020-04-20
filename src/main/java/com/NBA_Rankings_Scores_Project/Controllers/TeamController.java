package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamController {
    private TeamModel team;
    private TreeGames treeGames;

    public TeamController(TeamModel team, TreeGames treeGames){
        this.team = team;
        this.treeGames = treeGames;
    }

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

    public String calculateWinPercentage(){
        String winLoose = calculateTeamResults();
        String[] results = calculateTeamResults().split(" - ");
        float win = Float.valueOf(results[0]);
        float loose = Float.valueOf(results[1]);
        float percentage = win * 100 / (win + loose);
        String[] winPercentageSplit = Float.toString(percentage).split("\\.");
        return winPercentageSplit[1].length() < 2 ?
                winPercentageSplit[0]+"."+winPercentageSplit[1].substring(0,winPercentageSplit[1].length()) :
                winPercentageSplit[0]+"."+winPercentageSplit[1].substring(0,2);
    }

    public Map<String, String> calculateTeamSeasonStats(){
        Map<String, Float> statsTotals = calculateTotals();
        Map<String, String> statsAverages = new HashMap<String, String>();
        String[] pointsAverage = Float.toString(statsTotals.get("Points") / treeGames.getAllGamesOfTeam(team).size()).split("\\.");
        String[] opposantPointsAverage = Float.toString(statsTotals.get("OpposantPoints") / treeGames.getAllGamesOfTeam(team).size()).split("\\.");
        String[] reboundsAverage = Float.toString(statsTotals.get("Rebounds") / treeGames.getAllGamesOfTeam(team).size()).split("\\.");
        String[] assistsAverage = Float.toString(statsTotals.get("Assists") / treeGames.getAllGamesOfTeam(team).size()).split("\\.");
        String[] FGAverage = Float.toString(statsTotals.get("FG") / treeGames.getAllGamesOfTeam(team).size()).split("\\.");
        String[] FTAverage = Float.toString(statsTotals.get("FTMade") / statsTotals.get("FTAttempts")).split("\\.");
        String[] ThreePtAverage = Float.toString(statsTotals.get("3pt") / treeGames.getAllGamesOfTeam(team).size()).split("\\.");

        statsAverages.put("Points", pointsAverage[1].length() < 2 ?
                pointsAverage[0]+"."+pointsAverage[1].substring(0,pointsAverage[1].length()) :
                pointsAverage[0]+"."+pointsAverage[1].substring(0,2));
        statsAverages.put("OpposantPoints", opposantPointsAverage[1].length() < 2 ?
                opposantPointsAverage[0]+"."+opposantPointsAverage[1].substring(0,opposantPointsAverage[1].length()) :
                opposantPointsAverage[0]+"."+opposantPointsAverage[1].substring(0,2));
        statsAverages.put("Rebounds", reboundsAverage[1].length() < 2 ?
                reboundsAverage[0]+"."+reboundsAverage[1].substring(0,reboundsAverage[1].length()) :
                reboundsAverage[0]+"."+reboundsAverage[1].substring(0,2));
        statsAverages.put("Assists", assistsAverage[1].length() < 2 ?
                assistsAverage[0]+"."+assistsAverage[1].substring(0,assistsAverage[1].length()) :
                assistsAverage[0]+"."+assistsAverage[1].substring(0,2));
        statsAverages.put("FG", FGAverage[1].length() < 2 ?
                FGAverage[0]+"."+FGAverage[1].substring(0,FGAverage[1].length()) :
                FGAverage[0]+"."+FGAverage[1].substring(0,2));
        statsAverages.put("FT", FTAverage[1].length() < 2 ?
                FTAverage[0]+"."+FTAverage[1].substring(0,FTAverage[1].length()) :
                FTAverage[0]+"."+FTAverage[1].substring(0,2));
        statsAverages.put("3pt", ThreePtAverage[1].length() < 2 ?
                ThreePtAverage[0]+"."+ThreePtAverage[1].substring(0,ThreePtAverage[1].length()) :
                ThreePtAverage[0]+"."+ThreePtAverage[1].substring(0,2));

        return statsAverages;
    }

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
}

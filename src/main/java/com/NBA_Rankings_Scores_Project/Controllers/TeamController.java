package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;

import java.util.ArrayList;

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

    public String calculatePointsPerGame(){
        ArrayList<GameModel> games = treeGames.getAllGamesOfTeam(team);
        float totPoints = 0;
        for (GameModel game : games) {
            GameController gameController = new GameController(game);
            totPoints += (Integer) gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game, team)).get("Points");
        }
        float pointsPerGame = totPoints / games.size();
        String[] pointsPerGameSplit = Float.toString(pointsPerGame).split("\\.");
        return pointsPerGameSplit[1].length() < 2 ?
                pointsPerGameSplit[0]+"."+pointsPerGameSplit[1].substring(0,pointsPerGameSplit[1].length()) :
                pointsPerGameSplit[0]+"."+pointsPerGameSplit[1].substring(0,2);
    }
}

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
}

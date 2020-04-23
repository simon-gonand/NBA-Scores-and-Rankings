package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import java.util.*;

public class RankingController {
    private TreeSeasonInfo info;
    private TreeGames games;

    public RankingController(TreeSeasonInfo info, TreeGames games){
        this.info = info;
        this.games = games;
    }

    public ArrayList<TeamModel> sortRanking(Conference conference){
        ArrayList<TeamModel> teams = info.getTeamByConference(conference);
        ArrayList<TeamModel> ranking = new ArrayList<TeamModel>();
        ranking = quickSort(teams, 0, teams.size() - 1);
        Collections.reverse(ranking);
        return ranking;
    }

    private ArrayList<TeamModel> quickSort(ArrayList<TeamModel> teams, int l, int u){
        int j;
        if (l < u){
            Map<String, Object> returnValues = partition(teams, l, u);
            teams = (ArrayList<TeamModel>)returnValues.get("list");
            j = (Integer) returnValues.get("index");
            quickSort(teams, l, j-1);
            quickSort(teams, j+1, u);
        }
        return teams;
    }

    private Map<String, Object> partition (ArrayList<TeamModel> teams, int l, int u){
        TeamController teamController = new TeamController(teams.get(l), games);
        Float winPercentage = Float.valueOf(teamController.calculateWinPercentage());
        int i = l, j = u+1;
        TeamModel tmp;
        do {
            do
                i++;
            while(i <= u && Float.valueOf(new TeamController(teams.get(i), games).calculateWinPercentage()) < winPercentage);
            do
                --j;
            while(winPercentage < Float.valueOf(new TeamController(teams.get(j), games).calculateWinPercentage()));
            if (i < j){
                tmp = teams.get(i);
                teams.set(i, teams.get(j));
                teams.set(j, tmp);
            }
        } while(i < j);
        teams.set(l, teams.get(j));
        teams.set(j, teamController.getTeam());

        Map<String, Object> returnValues = new HashMap<String, Object>();
        returnValues.put("index", j);
        returnValues.put("list", teams);
        return returnValues;
    }
}

package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

import java.util.ArrayList;

/**
 * Controller of the research that implements the search algorithms
 */
public class ResearchControllers {
    private TreeSeasonInfo info;

    /**
     * Controller that initialize the data member
     * @param info Tree of the general informations of the current Season
     * @see TreeSeasonInfo
     */
    public ResearchControllers(TreeSeasonInfo info) {
        this.info = info;
    }

    /**
     * Search algorithm for players
     * @param name Name of the player to search
     * @param post Position of the players to search
     * @param team Team of the players to search
     * @param nationality Nationality of the players to search
     * @return List of the Player Model that fits with the parameters of the research
     * @see PlayerModel
     */
    public ArrayList<PlayerModel> doPlayerSearch(String name, String post, String team, String nationality){
        name = name.toLowerCase();
        team = team.toLowerCase();
        post = post.toLowerCase();
        nationality = nationality.toLowerCase();
        ArrayList<PlayerModel> results = new ArrayList<PlayerModel>();
        for (TeamModel teamModel : info.getTeams()){
            for (PlayerModel playerModel : info.getPlayersByTeam(teamModel)){
                String[] teamModelSplit = teamModel.getName().split(" ");
                if (teamModelSplit.length == 3) {
                    teamModelSplit[0] = teamModelSplit[0] + " " + teamModelSplit[1];
                    teamModelSplit[1] = teamModelSplit[2];
                }
                boolean isGood = true;
                if (!name.equals(playerModel.getSurname().toLowerCase()) && !name.equals(""))
                    isGood = false;
                else if (!post.equals(playerModel.getPosition().toString().toLowerCase()) && !post.equals("none"))
                    isGood = false;
                else if (!team.equals(teamModelSplit[0].toLowerCase()) && !team.equals(teamModelSplit[1].toLowerCase())
                        && !team.equals(teamModel.getName().toLowerCase()) && !team.equals(""))
                    isGood = false;
                else if (!nationality.equals(playerModel.getNationality().toLowerCase()) && !nationality.equals(""))
                    isGood = false;
                if (isGood)
                    results.add(playerModel);
            }
        }
        return results;
    }

    /**
     * Search algorithm for the teams
     * @param name Name of the team to search
     * @param conference Name of the conference to search
     * @return List of the teams that fits with the parameters of the research
     * @see TeamModel
     */
    public ArrayList<TeamModel> doTeamSearch(String name, String conference){
        name = name.toLowerCase();
        conference = conference.toLowerCase();
        ArrayList<TeamModel> results = new ArrayList<TeamModel>();
        for (TeamModel team : info.getTeams()){
            String[] teamModelSplit = team.getName().split(" ");
            if (teamModelSplit.length == 3) {
                teamModelSplit[0] = teamModelSplit[0] + " " + teamModelSplit[1];
                teamModelSplit[1] = teamModelSplit[2];
            }
            boolean isGood = true;
            try {
                if (!name.equals(teamModelSplit[0].toLowerCase()) && !name.equals(teamModelSplit[1].toLowerCase())
                        && !name.equals(team.getName().toLowerCase()) && !name.equals(""))
                    isGood = false;
                else if (!conference.equals(info.getConferenceOfATeam(team).getName().toLowerCase()) && !conference.equals("none"))
                    isGood = false;
            } catch (Exception e){
                e.printStackTrace();
            }

            if (isGood)
                results.add(team);
        }
        return results;
    }
}

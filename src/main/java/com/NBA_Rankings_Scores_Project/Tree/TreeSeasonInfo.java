package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.Season;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;

import java.util.ArrayList;

/**
 * Tree with the general info of the season (teams, players and conferences)
 * @see Tree
 */
public class TreeSeasonInfo extends Tree {

    /**
     * Constructor which call the constructor of Tree
     * @param season Season which will be the Tree root
     * @see Tree
     * @see Season
     */
    public TreeSeasonInfo(Season season){
        super (season);
    }

    /**
     * To get the conferences of the current season
     * @return ArrayList with the Conferences
     * @see TreeNode
     * @see Conference
     */
    public ArrayList<Conference> getConferences(){
        ArrayList<Conference> confList = new ArrayList<Conference>();
        for(TreeNode confNode : this.getRoot().getChildren()) {
            if (confNode.getData().getClass().equals(Conference.class))
                confList.add((Conference) confNode.getData());
        }
        return confList;
    }

    /**
     * To get all teams of the current season
     * @return ArrayList with all the teams
     * @see TreeNode
     * @see TeamModel
     */
    public ArrayList<TeamModel> getTeams(){
        ArrayList<TeamModel> teamsList = new ArrayList<TeamModel>();
        for (TreeNode confNode : this.getRoot().getChildren()){
            for (TreeNode teamNode : confNode.getChildren()){
                if (teamNode.getData().getClass().equals(TeamModel.class))
                    teamsList.add((TeamModel)teamNode.getData());
            }
        }
        return teamsList;
    }

    /**
     * To get the teams of one conference
     * @param conference Conference with the teams wanted
     * @return ArrayList with the teams of the conference
     * @see TreeNode
     * @see Conference
     * @see TeamModel
     */
    public ArrayList<TeamModel> getTeamByConference(Conference conference){
        ArrayList<TeamModel> teamsList = new ArrayList<TeamModel>();
        for (TreeNode confNode : this.getRoot().getChildren()){
            if (confNode.getData().getName().equals(conference.getName())){
                for (TreeNode teamNode : confNode.getChildren()){
                    if (teamNode.getData().getClass().equals(TeamModel.class))
                        teamsList.add((TeamModel) teamNode.getData());
                }
            }
        }
        return teamsList;
    }

    /**
     * To get the players of a team
     * @param team TeamModel with the players wanted
     * @return ArrayList with the players of the team
     * @see TreeNode
     * @see TeamModel
     * @see PlayerModel
     */
    public ArrayList<PlayerModel> getPlayersByTeam(TeamModel team){
        ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
        for (TreeNode confNode : this.getRoot().getChildren()){
            for (TreeNode teamNode : confNode.getChildren()){
                if (teamNode.getData().getName().equals(team.getName())){
                    for (TreeNode playerNode : teamNode.getChildren()){
                        if (playerNode.getData().getClass().equals(PlayerModel.class))
                            playersList.add((PlayerModel) playerNode.getData());
                    }
                }
            }
        }
        return playersList;
    }

    /**
     * To get the conference of a team
     * @param team TeamModel of the conference wanted
     * @return Conference of the team
     * @throws Exception if the team doesn't exist
     * @see TreeNode
     * @see TeamModel
     * @see Conference
     */
    public Conference getConferenceOfATeam (TeamModel team) throws Exception {
        for (TreeNode confNode : this.getRoot().getChildren()){
            for (TreeNode teamNode : confNode.getChildren()) {
                if (teamNode.getData().getClass().equals(TeamModel.class))
                    if (teamNode.getData().equals(team))
                        return (Conference) confNode.getData();
            }
        }
        throw new Exception("Team doesn't exist");
    }

    /**
     * To get the team of a player
     * @param player PlayerModel of the team wanted
     * @return TeamModel of the player
     * @throws Exception if the player doesn't exist in the tree
     * @see PlayerModel
     * @see TeamModel
     */
    public TeamModel getTeamOfAPlayer (PlayerModel player)throws Exception{
        for (TeamModel team : getTeams()){
            for (PlayerModel model : getPlayersByTeam(team)){
                if (model.equals(player))
                    return team;
            }
        }
        throw new Exception("Player doesn't exist");
    }
}

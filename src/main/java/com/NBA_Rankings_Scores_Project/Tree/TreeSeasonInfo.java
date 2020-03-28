package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.Season;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;

import java.util.ArrayList;

public class TreeSeasonInfo extends Tree {

    public TreeSeasonInfo(Season season){
        super (season);
    }

    public ArrayList<Conference> getConferences(){
        ArrayList<Conference> confList = new ArrayList<Conference>();
        for(TreeNode confNode : this.getRoot().getChilds()) {
            if (confNode.getData().getClass().equals(Conference.class))
                confList.add((Conference) confNode.getData());
        }

        return confList;
    }

    public ArrayList<TeamModel> getTeams(){
        ArrayList<TeamModel> teamsList = new ArrayList<TeamModel>();
        for (TreeNode confNode : this.getRoot().getChilds()){
            for (TreeNode teamNode : confNode.getChilds()){
                if (teamNode.getData().getClass().equals(TeamModel.class))
                    teamsList.add((TeamModel)teamNode.getData());
            }
        }

        return teamsList;
    }

    public ArrayList<TeamModel> getTeamByConference(Conference conference){
        ArrayList<TeamModel> teamsList = new ArrayList<TeamModel>();
        for (TreeNode confNode : this.getRoot().getChilds()){
            if (confNode.getData().getName().equals(conference.getName())){
                for (TreeNode teamNode : confNode.getChilds()){
                    if (teamNode.getData().getClass().equals(TeamModel.class))
                        teamsList.add((TeamModel) teamNode.getData());
                }
            }
        }

        return teamsList;
    }

    public ArrayList<PlayerModel> getPlayersByTeam(TeamModel team){
        ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
        for (TreeNode confNode : this.getRoot().getChilds()){
            for (TreeNode teamNode : confNode.getChilds()){
                if (teamNode.getData().getName().equals(team.getName())){
                    for (TreeNode playerNode : teamNode.getChilds()){
                        if (playerNode.getData().getClass().equals(PlayerModel.class))
                            playersList.add((PlayerModel) playerNode.getData());
                    }
                }
            }
        }

        return playersList;
    }
}

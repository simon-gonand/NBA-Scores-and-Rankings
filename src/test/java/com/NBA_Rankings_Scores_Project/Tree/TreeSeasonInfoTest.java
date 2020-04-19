package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSeasonInfoTest {
    private static TreeSeasonInfo treeSeasonInfo;

    @BeforeAll
    public static void setup() {
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
    }

    @Test
    public void getConferencesTest(){
        ArrayList<Conference> conferences = treeSeasonInfo.getConferences();
        assertEquals("West", conferences.get(0).getName(), "Conference must be West");
        assertEquals("East", conferences.get(1).getName(), "Conference must be East");
    }

    @Test
    public void getTeamsTest(){
        ArrayList<TeamModel> teams = treeSeasonInfo.getTeams();
        TeamModel team = teams.get(0);
        assertEquals("Dallas Mavericks",
                team.getName(),
                "Team's name must be Dallas Mavericks");
        assertEquals("Rick Carlisle",
                team.getHeadCoach(),
                "Team's head coach must be Rick Carlisle");

        team = teams.get(1);
        assertEquals("Los Angeles Clippers",
                team.getName(),
                "Team must be Los Angeles Clippers");
        assertEquals("Doc Rivers",
                team.getHeadCoach(),
                "Team must be Doc Rivers");

        team = teams.get(2);
        assertEquals("Milwaukee Bucks",
                team.getName(),
                "Team'name must be Milwaukee Bucks");
        assertEquals("Mike Budenholzer",
                team.getHeadCoach(),
                "Team's head coach must be Mike Budenholzer");

        team = teams.get(3);
        assertEquals("New Orleans Pelicans",
                team.getName(),
                "Team's name must be New Orleans Pelicans");
        assertEquals("Alvin Gentry",
                team.getHeadCoach(),
                "Team's head coach must be Alvin Gentry");
    }

    @Test
    public void getTeamByConferenceTest(){
        ArrayList<Conference> conferences = treeSeasonInfo.getConferences();
        ArrayList<TeamModel> teams = treeSeasonInfo.getTeamByConference(conferences.get(0));
        TeamModel team = teams.get(0);
        assertEquals("Dallas Mavericks",
                team.getName(),
                "Team's name must be Dallas Mavericks");
        assertEquals("Rick Carlisle",
                team.getHeadCoach(),
                "Team's head coach must be Rick Carlisle");

        team = teams.get(1);
        assertEquals("Los Angeles Clippers",
                team.getName(),
                "Team must be Los Angeles Clippers");
        assertEquals("Doc Rivers",
                team.getHeadCoach(),
                "Team must be Doc Rivers");
    }

    @Test
    public void getPlayersByTeamTest(){
        ArrayList<TeamModel> teams = treeSeasonInfo.getTeams();
        TeamModel team = teams.get(0);
        ArrayList<PlayerModel> players = treeSeasonInfo.getPlayersByTeam(team);
        PlayerModel player = players.get(0);
        assertEquals("J.J.", player.getName(), "Player's name must be J.J.");
        assertEquals("Barea", player.getSurname(), "Player's surname must be Barea");
        assertEquals(PlayerModel.Position.GUARD,
                player.getPosition(),
                "Player's position must be Guard");
        assertEquals(5, player.getNumber(), "Player's number must be 5");
        assertEquals(35, player.getAge(), "Player's age must be 35");
        assertEquals("Puerto Rican",
                player.getNationality(),
                "Player's nationality must be Puerto Rican");
        assertEquals(0, player.getID(), "Player's ID must be 0");
    }

    @Test
    public void getConferenceOfTeamTest(){
        TeamModel team = treeSeasonInfo.getTeams().get(0);
        try {
            Conference conference = treeSeasonInfo.getConferenceOfATeam(team);
            assertEquals("West", conference.getName(), "Conference must be West");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


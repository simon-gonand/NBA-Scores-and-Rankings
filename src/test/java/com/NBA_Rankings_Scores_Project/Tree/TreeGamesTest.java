package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeGamesTest {
    private static TreeSeasonInfo treeSeasonInfo;
    private static TreeGames treeGames;

    @BeforeAll
    public static void setup() {
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
        treeGames = parser.getTreeSeasonGames(treeSeasonInfo);
    }

    @Test
    public void getAllGamesTest(){
        ArrayList<GameModel> games = treeGames.getAllGames();
        GameModel gameModel = games.get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yy");
        String date = sdf.format(gameModel.getDate());
        assertEquals("26/10/19", date, "Game's date must be 26/10/19");
        assertEquals("visitor", gameModel.getWinner(), "Game's winner must be visitor");
        assertEquals("116-123", gameModel.getTotScore(), "Game's total score must be 116-123");
        assertEquals("27-43", gameModel.getQ1Score(), "Game's q1 score must be 27-43");
        assertEquals("45-23", gameModel.getQ2Score(), "Game's q2 score must be 45-23");
        assertEquals("23-29", gameModel.getQ3Score(), "Game's q3 score must be 23-29");
        assertEquals("28-23", gameModel.getQ4Score(), "Game's q4 score must be 28-23");
    }

    @Test
    public void getTeamsOfGameTest(){
        ArrayList<GameModel> games = treeGames.getAllGames();
        GameModel gameModel = games.get(0);
        HashMap<String, TeamModel> teams = treeGames.getTeamsOfGame(gameModel);
        assertEquals("New Orleans Pelicans",
                teams.get("Home").getName(),
                "Home team's name must be New Orleans Pelicans");
        assertEquals("Alvin Gentry",
                teams.get("Home").getHeadCoach(),
                "Team's head coach must be Alvin Gentry");
        assertEquals("Dallas Mavericks",
                teams.get("Visitor").getName(),
                "Home team's name must be Dallas Mavericks");
        assertEquals("Rick Carlisle",
                teams.get("Visitor").getHeadCoach(),
                "Team's head coach must be Rick Carlisle");

    }

    @Test
    public void getPlayerStatsByTeamTest(){
        ArrayList<GameModel> games = treeGames.getAllGames();
        GameModel gameModel = games.get(0);
        HashMap<String, TeamModel> teams = treeGames.getTeamsOfGame(gameModel);
        ArrayList<PlayerStats> playersStats = treeGames.getPlayerStatsByTeam(gameModel, teams.get("Home"));

        PlayerStats playerStats = playersStats.get(0);
        assertEquals("0", playerStats.getID().toString(), "Player's stat ID must be 0");
        assertEquals("14", playerStats.getMinutes().toString(), "Player's minutes must be 14");
        assertEquals("6", playerStats.getPoints().toString(), "Player's points must be 6");
        assertEquals("1", playerStats.getRebounds().toString(), "Player's rebounds must be 1");
        assertEquals("6", playerStats.getAssists().toString(), "Player's assists must be 6");
        assertEquals("2/9", playerStats.getFg(), "Player's field goals must be 2/9");
        assertEquals("2/5", playerStats.getThreePts(), "Player's three points must be 2/5");
        assertEquals("1/5", playerStats.getFt(), "Player's free throws must be 1/5");
        assertEquals("0", playerStats.getSteals().toString(), "Player's steals must be 0");
        assertEquals("0", playerStats.getBlocks().toString(), "Player's blocks must be 0");
        assertEquals("1", playerStats.getTurnovers().toString(), "Player's turnovers must be 1");

        playersStats = treeGames.getPlayerStatsByTeam(gameModel, teams.get("Visitor"));
        playerStats = playersStats.get(0);
        assertEquals("0", playerStats.getID().toString(), "Player's stat ID must be 0");
        assertEquals("0", playerStats.getMinutes().toString(), "Player's minutes must be 0");
        assertEquals(null, playerStats.getPoints(), "Player's points must be null");
        assertEquals(null, playerStats.getRebounds(), "Player's rebounds must be null");
        assertEquals(null, playerStats.getAssists(), "Player's assists must be null");
        assertEquals(null, playerStats.getFg(), "Player's field goals must be null");
        assertEquals(null, playerStats.getThreePts(), "Player's three points must be null");
        assertEquals(null, playerStats.getFt(), "Player's free throws must be null");
        assertEquals(null, playerStats.getSteals(), "Player's steals must be null");
        assertEquals(null, playerStats.getBlocks(), "Player's blocks must be null");
        assertEquals(null, playerStats.getTurnovers(), "Player's turnovers must be null");
    }
}

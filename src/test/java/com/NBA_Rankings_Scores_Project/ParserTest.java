package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.*;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static TreeSeasonInfo treeSeasonInfo;
    private static TreeNode confWest;
    private static TreeNode confEast;
    private static TreeNode teamWest1;
    private static TreeNode teamWest2;
    private static TreeNode teamEast1;
    private static TreeNode teamEast2;
    private static TreeNode playerTeamWest1;
    private static TreeNode playerTeamWest2;
    private static TreeNode playerTeamEast1;
    private static TreeNode playerTeamEast2;

    private static TreeGames treeGames;
    private static TreeNode gameNode;
    private static TreeNode homeNode;
    private static TreeNode visitorNode;
    private static TreeNode homePlayerStatNode;
    private static TreeNode visitorPlayerStatNode;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();

        confWest = treeSeasonInfo.getRoot().getChilds().get(0);
        confEast = treeSeasonInfo.getRoot().getChilds().get(1);

        teamWest1 = confWest.getChilds().get(0);
        teamWest2 = confWest.getChilds().get(1);
        teamEast1 = confEast.getChilds().get(0);
        teamEast2 = confEast.getChilds().get(1);

        playerTeamWest1 = teamWest1.getChilds().get(0);
        playerTeamWest2 = teamWest2.getChilds().get(0);
        playerTeamEast1 = teamEast1.getChilds().get(0);
        playerTeamEast2 = teamEast2.getChilds().get(0);

        treeGames = parser.getTreeSeasonGames(treeSeasonInfo);

        gameNode = treeGames.getRoot().getChilds().get(0);
        homeNode = gameNode.getChilds().get(0);
        visitorNode = gameNode.getChilds().get(1);
        homePlayerStatNode = homeNode.getChilds().get(0);
        visitorPlayerStatNode = visitorNode.getChilds().get(0);
    }

    @Test
    public void seasonInfoTest() {
        assertEquals("2019/20", treeSeasonInfo.getRoot().getData().getName(), "Season must be 2019/20");
    }

    @Test
    public void conferenceInfoTest() {
        assertEquals("West", confWest.getData().getName(), "Conference must be West");
        assertEquals("East", confEast.getData().getName(), "Conference must be East");
    }

    @Test
    public void teamInfoTest() {
        TeamModel teamWestModel1 = (TeamModel) teamWest1.getData();
        TeamModel teamWestModel2 = (TeamModel) teamWest2.getData();
        assertEquals("Dallas Mavericks",
                teamWestModel1.getName(),
                "Team's name must be Dallas Mavericks");
        assertEquals("Rick Carlisle",
                teamWestModel1.getHeadCoach(),
                "Team's head coach must be Rick Carlisle");

        assertEquals("Los Angeles Clippers",
                teamWestModel2.getName(),
                "Team must be Los Angeles Clippers");

        assertEquals("Doc Rivers",
                teamWestModel2.getHeadCoach(),
                "Team must be Doc Rivers");

        TeamModel teamEastModel1 = (TeamModel) teamEast1.getData();
        TeamModel teamEastModel2 = (TeamModel) teamEast2.getData();
        assertEquals("Milwaukee Bucks",
                teamEastModel1.getName(),
                "Team'name must be Milwaukee Bucks");
        assertEquals("Mike Budenholzer",
                teamEastModel1.getHeadCoach(),
                "Team's head coach must be Mike Budenholzer");
        assertEquals("New Orleans Pelicans",
                teamEastModel2.getName(),
                "Team's name must be New Orleans Pelicans");
        assertEquals("Alvin Gentry",
                teamEastModel2.getHeadCoach(),
                "Team's head coach must be Alvin Gentry");
    }

    @Test
    public void playerInfoTest() {
        PlayerModel playerModelTeamWest1 = (PlayerModel) playerTeamWest1.getData();
        assertEquals("J.J.", playerModelTeamWest1.getName(), "Player's name must be J.J.");
        assertEquals("Barea", playerModelTeamWest1.getSurname(), "Player's surname must be Barea");
        assertEquals(PlayerModel.Position.GUARD,
                playerModelTeamWest1.getPosition(),
                "Player's position must be Guard");
        assertEquals(5, playerModelTeamWest1.getNumber(), "Player's number must be 5");
        assertEquals(35, playerModelTeamWest1.getAge(), "Player's age must be 35");
        assertEquals("Puerto Rican",
                playerModelTeamWest1.getNationality(),
                "Player's nationality must be Puerto Rican");
        assertEquals(0, playerModelTeamWest1.getID(), "Player's ID must be 0");

        PlayerModel playerModelTeamWest2 = (PlayerModel) playerTeamWest2.getData();
        assertEquals("Patrick", playerModelTeamWest2.getName(), "Player's name must be Patrick");
        assertEquals("Beverley",
                playerModelTeamWest2.getSurname(),
                "Player's surname must be Beverley");
        assertEquals(PlayerModel.Position.SHOOTING_GUARD,
                playerModelTeamWest2.getPosition(),
                "Player's position must be Shooting Guard");
        assertEquals(31, playerModelTeamWest2.getNumber(), "Player's number must be 31");
        assertEquals(27, playerModelTeamWest2.getAge(), "Player's age must be 27");
        assertEquals("American",
                playerModelTeamWest2.getNationality(),
                "Player's nationality must be American");
        assertEquals(0, playerModelTeamWest2.getID(), "Player's ID must be 0");

        PlayerModel playerModelTeamEast1 = (PlayerModel) playerTeamEast1.getData();
        assertEquals("Giannis", playerModelTeamEast1.getName(), "Player's name must be Giannis");
        assertEquals("Antetokounmpo",
                playerModelTeamEast1.getSurname(),
                "Player's surname must be Antetokounmpo");
        assertEquals(PlayerModel.Position.POWER_FORWARD,
                playerModelTeamEast1.getPosition(),
                "Player's position must be Power Forward");
        assertEquals(34, playerModelTeamEast1.getNumber(), "Player's number must be 34");
        assertEquals(25, playerModelTeamEast1.getAge(), "Player's age must be 25");
        assertEquals("Greek",
                playerModelTeamEast1.getNationality(),
                "Player's nationality must be Greek");
        assertEquals(0, playerModelTeamEast1.getID(), "Player's ID must be 0");

        PlayerModel playerModelTeamEast2 = (PlayerModel) playerTeamEast2.getData();
        assertEquals("Nickeil", playerModelTeamEast2.getName(), "Player's name must be Nickeil");
        assertEquals("Alexander-Walker",
                playerModelTeamEast2.getSurname(),
                "Player's surname must be Alexander-Walker");
            assertEquals(PlayerModel.Position.CENTER,
                    playerModelTeamEast2.getPosition(),
                    "Player's position must be Center");
        assertEquals(0, playerModelTeamEast2.getNumber(), "Player's number must be 0");
        assertEquals(21, playerModelTeamEast2.getAge(), "Player's age must be 21");
        assertEquals("Canadian",
                playerModelTeamEast2.getNationality(),
                "Player's nationality must be Canadian");
        assertEquals(0, playerModelTeamEast2.getID(), "Player's ID must be 0");
    }

    @Test
    public void gameInfoTest(){
        GameModel gameModel = (GameModel) gameNode.getData();
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
    public void gameTeamsTest(){
        assertEquals("New Orleans Pelicans",
                homeNode.getData().getName(),
                "Home team's name must be New Orleans Pelicans");
        assertEquals("Dallas Mavericks",
                visitorNode.getData().getName(),
                "Home team's name must be Dallas Mavericks");
    }

    @Test
    public void playerStatsTest(){
        PlayerStats homePlayerStats = (PlayerStats) homePlayerStatNode.getData();
        assertEquals("0", homePlayerStats.getID().toString(), "Player's stat ID must be 0");
        assertEquals("14", homePlayerStats.getMinutes().toString(), "Player's minutes must be 14");
        assertEquals("6", homePlayerStats.getPoints().toString(), "Player's points must be 6");
        assertEquals("1", homePlayerStats.getRebounds().toString(), "Player's rebounds must be 1");
        assertEquals("6", homePlayerStats.getAssists().toString(), "Player's assists must be 6");
        assertEquals("2/9", homePlayerStats.getFg(), "Player's field goals must be 2/9");
        assertEquals("2/5", homePlayerStats.getThreePts(), "Player's three points must be 2/5");
        assertEquals("0/0", homePlayerStats.getFt(), "Player's free throws must be 0/0");
        assertEquals("0", homePlayerStats.getSteals().toString(), "Player's steals must be 0");
        assertEquals("0", homePlayerStats.getBlocks().toString(), "Player's blocks must be 0");
        assertEquals("1", homePlayerStats.getTurnovers().toString(), "Player's turnovers must be 1");

        PlayerStats visitorPlayerStats = (PlayerStats) visitorPlayerStatNode.getData();
        assertEquals("0", visitorPlayerStats.getID().toString(), "Player's stat ID must be 0");
        assertEquals("0", visitorPlayerStats.getMinutes().toString(), "Player's minutes must be 0");
        assertEquals(null, visitorPlayerStats.getPoints(), "Player's points must be null");
        assertEquals(null, visitorPlayerStats.getRebounds(), "Player's rebounds must be null");
        assertEquals(null, visitorPlayerStats.getAssists(), "Player's assists must be null");
        assertEquals(null, visitorPlayerStats.getFg(), "Player's field goals must be null");
        assertEquals(null, visitorPlayerStats.getThreePts(), "Player's three points must be null");
        assertEquals(null, visitorPlayerStats.getFt(), "Player's free throws must be null");
        assertEquals(null, visitorPlayerStats.getSteals(), "Player's steals must be null");
        assertEquals(null, visitorPlayerStats.getBlocks(), "Player's blocks must be null");
        assertEquals(null, visitorPlayerStats.getTurnovers(), "Player's turnovers must be null");
    }
}

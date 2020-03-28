package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Tree.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static Tree tree;
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

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        tree = parser.getTreeSeason();

        confWest = tree.getRoot().getChilds().get(0);
        confEast = tree.getRoot().getChilds().get(1);

        teamWest1 = confWest.getChilds().get(0);
        teamWest2 = confWest.getChilds().get(1);
        teamEast1 = confEast.getChilds().get(0);
        teamEast2 = confEast.getChilds().get(1);

        playerTeamWest1 = teamWest1.getChilds().get(0);
        playerTeamWest2 = teamWest2.getChilds().get(0);
        playerTeamEast1 = teamEast1.getChilds().get(0);
        playerTeamEast2 = teamEast2.getChilds().get(0);
    }

    @Test
    public void seasonInfoTest() {
        assertEquals("2019/20", tree.getRoot().getData().getName(), "Season must be 2019/20");
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
}

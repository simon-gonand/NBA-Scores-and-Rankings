package com.NBA_Rankings_Scores_Project;

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
        tree = parser.getTree();

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
        assertEquals("Dallas Mavericks", teamWestModel1.getName(), "Team must be Dallas Mavericks");
        assertEquals("Los Angeles Clippers", teamWestModel2.getName(), "Team must be Los Angeles Clippers");

        TeamModel teamEastModel1 = (TeamModel) teamEast1.getData();
        TeamModel teamEastModel2 = (TeamModel) teamEast2.getData();
        assertEquals("Milwaukee Bucks", teamEastModel1.getName(), "Team must be Milwaukee Bucks");
        assertEquals("Milwaukee Bucks", teamEastModel1.getName(), "Team must be Milwaukee Bucks");
        assertEquals("New Orleans Pelicans", teamEastModel2.getName(), "Team must be New Orleans Pelicans");
    }

    @Test
    public void playerInfoTest() {
        assertEquals("J.J.",playerTeamWest1.getData().getName(), "Player must be J.J.");
        assertEquals("Patrick",playerTeamWest2.getData().getName(), "Player must be Patrick");

        assertEquals("Giannis",playerTeamEast1.getData().getName(), "Player must be Giannis");
        assertEquals("Nickeil",playerTeamEast2.getData().getName(), "Player must be Nickeil");
    }
}

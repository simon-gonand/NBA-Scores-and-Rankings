package com.NBA_Rankings_Scores_Project;

import com.NBA_Rankings_Scores_Project.Tree.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static Tree tree;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/main/resources/Test_Datas.xml");
        tree = parser.getTree();
    }

    @Test
    public void generalInfoTest(){

        assertEquals("2019/20",tree.getRoot().getData().getName(), "Season must be 2019/20");

        TreeNode confWest = tree.getRoot().getChilds().get(0);
        TreeNode confEast = tree.getRoot().getChilds().get(1);
        assertEquals("West",confWest.getData().getName(), "Conference must be West");
        assertEquals("East",confEast.getData().getName(), "Conference must be East");

        TreeNode teamWest1 = confWest.getChilds().get(0);
        TreeNode teamWest2 = confWest.getChilds().get(1);
        assertEquals("Dallas Mavericks",teamWest1.getData().getName(), "Team must be Dallas Mavericks");
        assertEquals("Los Angeles Clippers",teamWest2.getData().getName(), "Team must be Los Angeles Clippers");

        TreeNode teamEast1 = confEast.getChilds().get(0);
        TreeNode teamEast2 = confEast.getChilds().get(1);
        assertEquals("Milwaukee Bucks",teamEast1.getData().getName(), "Team must be Milwaukee Bucks");
        assertEquals("New Orleans Pelicans",teamEast2.getData().getName(), "Team must be New Orleans Pelicans");

        TreeNode playerTeamWest1 = teamWest1.getChilds().get(0);
        TreeNode playerTeamWest2 = teamWest2.getChilds().get(0);
        assertEquals("J.J.",playerTeamWest1.getData().getName(), "Player must be J.J.");
        assertEquals("Patrick",playerTeamWest2.getData().getName(), "Player must be Patrick");

        TreeNode playerTeamEast1 = teamEast1.getChilds().get(0);
        TreeNode playerTeamEast2 = teamEast2.getChilds().get(0);
        assertEquals("Giannis",playerTeamEast1.getData().getName(), "Player must be Giannis");
        assertEquals("Nickeil",playerTeamEast2.getData().getName(), "Player must be Nickeil");
    }
}

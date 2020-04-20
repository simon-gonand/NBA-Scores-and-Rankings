package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.TeamController;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamControllerTest {
    private static TreeGames treeGames;
    private static TreeSeasonInfo treeSeasonInfo;
    private static TeamController teamController;
    private static TeamController teamController2;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
        treeGames = parser.getTreeSeasonGames(treeSeasonInfo);
    }

    @Test
    public void calculateTeamResultsTest(){
        teamController = new TeamController(treeSeasonInfo.getTeams().get(0), treeGames);
        teamController2 = new TeamController(treeSeasonInfo.getTeams().get(3), treeGames);
        assertEquals("1 - 0", teamController.calculateTeamResults(), "Results must be \"1 - 0\"");
        assertEquals("1 - 1", teamController2.calculateTeamResults(), "Results must be \"1 - 1\"");
    }

    @Test
    public void calculateWinPercentageOfATeam(){
        teamController = new TeamController(treeSeasonInfo.getTeams().get(3), treeGames);
        String winPercentage = teamController.calculateWinPercentage();
        assertEquals("50.0", winPercentage, "Win percentage must be 50.0");
    }
}

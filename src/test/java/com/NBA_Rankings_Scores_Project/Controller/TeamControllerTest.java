package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.TeamController;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamControllerTest {
    private static TreeGames treeGames;
    private static TreeSeasonInfo treeSeasonInfo;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
        treeGames = parser.getTreeSeasonGames(treeSeasonInfo);
    }

    @Test
    public void calculateTeamResultsTest(){
        TeamController teamController = new TeamController(treeSeasonInfo.getTeams().get(0), treeGames);
        TeamController teamController2 = new TeamController(treeSeasonInfo.getTeams().get(3), treeGames);
        assertEquals("1 - 0", teamController.calculateTeamResults(), "Results must be \"1 - 0\"");
        assertEquals("1 - 1", teamController2.calculateTeamResults(), "Results must be \"1 - 1\"");
    }

    @Test
    public void calculateWinPercentageTest(){
        TeamController teamController = new TeamController(treeSeasonInfo.getTeams().get(3), treeGames);
        String winPercentage = teamController.calculateWinPercentage();
        assertEquals("50.0", winPercentage, "Win percentage must be 50.0");
    }

    @Test
    public void calculateTeamSeasonStatsTest(){
        TeamController teamController = new TeamController(treeSeasonInfo.getTeams().get(3), treeGames);
        Map<String, String> teamSeasonStats = teamController.calculateTeamSeasonStats();
        assertEquals("25.5", teamSeasonStats.get("Points"), "Points per game average must be 25.5");
        assertEquals("22.0", teamSeasonStats.get("OpposantPoints"), "Opposant oints per game average must be 22.0");
        assertEquals("8.0", teamSeasonStats.get("Rebounds"), "Rebounds per game average must be 8.0");
        assertEquals("15.5", teamSeasonStats.get("Assists"), "Assists per game average must be 15.5");
        assertEquals("39.0", teamSeasonStats.get("FG"), "Field Goal per game average must be 39.0");
        assertEquals("51.5", teamSeasonStats.get("FT"), "Free Trows per game average must be 51.5");
        assertEquals("41.4", teamSeasonStats.get("3pt"), "Three Points per game average must be 41.4");
    }
}

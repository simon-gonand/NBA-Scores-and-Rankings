package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.GameController;
import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    private static TreeSeasonInfo treeSeasonInfo;
    private static TreeGames treeGames;
    private static GameModel game;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
        treeGames = parser.getTreeSeasonGames(treeSeasonInfo);
        game = treeGames.getAllGames().get(0);
    }

    @Test
    public void calculateTeamStatTest(){
        GameController gameController = new GameController(game);
        Map<String, Object> playerStats = gameController.calculateTeamStats(treeGames.getPlayerStatsByTeam(game,
                treeGames.getTeamsOfGame(game).get("Home")));
        assertEquals(18, playerStats.get("Points"), "Points must be 18");
        assertEquals(3, playerStats.get("Rebounds"),"Rebounds must be 3");
        assertEquals(18, playerStats.get("Assists"), "Assists must be 18");
        assertEquals(5, playerStats.get("Steals"),"Steals must be 5");
        assertEquals(1, playerStats.get("Blocks"), "Blocks must be 1");
        assertEquals(3, playerStats.get("Turnovers"), "Turnovers must be 3");
        assertEquals("22.22", playerStats.get("FG"), "Field Goal must be " + 22.22);
        assertEquals("40.0", playerStats.get("3pt"), "Three Points must be " + (float)2*100/5);
        assertEquals("3/15", playerStats.get("FT"), "Free Trows must be 3/15");
    }
}

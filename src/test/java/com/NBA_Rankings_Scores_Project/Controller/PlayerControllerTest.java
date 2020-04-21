package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.PlayerController;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerControllerTest {
    private static TreeSeasonInfo treeSeasonInfo;
    private static TreeGames treeGames;
    private static PlayerModel player;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
        treeGames = parser.getTreeSeasonGames(treeSeasonInfo);
        player = treeSeasonInfo.getPlayersByTeam(treeSeasonInfo.getTeams().get(3)).get(0);
    }

    @Test
    public void calculatePlayerStats(){
        PlayerController controller = new PlayerController(player, treeSeasonInfo, treeGames);
        Map<String, String> playerStats = controller.calculatePlayerSeasonStats();
        assertEquals("2", playerStats.get("GamesPlayed"), "Games played must be 2");
        assertEquals("16.5", playerStats.get("Minutes"), "Minutes must be 16.5");
        assertEquals("8.0", playerStats.get("Points"), "Points must be 8.0");
        assertEquals("1.5", playerStats.get("Rebounds"), "Rebounds must be 1.5");
        assertEquals("6.5", playerStats.get("Assists"), "Assists must be 6.5");
        assertEquals("40.0", playerStats.get("FG"), "Field Goal must be 40.0");
        assertEquals("50.0", playerStats.get("FT"), "Free Throws must be 50.0");
        assertEquals("57.1", playerStats.get("3pt"), "3-Points must be 57.1");
        assertEquals("1.5", playerStats.get("Blocks"), "Blocks must be 1.5");
        assertEquals("3.5", playerStats.get("Steals"), "Steals must be 3.5");
        assertEquals("2.5", playerStats.get("Turnovers"), "Turnovers must be 2.5");
    }
}

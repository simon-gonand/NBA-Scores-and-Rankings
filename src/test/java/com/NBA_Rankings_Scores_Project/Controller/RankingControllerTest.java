package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.RankingController;
import com.NBA_Rankings_Scores_Project.Models.Conference;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankingControllerTest {
    private static TreeSeasonInfo info;
    private static TreeGames games;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        info = parser.getTreeSeason();
        games = parser.getTreeSeasonGames(info);
    }

    @Test
    public void quickSortRankingTest(){
        Conference west = info.getConferences().get(0);
        Conference east = info.getConferences().get(1);
        RankingController controller = new RankingController(info, games);
        ArrayList<TeamModel> rankingWest = controller.sortRanking(west);
        ArrayList<TeamModel> rankingEast = controller.sortRanking(east);

        assertEquals("Dallas Mavericks", rankingWest.get(0).getName(), "1st west team must be Dallas Mavericks");
        assertEquals("Los Angeles Clippers", rankingWest.get(1).getName(), "2nd west team must be Los Angeles Clippers");
        assertEquals("New Orleans Pelicans", rankingEast.get(0).getName(), "1st east team must be New Orleans Pelicans");
        assertEquals("Milwaukee Bucks", rankingEast.get(1).getName(), "2nd east team must be Los Angeles Clippers");
    }
}

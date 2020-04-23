package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Tree.TreeGames;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;

public class RankingController {
    private TreeSeasonInfo info;
    private TreeGames games;

    public RankingController(TreeSeasonInfo info, TreeGames games){
        this.info = info;
        this.games = games;
    }
}

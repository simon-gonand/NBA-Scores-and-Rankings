package com.NBA_Rankings_Scores_Project.Controllers;

import com.NBA_Rankings_Scores_Project.Models.GameModel;
import com.NBA_Rankings_Scores_Project.Models.PlayerStats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller of a game that makes the calculation in order to display them in a the view
 */
public class GameController {
    private GameModel game;

    /**
     * Controller that initialize the GameModel
     * @param game GameModel of the game
     * @see GameModel
     */
    public GameController(GameModel game){
        this.game = game;
    }

    /**
     * To calculate the whole team statistics of this game
     * @param playerStatsList List of the players statistics of the team
     * @return Map with the statistics of the team
     */
    public Map<String, Object> calculateTeamStats(List<PlayerStats> playerStatsList){
        Map<String, Object> teamStats = new HashMap<String, Object>();
        int points = 0, rebounds = 0, assists = 0, steals = 0, blocks = 0, turnovers = 0, ftMade = 0, ftAttempts = 0, fgMade = 0, fgAttempts = 0;
        int threePtMade = 0, threePtAttempts = 0;
        for (PlayerStats p : playerStatsList){
            if (!p.getMinutes().equals(0)) {
                points += p.getPoints();
                rebounds += p.getRebounds();
                assists += p.getAssists();
                steals += p.getSteals();
                blocks += p.getBlocks();
                turnovers += p.getTurnovers();
                List<Integer> madeAttempts = getMadeAttempts(p.getFt());
                ftMade += madeAttempts.get(0);
                ftAttempts += madeAttempts.get(1);
                madeAttempts = getMadeAttempts(p.getFg());
                fgMade += madeAttempts.get(0);
                fgAttempts += madeAttempts.get(1);
                madeAttempts = getMadeAttempts(p.getThreePts());
                threePtMade += madeAttempts.get(0);
                threePtAttempts += madeAttempts.get(1);
            }
        }
        teamStats.put("Points", points);
        teamStats.put("Rebounds", rebounds);
        teamStats.put("Assists", assists);
        teamStats.put("Steals", steals);
        teamStats.put("Blocks", blocks);
        teamStats.put("Turnovers", turnovers);
        teamStats.put("FT", ftMade + "/" + ftAttempts);
        String[] str = Float.toString(calculatePercentage(fgMade, fgAttempts)).split("\\.");
        String res = str[1].length() < 2 ? str[0]+"."+str[1].substring(0,str[1].length()) : str[0]+"."+str[1].substring(0,2);
        teamStats.put("FG", res);
        str = Float.toString(calculatePercentage(threePtMade,threePtAttempts)).split("\\.");
        res = str[1].length() < 2 ? str[0]+"."+str[1].substring(0,str[1].length()) : str[0]+"."+str[1].substring(0,2);
        teamStats.put("3pt", res);
        return teamStats;
    }

    /**
     * Split a String from "Made/Attempts" to two Integer
     * @param strMadeAttempts String of the Made/Attempts
     * @return List of two Integer one is the shoot made and the other is the shoot attempted
     */
    private List<Integer> getMadeAttempts(String strMadeAttempts){
        List<Integer> madeAttempts = new ArrayList<Integer>(2);
        String[] madeAndAttemptsStr = strMadeAttempts.split("/");
        madeAttempts.add(Integer.parseInt(madeAndAttemptsStr[0]));
        madeAttempts.add(Integer.parseInt(madeAndAttemptsStr[1]));
        return madeAttempts;
    }

    /**
     * Calculate a percentage
     * @param made What made (succeed)
     * @param attempts what attempted
     * @return the Percentage of Made/Attempts
     */
    private float calculatePercentage(float made, float attempts){
        return made*100/attempts;
    }
}

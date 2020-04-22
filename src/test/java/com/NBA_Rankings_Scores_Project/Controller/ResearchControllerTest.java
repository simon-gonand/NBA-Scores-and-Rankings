package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.ResearchControllers;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Parser;
import com.NBA_Rankings_Scores_Project.Tree.TreeSeasonInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResearchControllerTest {
    private static TreeSeasonInfo treeSeasonInfo;

    @BeforeAll
    public static void setup(){
        Parser parser = new Parser("src/test/resources/Test_Datas.xml");
        treeSeasonInfo = parser.getTreeSeason();
    }

    @Test
    public void ResearchNameTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("Barea", "NoNe", "","");
        assertEquals("J.J. Barea", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be J.J. Barea");
    }

    @Test
    public void ResearchNationalityTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "none", "","canadian");
        assertEquals("Nickeil Alexander-Walker", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Nickeil Alexander-Walker");
    }

    @Test
    public void ResearchTeamTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "NONE", "Bucks","");
        assertEquals("Giannis Antetokounmpo", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Giannis Antetokounmpo");
        results = researchControllers.doPlayerSearch("", "none", "Los Angeles","");
        assertEquals("Patrick Beverley", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Patrick Beverley");
        results = researchControllers.doPlayerSearch("", "nONe", "dAllas MaveRicks","");
        assertEquals("J.J. Barea", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be J.J. Barea");
    }

    @Test
    public void ResearchPostTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "Power_Forward", "","");
        assertEquals("Giannis Antetokounmpo", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Giannis Antetokounmpo");
    }

    @Test
    public void ResearchWithSeveralParameters(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "Center", "","canadian");
        assertEquals("Nickeil Alexander-Walker", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Nickeil Alexander-Walker");
        results = researchControllers.doPlayerSearch("", "Center", "New Orleans","canadian");
        assertEquals("Nickeil Alexander-Walker", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Nickeil Alexander-Walker");
        results = researchControllers.doPlayerSearch("", "none", "New Orleans","canadian");
        assertEquals("Nickeil Alexander-Walker", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Nickeil Alexander-Walker");
        results = researchControllers.doPlayerSearch("Alexander-walKer", "Center", "New Orleans","canadian");
        assertEquals("Nickeil Alexander-Walker", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Nickeil Alexander-Walker");
    }
}

package com.NBA_Rankings_Scores_Project.Controller;

import com.NBA_Rankings_Scores_Project.Controllers.ResearchControllers;
import com.NBA_Rankings_Scores_Project.Models.PlayerModel;
import com.NBA_Rankings_Scores_Project.Models.TeamModel;
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
    public void ResearchPlayerNameTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("Barea", "NoNe", "","");
        assertEquals("J.J. Barea", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be J.J. Barea");
    }

    @Test
    public void ResearchPlayerNationalityTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "none", "","canadian");
        assertEquals("Nickeil Alexander-Walker", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Nickeil Alexander-Walker");
    }

    @Test
    public void ResearchPlayerTeamTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "NONE", "Bucks","");
        assertEquals("Giannis Antetokounmpo", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Giannis Antetokounmpo");
        results = researchControllers.doPlayerSearch("", "none", "Los Angeles","");
        assertEquals("Patrick Beverley", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Patrick Beverley");
        results = researchControllers.doPlayerSearch("", "nONe", "dAllas MaveRicks","");
        assertEquals("J.J. Barea", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be J.J. Barea");
    }

    @Test
    public void ResearchPlayerPostTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<PlayerModel> results = researchControllers.doPlayerSearch("", "Power_Forward", "","");
        assertEquals("Giannis Antetokounmpo", results.get(0).getName() + " " + results.get(0).getSurname(), "Player must be Giannis Antetokounmpo");
    }

    @Test
    public void ResearchPlayerWithSeveralParameters(){
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

    @Test
    public void ResearchTeamNameTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<TeamModel> results = researchControllers.doTeamSearch("Los Angeles","none");
        assertEquals("Los Angeles Clippers", results.get(0).getName(), "Team must be Los Angeles Clippers");
        results = researchControllers.doTeamSearch("MAVeriCKs","none");
        assertEquals("Dallas Mavericks", results.get(0).getName(), "Team must be Dallas Mavericks");
        results = researchControllers.doTeamSearch("New Orleans Pelicans","none");
        assertEquals("New Orleans Pelicans", results.get(0).getName(), "Team must be New Orleans Pelicans");
    }

    @Test
    public void ResearchTeamConferenceTest(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<TeamModel> results = researchControllers.doTeamSearch("","west");
        assertEquals("Dallas Mavericks", results.get(0).getName(), "Team must be Dallas Mavericks");
        assertEquals("Los Angeles Clippers", results.get(1).getName(), "Team must be Los Angeles Clippers");
    }

    @Test
    public void ResearchTeamWithSeveralParameters(){
        ResearchControllers researchControllers = new ResearchControllers(treeSeasonInfo);
        ArrayList<TeamModel> results = researchControllers.doTeamSearch("Milwaukee Bucks","east");
        assertEquals("Milwaukee Bucks", results.get(0).getName(), "Team must be Milwaukee Bucks");
    }
}

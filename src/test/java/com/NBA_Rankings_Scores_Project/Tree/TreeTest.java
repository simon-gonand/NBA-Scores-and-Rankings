package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Models;
import com.NBA_Rankings_Scores_Project.Models.Season;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeTest {
    private static Tree tree;

    @BeforeAll
    public static void setup(){
        tree = new Tree(new Season("testSeason"));
    }

    @Test
    public void getRootTest(){
        assertEquals("testSeason", tree.getRoot().getData().getName(), "Season must be testSeason");
    }

    @Test
    public void addTest(){
        tree.add(tree.getRoot(), new Models("model test"));
        assertEquals("model test",
                tree.getRoot().getChildren().get(0).getData().getName(),
                "Model must be \"model test\"");
    }
}

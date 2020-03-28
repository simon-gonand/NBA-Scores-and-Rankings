package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Models;
import com.NBA_Rankings_Scores_Project.Models.Season;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeNodeTest {
    private static TreeNode node;

    @BeforeEach
    public void setup(){
        node = new TreeNode(new Models("tree node test"));
    }

    @Test
    public void getDataTest(){
        assertEquals("tree node test",
                node.getData().getName(),
                "node name must be \"tree node test\"");
    }

    @Test
    public void setDataTest(){
        node.setData(new Models("set data test"));
        assertEquals("set data test",
                node.getData().getName(),
                "node name must be \"set data test\"");
    }

    @Test
    public void childsTest(){
        node.addChilds(new TreeNode(new Models("child node test")));
        List<TreeNode> child = node.getChilds();
        assertEquals("child node test",
                child.get(0).getData().getName(),
                "child node name must be \"child node test\"");
    }
}

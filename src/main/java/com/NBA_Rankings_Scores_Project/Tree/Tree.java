package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.*;

public class Tree {
    private TreeNode root;

    public Tree(Season season) {
        root = new TreeNode (season);
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode add(TreeNode node, Models val){
        TreeNode tmp = new TreeNode(val);
        node.addChilds(tmp);
        return tmp;
    }
}

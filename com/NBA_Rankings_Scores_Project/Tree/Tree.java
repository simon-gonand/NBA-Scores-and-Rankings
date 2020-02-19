package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Models;
import com.NBA_Rankings_Scores_Project.Models.Season;

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

    private void localShow(TreeNode node) {
        if (node.equals(null)) return;
        for (TreeNode subNode : node.getChilds()){
            System.out.println("Node : " + subNode.getData().getName());
            localShow(subNode);
        }
    }

    public void show(){
        localShow(root);
    }
}

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

    private void localShow(TreeNode node) {
        if (node.equals(null)) return;
        for (TreeNode subNode : node.getChilds()){
            if (subNode.getData().getClass().equals(Conference.class)) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Conference : " + subNode.getData().getName());
            }

            if (subNode.getData().getClass().equals(TeamModel.class)) {
                System.out.println("-------------------------------");
                System.out.println("Team : " + subNode.getData().getName());
                System.out.println("----------------");
            }

            if (subNode.getData().getClass().equals(PlayerModel.class)) {
                System.out.println("Player : " + subNode.getData().getName());
            }
            localShow(subNode);
        }
    }

    public void show(){
        System.out.println("Season : " + this.root.getData().getName());
        localShow(this.root);
    }
}

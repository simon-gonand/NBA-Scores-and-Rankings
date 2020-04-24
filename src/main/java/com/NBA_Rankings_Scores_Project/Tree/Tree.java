package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.*;

/**
 * Tree class parent which implement a root and the add function
 */
public class Tree {
    private TreeNode root;

    /**
     * Constructor that create the tree with the root
     * @param season Season which is the Tree root
     * @see TreeNode
     * @see Season
     */
    public Tree(Season season) {
        root = new TreeNode (season);
    }

    /**
     * Getter of the Tree root
     * @return TreeNode of the root
     * @see TreeNode
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * To add a TreeNode
     * @param node TreeNode which will receive the new node as child
     * @param val Value of the new TreeNode
     * @return the new tree node that has been added
     * @see TreeNode
     * @see Models
     */
    public TreeNode add(TreeNode node, Models val){
        TreeNode tmp = new TreeNode(val);
        node.addChild(tmp);
        return tmp;
    }
}

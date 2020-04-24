package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * node that tree will contains
 * @see Tree
 */
public class TreeNode {
    private Models data;
    private List<TreeNode> children;

    /**
     * Constructor that initialize the value of the node and the list of the children
     * @param val value of the node
     * @see Models
     */
    public TreeNode(Models val){
        data = val;
        children = new ArrayList<TreeNode>();
    }

    /**
     * Getter of the data
     * @return the value of the TreeNode
     * @see Models
     */
    public Models getData() {
        return data;
    }

    /**
     * Getter for the children
     * @return the list of the children
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * Setter of the node data
     * @param data Models of the new data
     * @see Models
     */
    public void setData(Models data) {
        this.data = data;
    }

    /**
     * Add a child to the children list
     * @param child TreeNode new node to the list
     */
    public void addChild(TreeNode child) {
        this.children.add(child);
    }
}

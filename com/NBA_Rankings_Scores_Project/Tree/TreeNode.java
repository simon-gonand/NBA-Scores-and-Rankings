package com.NBA_Rankings_Scores_Project.Tree;

import com.NBA_Rankings_Scores_Project.Models.Models;

import java.util.List;

public class TreeNode {
    private Models data;
    private List<TreeNode> childs;

    public TreeNode(Models val){
        data = val;
    }

    public Models getData() {
        return data;
    }

    public List<TreeNode> getChilds() {
        return childs;
    }

    public void setData(Models data) {
        this.data = data;
    }

    public void addChilds(TreeNode child) {
        this.childs.add(child);
    }
}

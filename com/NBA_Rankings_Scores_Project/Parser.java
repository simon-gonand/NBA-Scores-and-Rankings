package com.NBA_Rankings_Scores_Project;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Parser {
    public Parser (){
        try {
            File inputFile = new File ("src/Datas/Season_19_20.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            java.lang.System.out.println("Root element : " + doc.getDocumentElement().getNodeName() + " " + doc.getDocumentElement().getAttribute("name"));
            NodeList nodeList = doc.getElementsByTagName("Conference");
            java.lang.System.out.println("-------------------------------");

            for (int i = 0; i < nodeList.getLength(); ++i){
                Node node = nodeList.item(i);
                java.lang.System.out.println("Current : " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    java.lang.System.out.println("name : " + element.getAttribute("name"));
                    NodeList subNodeList = element.getElementsByTagName("Team");
                    for (int j = 0; j < subNodeList.getLength(); ++j){
                        Node subNode = subNodeList.item(j);
                        java.lang.System.out.println("----------------");
                        if (subNode.getNodeType() == Node.ELEMENT_NODE){
                            Element subElement = (Element) subNode;
                            java.lang.System.out.println(subElement.getNodeName() + " " + j + " : " + subElement.getAttribute("name"));
                            java.lang.System.out.println("Head coach : " + subElement.getAttribute("headCoach"));
                        }
                    }
                }
                java.lang.System.out.println("-------------------------------");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

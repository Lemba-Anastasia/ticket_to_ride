package Controllers;

import ElementsOfGraph.Edge;
import ElementsOfGraph.GraphOfCities;
import ElementsOfGraph.Node;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lemba on 12.09.2018.
 */
public class Controller {
    private GraphOfCities graphOfCities;
    private WorkWithFile workWithFile;
    private Deque<Node> queueNodeOfIncidentANode;
    private Node startPunkt;
    private int budget = 100;
    private int poinsForDoingRoad = 0;
    private Node endNodeRoute;
    private Node punktB;
    private View view;
    private String mission;

    public Controller() {
        workWithFile = new WorkWithFile();
        graphOfCities = workWithFile.createGraph();
        queueNodeOfIncidentANode = new LinkedList<>();
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<Point> getNodePoitsList() {
        java.util.List<Point> pointList = new ArrayList<>();
        for (Node n : graphOfCities.getNodeList()) {
            pointList.add(n.getPoint());
        }
        return pointList;
    }

    public List<Edge> getEdgeList() {
        return graphOfCities.getEdgeList();
    }

    public List<Node> getNodeList() {
        return graphOfCities.getNodeList();
    }

    public void activateIntidentNode() {
        Node n = queueNodeOfIncidentANode.getFirst();
        n.setActive(true);
        for (Node node : queueNodeOfIncidentANode) {
            if (node != n) node.setActive(false);
        }
        queueNodeOfIncidentANode.removeFirst();
        queueNodeOfIncidentANode.add(n);
        punktB = n;
    }

    public void updateQueueOfIntidentNodes() {
        startPunkt.setActive(true);
        for (Edge edge : graphOfCities.getEdgeList()) {
            if (edge.containNode(startPunkt)) {
                queueNodeOfIncidentANode.add(edge.getAnotherNode(startPunkt));
            }
        }
    }

    public void buidRailWay() {
        int costForASegment = 5;
        if (!graphOfCities.searchEdgeByNodes(startPunkt, punktB).isBuilt()) {
            if (budget >= costForASegment) {
                searchEdgeByNodes(startPunkt, punktB).setBuilt(true);
                budget -= costForASegment;
                poinsForDoingRoad += 10;
                view.updaitInfoOfGame();
                if (endNodeRoute == punktB) {
                    poinsForDoingRoad += 20;
                    endNodeRoute.setActive(false);
                    JOptionPane.showInputDialog("Миссия выполнена!");//FIXME убрать
                    view.updaitInfoOfGame();
                    view.updateMission(mission);
                }
                recourseOfTheGame(punktB);

            } else {
                ///TODO: game over
            }
        } else {
            recourseOfTheGame(punktB);
        }
    }

    private void recourseOfTheGame(Node punktB) {
        startPunkt = punktB;
        queueNodeOfIncidentANode.clear();
        updateQueueOfIntidentNodes();
    }

    public Node getNodeByName(String name) {
        for (Node node : graphOfCities.getNodeList()) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public Node getStartPunkt() {
        return startPunkt;
    }

    public void setStartPunkt(Node startPunkt) {
        this.startPunkt = startPunkt;
        updateQueueOfIntidentNodes();

    }

    public Edge searchEdgeByNodes(Node a, Node b) {
        return graphOfCities.searchEdgeByNodes(a, b);
    }

    public Node searchNodeByName(String s) {
        for (Node n : getNodeList()) {
            if (n.getName().equals(s)) return n;
        }
        return null;
    }

    public int getBudget() {
        return budget;
    }

    public void setEndNodeRoute(Node endNodeRoute) {
        this.endNodeRoute = endNodeRoute;
    }

    public int getPoinsForDoingRoad() {
        return poinsForDoingRoad;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }
}

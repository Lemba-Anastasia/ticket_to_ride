package Controllers;

import ElementsOfGraph.Edge;
import ElementsOfGraph.GraphOfCities;
import ElementsOfGraph.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lemba on 12.09.2018.
 */
public class Controller {
    private GraphOfCities graphOfCities;
    private Deque<Node> queueNodeOfIncidentANode;
    private int budget = 100;
    private int poinsForDoingRoad = 0;

    public Controller() {
        WorkWithFile workWithFile = new WorkWithFile();
        graphOfCities = workWithFile.createGraph();
        queueNodeOfIncidentANode = new LinkedList<>();
    }

    public Node getNodeByName(String name) {
        for (Node node : graphOfCities.getNodeList()) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void doActivaitInzidentNode(String nameOfNode) {
        Node inzidentNode = getNodeByName(nameOfNode);
        inzidentNode.setActive(true);
        for (Node node : queueNodeOfIncidentANode) {
            if (node != inzidentNode) node.setActive(false);
        }
        queueNodeOfIncidentANode.removeFirst();
        queueNodeOfIncidentANode.add(inzidentNode);
    }

    public void updaitGraphForActivationNode(String nName, String startPunktName) {
        Node n = getNodeByName(nName);
        Node startPunkt = getNodeByName(startPunktName);
        startPunkt.setActive(true);
        for (Edge edge : graphOfCities.getEdgeList()) {
            if (edge.containNode(startPunkt)) {
                queueNodeOfIncidentANode.add(edge.getAnotherNode(startPunkt));
            }
        }
    }

    public void clearNodesOfIncidentNode() {
        queueNodeOfIncidentANode.clear();
    }

    public List<Edge> getEdgeList() {
        return graphOfCities.getEdgeList();
    }

    public List<Node> getNodeList() {
        return graphOfCities.getNodeList();
    }

    public Edge searchEdgeByNodes(Node a, Node b) {
        return graphOfCities.searchEdgeByNodes(a, b);
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getPoinsForDoingRoad() {
        return poinsForDoingRoad;
    }

    public void setPoinsForDoingRoad(int poinsForDoingRoad) {
        this.poinsForDoingRoad = poinsForDoingRoad;
    }

    public void buildRailWay(String startPunkt, String punkrB) {
        Node nodeA = getNodeByName(startPunkt);
        Node nodeB = getNodeByName(punkrB);
        searchEdgeByNodes(nodeA, nodeB).setBuilt(true);
    }
}

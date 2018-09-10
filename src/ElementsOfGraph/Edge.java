package ElementsOfGraph;

/**
 * Created by Lemba on 10.09.2018.
 */
public class Edge {
    private Node firstNode;
    private Node secondNode;
    private boolean built = false;


    public Edge(Node node1, Node node2) {
        this.firstNode = node1;
        this.secondNode = node2;
    }

    public boolean isBuilt() {
        return built;
    }

    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }

}

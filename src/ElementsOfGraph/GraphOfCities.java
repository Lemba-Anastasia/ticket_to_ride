package ElementsOfGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lemba on 10.09.2018.
 */
public class GraphOfCities {
    private List<Node> nodeList = new ArrayList<Node>();
    private List<Edge> edgeList = new ArrayList<Edge>();

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    public void addNode(Node node) {
        nodeList.add(node);
    }

    public Node getNodeByName(String name) {
        for (Node n : nodeList) {
            if (n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }

    public void createEdgeBehindTwoNodeWithId(String nameBeginNode, String nameEndNode) {
        Node nodeBegin = null;
        Node nodeEnd = null;
        for (Node node : nodeList) {
            if (node.getName() == nameBeginNode)
                nodeBegin = node;
            else if (node.getName() == nameEndNode)
                nodeEnd = node;
        }
        addEdge(new Edge(nodeBegin, nodeEnd));
    }

    public void deleteOllElements() {
        Iterator<Node> nodeIterator = nodeList.iterator();
        while (nodeIterator.hasNext()) {
            nodeIterator.next();
            nodeIterator.remove();
        }
        Iterator<Edge> edgeIterator = edgeList.iterator();
        while (edgeIterator.hasNext()) {
            edgeIterator.next();
            edgeIterator.remove();
        }
    }

    public Edge searchEdgeByNodes(Node punktA, Node punktB) {
        for (Edge edge: edgeList){
            if(edge.containNode(punktA)&&edge.containNode(punktB))
                return edge;
        }
        return null;
    }
}

package Propagation;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Functions {

    public static Set<Node> nodesSet(Graph graph) {
        Set<Node> nodeSet = new HashSet<>();
        for (Node node : graph) {
            nodeSet.add(node);
        }

        return nodeSet;
    }

    private static Collection<Node> nodesCollection(Graph graph) {
        Collection<Node> nodeCollection = new HashSet<>();
        for (Node node : graph) {
            nodeCollection.add(node);
        }
        return nodeCollection;
    }
}

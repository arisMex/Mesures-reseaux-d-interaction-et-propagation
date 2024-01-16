package Propagation;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static Propagation.Functions.nodesSet;

public class Scenario1 implements InterfaceScenario{

    private PropaSis sis;
    private Collection<Node> nodes;

    public Scenario1(Graph graph) {
        Collection<Node> susceptibleNodes = this.nodes = nodesSet(graph);
        this.sis = new PropaSis(1.0 / 7.0, 1.0 / 14.0, new ArrayList<>(susceptibleNodes));
    }

    public List<Node> getNodes() {
        return new ArrayList<>(this.nodes);
    }

    @Override
    public List<Collection<Node>> propagation(int days) {
        return this.sis.propagation(days);
    }

}

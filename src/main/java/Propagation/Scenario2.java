package Propagation;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Scenario2 implements InterfaceScenario{


        private PropaSis sis;
        private Collection<Node> nodes;

        public Scenario2(Graph graph) {
            this.nodes = Toolkit.randomNodeSet(graph, graph.getNodeCount()/2);
            this.sis = new PropaSis(1.0 / 7.0, 1.0 / 14.0, graph);
        }

        public List<Node> getNodes() {
            return new ArrayList<>(this.nodes);
        }

        public List<Collection<Node>> propagation(int days) {
            return this.sis.propagation(days);
        }


}

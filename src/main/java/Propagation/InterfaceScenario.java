package Propagation;


import org.graphstream.graph.Node;

import java.util.Collection;
import java.util.List;

public interface InterfaceScenario {
    List<Collection<Node>> propagation(int days);
}

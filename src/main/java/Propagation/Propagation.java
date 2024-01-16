package Propagation;

import org.graphstream.graph.Node;

import java.util.*;
import java.util.stream.Stream;


public class Propagation implements InterfaceScenario {


        private static final Random RANDOM = new Random();

        private double beta, mu;
        private Set<Node> susceptibleNodes;
        private Set<Node> infectedNodes;

        Propagation(double beta, double mu, List<Node> susceptibleNodes) {
            this.beta = beta;
            this.mu = mu;
            this.susceptibleNodes = new HashSet<>(susceptibleNodes); ;
            infectedNodes =  new HashSet<>();
            infection(susceptibleNodes.get(RANDOM.nextInt(this.susceptibleNodes.size())));

        }

        public List<Collection<Node>> propagation(int days) {
            List<Collection<Node>> result = new ArrayList<>(days);
            for (int i = 0; i < days; i++)
                result.add(nextDay());
            result.add(new ArrayList<>(this.infectedNodes));
            return result;
        }

        private Collection<Node> nextDay() {
            Collection<Node> infectedNodes = new ArrayList<>(this.infectedNodes);
            infectedNodes.forEach(this::sendMail);
            return infectedNodes;
        }

        private void sendMail(Node node) {
            Stream<Node> nodeStrm = node.neighborNodes();
            Iterator<Node> uIte = nodeStrm.iterator();

            while (uIte.hasNext()) {
                Node u = uIte.next();
                if (this.susceptibleNodes.contains(u)) {
                    if (Math.random() <= this.beta) {
                        infection(u);
                    }
                }
            }
            if (this.infectedNodes.contains(node)) {
                if (Math.random() < this.mu) {
                    remission(node);
                }
            }
        }


        private void infection(Node node) {
            this.infectedNodes.add(node);
            this.susceptibleNodes.remove(node);
        }

        private void remission(Node node) {
            this.infectedNodes.remove(node);
            this.susceptibleNodes.add(node);
        }
    }



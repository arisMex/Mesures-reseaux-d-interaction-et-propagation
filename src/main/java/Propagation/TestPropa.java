package Propagation;

import Mesure.GivenGraph;
import Mesure.RandomGraph;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static Propagation.Functions.*;


public class TestPropa {


    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();

        Graph g = (new GivenGraph()).getGraph();
        Graph gR  = (new RandomGraph()).getGraph();
        exec(g, 1);
        scenariosGenererFichierPLT(1);
        exec(gR, 2);
        scenariosGenererFichierPLT(2);
        //exec(GraphUtils.readResource("Preferential", "preferential-graph.txt", new FileSourceDGS()));
        long end = System.currentTimeMillis();
        System.out.printf("%nExec in %d s%n", (end - begin) / 1000);
    }

    public static void exec(Graph g, int id) throws InterruptedException {
        System.out.printf("%n%s%n%n", g.getId());
        double averageDegree = Toolkit.averageDegree(g);
        System.out.printf("<k>  = %f%n", averageDegree);

        float beta = 1F / 7;
        float mu = 1F / 14;
        System.out.printf("<k²> = %f%n", degreeVariance(g));
        System.out.printf("λ    = %f%n", propagationRate(beta, mu));

        int days = 3 * 4 * 7;
        Scenario1 s1 = new Scenario1(g);
        Scenario2 s2 = new Scenario2(g);
        Scenario3 s3 = new Scenario3(g);

        System.out.println("--------- Simulation ---------");
        Thread thread1 = runScenario(s1, days, res -> {
            double prc = res.get(res.size() - 1).size() / (double) g.getNodeCount();
            System.out.printf("Scénario %d : %7.4f%%%n", 1, 100 * prc);
            try {
                infectionDistribToFile( res, "G"+id+"_S1.dat");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //writeInfectedDistribution(g, res, 1);
        });
        Thread thread2 = runScenario(s2, days, res -> {
            double prc = res.get(res.size() - 1).size() / (double) g.getNodeCount();
            System.out.printf("Scénario %d : %7.4f%%%n", 2, 100 * prc);
            //writeInfectedDistribution(g, res, 2);
            try {
                infectionDistribToFile( res, "G"+id+"_S2.dat");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = runScenario(s3, days, res -> {
            double prc = res.get(res.size() - 1).size() / (double) g.getNodeCount();
            System.out.printf("Scénario %d : %7.4f%%%n", 3, 100 * prc);
            //writeInfectedDistribution(g, res, 3);
            try {
                infectionDistribToFile( res, "G"+id+"_S3.dat");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.join();
        thread2.join();
        thread3.join();


        System.out.println("------------------------------");



        double epidemicSeuil1 = epidemicSeuil(s1.getNodes());
        double epidemicSeuil2 = epidemicSeuil(s2.getNodes());
        double epidemicSeuil3 = epidemicSeuil(s3.getNodes());



        System.out.printf("λc 1 = %f%n", epidemicSeuil1);
        System.out.printf("λc 2 = %f%n", epidemicSeuil2);
        System.out.printf("λc 3 = %f%n", epidemicSeuil3);



    }

    public static Thread runScenario(InterfaceScenario s, int days, Consumer<List<Collection<Node>>> callback) {
        Thread thread = new Thread(() -> callback.accept(s.propagation(days)));
        thread.start();
        return thread;
    }

    public static double averageDegree(Collection<Node> nodes) {
        int sum = 0;
        for (Node n : nodes)
            sum += n.getDegree();
        return (double) sum / nodes.size();
    }

    public static float propagationRate(float beta, float mu) {
        return beta / mu;
    }

    public static double degreeVariance(Graph g) {
        return degreeVariance(nodesSet(g));
    }

    public static double degreeVariance(Collection<Node> nodes) {
        int sum = 0;
        for (Node n : nodes)
            sum += Math.pow(n.getDegree(), 2);
        return sum / (double) nodes.size();
    }

    public static double epidemicSeuil(Collection<Node> nodes) {
        return epidemicSeuil(averageDegree(nodes), degreeVariance(nodes));
    }

    public static double epidemicSeuil(double averageDegree, double degreeVariance) {
        return averageDegree / degreeVariance;
    }

    public static float randomEpidemicSeuil(double averageDegree) {
        return (float) (1 / (averageDegree + 1)); //
    }









}

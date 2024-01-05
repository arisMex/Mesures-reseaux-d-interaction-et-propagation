package Mesure;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;

import static org.graphstream.algorithm.Toolkit.*;
import static org.graphstream.algorithm.Toolkit.isConnected;

public class RandomGraph extends GraphClass {

    public static void main(String[] args) throws IOException {

        int nbNodes = 317080;
        double degreMoyen = 6.62208890914917;
        // Graphe Random
        System.out.println("\n Graphe Random : ");
        Graph graphR = new SingleGraph("Random");
        Generator gen = new RandomGenerator(degreMoyen);
        gen.addSink(graphR);
        gen.begin();
        for(int i=0; i<nbNodes; i++)
            gen.nextEvents();
        gen.end();
        System.out.println("2. Quelques mesures : ");
        System.out.println("* Nombre de noeuds : " + nbNodes);
        int nbEdgesR = graphR.getEdgeCount();
        System.out.println("* Nombre d'arêtes : " + nbEdgesR);
        Double degreMoyenR = averageDegree(graphR);
        System.out.println("* Degré Moyen = " + degreMoyenR);
        System.out.println("* Densité = " + density(graphR));
        System.out.println("* Coefficient de Clustering Moyen = " + averageClusteringCoefficient(graphR));
        System.out.println("\n");
        System.out.println("3.Connexité : ");
        System.out.println((isConnected(graphR))?"Ce graphe connexe ✅ "  :"Ce graphe n'est pas connexe ❌");
        System.out.println((degreMoyen > Math.log(nbNodes)?"<k> > ln(N)":"<k> < ln(N) !"));

        String filenameR = "distributionDegres_Random.dat";
        distributionDegres(graphR, filenameR );
        String  pltFileNameR = "target/classes/distribRandom.plt";
        String  pltFileName2R = "target/classes/distrib_Random_loglog.plt";
        String graphNameR= "distribsRandom.png";
        String graphName2R = "distribs_Random_loglog.png";
        //graphe linéaire
        genererFichierPLT( pltFileNameR, filenameR, graphNameR, "linéaire");
        //graphe log-log
        genererFichierPLT( pltFileName2R, filenameR, graphName2R, "log-log");
        //genererGraphe(pltFileName);
    }
}

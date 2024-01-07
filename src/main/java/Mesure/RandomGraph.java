package Mesure;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.graphstream.algorithm.Toolkit.*;

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

        String filenameR = "Random_distributionDegres.dat";
        distributionDegres(graphR, filenameR );
        String  pltFileNameR = "Random_distribRandom.plt";
        String  pltFileName2R = "Random_distrib_Random_loglog.plt";
        String graphNameR= "Random_distribsDegre";
        String graphName2R = "Random_distribs_Degre_loglog";
        //graphe linéaire
        genererFichierPLT( pltFileNameR, filenameR, graphNameR, "linéaire");
        //graphe log-log
        genererFichierPLT( pltFileName2R, filenameR, graphName2R, "log-log");
        genererGraphe(pltFileNameR);
        genererGraphe(pltFileName2R);



        //* 5

        //* 5.
        int n = 1000;

        List<Node> echantillon =getEchantillon(graphR, n);
        HashMap<Integer, Double> distrDist = distanceDistribution(echantillon, n);
        double averageDistance = AVGdist( echantillon,  n);
        //double averageDistance = 6.789172899583702;
        System.out.println("\n 5. Distance Moyenne :" + averageDistance);
        System.out.println("⟨d⟩=lnN/ln⟨k⟩=ln("+nbNodes+")/ln("+degreMoyen+") ≈ "+Math.log(nbNodes)/Math.log(degreMoyen));
        System.out.println("Distance moyenne estimée : " + averageDistance);
        System.out.println("On a la Distance moyenne = "+averageDistance+ ((averageDistance>6)?" > 6  \n\t=> L'hypothèse des six degrés de séparation se confirme  \n\t=> le réseau n'est pas un réseau petit monde car il ne remplit pas la condition de Six degrés de séparation "     :   "petit Monde ✅"));


        String  pltFileName3 = "Random_distribDist.plt";
        String graphName3 = "Random_distribDist";
        String filename3 = "Random_distributionDist.dat";
        distributionDistancesToFile(distrDist, "Random_distributionDist.dat");
        //graphe linéaire
        genererFichierPLT( pltFileName3, filename3, graphName3, "linéaire");
        genererGraphe(pltFileName3);






    }
}

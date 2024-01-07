package Mesure;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.graphstream.algorithm.Toolkit.*;

public class BarabasiGraph extends GraphClass {

    public static void main(String[] args) throws IOException {

        int nbNodes = 317080;
        int degreMoyen = (int)6.62208890914917;
        System.out.println(degreMoyen);

        // Graphe Barabàsi-Albert
        System.out.println("Graph generation ...");
        Graph graphB = new SingleGraph("Barabàsi-Albert");
        Generator gen = new BarabasiAlbertGenerator((int) Math.round(degreMoyen));
        gen.addSink(graphB);
        gen.begin();
        for(int i=0; i<nbNodes; i++) {
            gen.nextEvents();
            System.out.println("/"+i);
        }
        System.out.println("Done .");


        System.out.println("2. Quelques mesures : ");
        System.out.println("* Nombre de noeuds : " + nbNodes);
        int nbEdgesR = graphB.getEdgeCount();
        System.out.println("* Nombre d'arêtes : " + nbEdgesR);
        Double degreMoyenR = averageDegree(graphB);
        System.out.println("* Degré Moyen = " + degreMoyenR);
        System.out.println("* Densité = " + density(graphB));
        System.out.println("* Coefficient de Clustering Moyen = " + averageClusteringCoefficient(graphB));
        System.out.println("\n");
        System.out.println("3.Connexité : ");
        System.out.println((isConnected(graphB))?"Ce graphe connexe ✅ "  :"Ce graphe n'est pas connexe ❌");
        System.out.println((degreMoyen > Math.log(nbNodes)?"<k> > ln(N)":"<k> < ln(N) !"));

        String filenameR = "Barabasi_distributionDegres.dat";
        distributionDegres(graphB, filenameR );
        String  pltFileNameR = "Barabasi_distribDregres.plt";
        String  pltFileName2R = "Barabasi_distribDegres_loglog.plt";
        String graphNameR= "Barabasi_distribsDegres";
        String graphName2R = "Barabasi_distribs_Degre_loglog";
        //graphe linéaire
        genererFichierPLT( pltFileNameR, filenameR, graphNameR, "linéaire");
        //graphe log-log
        genererFichierPLT( pltFileName2R, filenameR, graphName2R, "log-log");
        genererGraphe(pltFileNameR);
        genererGraphe(pltFileName2R);



        //* 5

        //* 5.
        int n = 1000;

        List<Node> echantillon =getEchantillon(graphB, n);
        HashMap<Integer, Double> distrDist = distanceDistribution(echantillon, n);
        double averageDistance = AVGdist( echantillon,  n);
        //double averageDistance = 6.789172899583702;
        System.out.println("\n 5. Distance Moyenne :" + averageDistance);
        System.out.println("⟨d⟩=lnN/ln⟨k⟩=ln("+nbNodes+")/ln("+degreMoyen+") ≈ "+Math.log(nbNodes)/Math.log(degreMoyen));
        System.out.println("Distance moyenne estimée : " + averageDistance);
        System.out.println("On a la Distance moyenne = "+averageDistance+ ((averageDistance>6)?" > 6  \n\t=> L'hypothèse des six degrés de séparation se confirme  \n\t=> le réseau n'est pas un réseau petit monde car il ne remplit pas la condition de Six degrés de séparation "     :   "petit Monde ✅"));


        String  pltFileName3 = "Barabasi_distribDist.plt";
        String graphName3 = "Barabasi_distribDist";
        String filename3 = "Barabasi_distributionDist.dat";
        distributionDistancesToFile(distrDist, "Barabasi_distributionDist.dat");
        //graphe linéaire
        genererFichierPLT( pltFileName3, filename3, graphName3, "linéaire");
        genererGraphe(pltFileName3);






    }

}
/*2. Quelques mesures :
* Nombre de noeuds : 317080
* Nombre d'arêtes : 1108550
* Degré Moyen = 6.992197513580322
* Densité = 2.2051770429243334E-5
* Coefficient de Clustering Moyen = 3.9488909484654744E-4


3.Connexité :
Ce graphe connexe ✅
<k> < ln(N) !
Loading :
(1000/1000)
(1000/1000)

 5. Distance Moyenne :5.080822774550433
⟨d⟩=lnN/ln⟨k⟩=ln(317080)/ln(6) ≈ 7.06953673441915
Distance moyenne estimée : 5.080822774550433
On a la Distance moyenne = 5.080822774550433 petit Monde ✅*/
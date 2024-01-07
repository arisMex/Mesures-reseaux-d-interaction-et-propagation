package Mesure;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.IOException;
import java.net.URL;

import static org.graphstream.algorithm.Toolkit.*;

public class GivenGraph extends GraphClass {
    public static void main(String[] args) throws IOException {

        System.setProperty("org.graphstream.ui", "swing");

        // Créer un graphe avec GraphStream
        org.graphstream.graph.Graph graph = new SingleGraph("MonGraphe");

        // Obtenez le chemin du fichier à partir des ressources
        URL resourceUrl = GraphClass.class.getResource("/this.txt");
        if (resourceUrl == null) {
            System.out.println("File not found: this.txt");
            return;
        }

        String filePath = resourceUrl.getFile();

        FileSourceEdge edgeSource = new FileSourceEdge();

        edgeSource.addSink(graph);

        try {
            edgeSource.readAll(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            edgeSource.removeSink(graph);
        }

        // 2.
        System.out.println("2. Quelques mesures : ");
        int nbNodes = graph.getNodeCount();
        System.out.println("* Nombre de noeuds : " + nbNodes);
        int nbEdges = graph.getEdgeCount();
        System.out.println("* Nombre d'arêtes : " + nbEdges);
        Double degreMoyen = averageDegree(graph);
        System.out.println("* Degré Moyen = " + degreMoyen);
        System.out.println("* Densité = " + density(graph));
        System.out.println("* Coefficient de Clustering Moyen = " + averageClusteringCoefficient(graph));
        //System.out.println("Coefficient de Clustering de tous les noeuds = " + Arrays.toString(clusteringCoefficients(graph)));
        System.out.println("* Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen sera le même \n\t Car : C = 3*DegréMoyen/tailleréseau " );

        System.out.println("\n");


        //* 3.
        System.out.println("3.Connexité : ");
        System.out.println((isConnected(graph))?"Ce graphe connexe ✅ "  :"Ce graphe n'est pas connexe ❌");
        System.out.println("Un graphe aléatoire de telle taille et tel degré moyen est connexe avec une probabilité de : "+degreMoyen/nbEdges);
        System.out.println("Un graphe aléatoire est connexe si <k> > ln(N) . ");

        //System.out.println(Arrays.toString(degreeDistribution(graph)));



        //* 4.
        String filename = "Graph1_distributionDegres.dat";
        distributionDegres(graph, filename);
        String  pltFileName = "Graph1_distrib.plt";
        String  pltFileName2 = "Graph1_distrib_loglog.plt";
        String graphName = "Graph1_distribs";String graphName2 = "Graph1_distribs_loglog";
        //graphe linéaire
        //genererFichierPLT( pltFileName, filename, graphName, "linéaire");
        //graphe log-log
        //genererFichierPLT( pltFileName2, filename, graphName2, "log-log");
        //genererGraphe(pltFileName);
        //genererGraphe(pltFileName2);


        System.out.println("Le tracé en échelle log-log de la distribution des degrés  forme  une ligne semi-droite,\n" +
                " cela dit que la distribution des degrés  suit  une loi de puissance ");



        //* 5.
        int n = 1000;
        //List<Node> echantillon =getEchantillon(graph, n);
        //HashMap<Integer, Double> distrDist = distanceDistribution(echantillon, n);
        //double averageDistance = AVGdist( echantillon,  n);
        double averageDistance = 6.789172899583702;
        System.out.println("\n 5. Distance Moyenne :" + averageDistance);
        System.out.println("⟨d⟩=lnN/ln⟨k⟩=ln("+nbNodes+")/ln("+degreMoyen+") ≈ "+Math.log(nbNodes)/Math.log(degreMoyen));
        System.out.println("Distance moyenne estimée : " + averageDistance);


        String  pltFileName3 = "Graph1_distribDist.plt";
        String graphName3 = "Graph1_distribDist";
        String filename3 = "Graph1_distributionDist.dat";
        //distributionDistancesToFile(distrDist, "Graph1_distributionDist.dat");
        //graphe linéaire
        //genererFichierPLT( pltFileName3, filename3, graphName3, "linéaire");
        //genererGraphe(pltFileName3);








    }
}

/*2. Quelques mesures :
* Nombre de noeuds : 317080
* Nombre d'arêtes : 1049866
* Degré Moyen = 6.62208890914917
* Densité = 2.0884666810161434E-5
* Coefficient de Clustering Moyen = 0.6324308280637396
* Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen sera le même
	 Car : C = 3*DegréMoyen/tailleréseau


3.Connexité :
Ce graphe connexe ✅
Un graphe aléatoire de telle taille et tel degré moyen est connexe avec une probabilité de : 6.307556306375452E-6
Un graphe aléatoire est connexe si <k> > ln(N) .
Le tracé en échelle log-log de la distribution des degrés  forme  une ligne semi-droite,
 cela dit que la distribution des degrés  suit  une loi de puissance

 5. Distance Moyenne :6.789172899583702
⟨d⟩=lnN/ln⟨k⟩=ln(317080)/ln(6.62208890914917) ≈ 6.700611818856679
Distance moyenne estimée : 6.789172899583702
On a la Distance moyenne = 6.789172899583702 > 6
	=> L'hypothèse des six degrés de séparation se confirme
	=> le réseau n'est pas un réseau petit monde car il ne remplit pas la condition de Six degrés de séparation
*/
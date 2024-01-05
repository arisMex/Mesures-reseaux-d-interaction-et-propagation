package Mesure;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

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
        String filename = "distributionDegres.dat";
        distributionDegres(graph, filename);
        String  pltFileName = "target/classes/distrib.plt";
        String  pltFileName2 = "target/classes/distrib_loglog.plt";
        String graphName = "distribs.png";String graphName2 = "distribs_loglog.png";
        //graphe linéaire
        genererFichierPLT( pltFileName, filename, graphName, "linéaire");
        //graphe log-log
        genererFichierPLT( pltFileName2, filename, graphName2, "log-log");
        //genererGraphe(pltFileName);

        System.out.println("Le tracé en échelle log-log de la distribution des degrés  forme  une ligne semi-droite,\n" +
                " cela dit que la distribution des degrés  suit  une loi de puissance ");



        //* 5.
        int n = 1000;
        HashMap<Integer, Double> distrDist = distanceDistribution(graph, n);
        double averageDistance = AVGdistanceDistribution(distrDist);
        System.out.println("\n 5. Distance Moyenne :" + averageDistance);
        System.out.println("Distance moyenne estimée : " + averageDistance);
        System.out.println("On a la Distance moyenne = "+averageDistance+ ((averageDistance>6)?" > 6  \n\t=> L'hypothèse des six degrés de séparation ne se confirme \n\t=>" +
                " le réseau n'est pas un réseau petit monde car il ne remplit pas la condition de Six degrés de séparation "     :   "petit Monde ✅"));


        String  pltFileName3 = "target/classes/Graph1_distribDist.plt";
        String graphName3 = "Graph1_distribDist.png";
        String filename3 = "Graph1_distributionDist.dat";
        distributionDistancesToFile(distrDist, "Graph1_distributionDist.dat");
        //graphe linéaire
        genererFichierPLT( pltFileName3, filename3, graphName3, "linéaire");
        genererGraphe(pltFileName3);








    }
}

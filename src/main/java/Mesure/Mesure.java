package Mesure;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;
import java.net.URL;


import static org.graphstream.algorithm.Toolkit.*;

public class Mesure {

    public static void main(String[] args) {

        System.setProperty("org.graphstream.ui", "swing");

        // Créer un graphe avec GraphStream
        Graph graph = new SingleGraph("MonGraphe");

        // Obtenez le chemin du fichier à partir des ressources
        URL resourceUrl = Mesure.class.getResource("/this.txt");
        if (resourceUrl == null) {
            System.out.println("File not found: this.txt");
            return;
        }

        String filePath = resourceUrl.getFile();
         //filePath = "target/classes/this.txt";

        System.out.println(filePath);

        FileSourceEdge edgeSource = new FileSourceEdge();

        edgeSource.addSink(graph);

        try {
            edgeSource.readAll(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            edgeSource.removeSink(graph);
        }
        System.out.println("Nombre de noeuds : " + graph.getNodeCount());
        System.out.println("Nombre d'arêtes : " + graph.getEdgeCount());
        System.out.println("Degré Moyen = " + averageDegree(graph));
        System.out.println("Densité = " + density(graph));
        System.out.println("Coefficient de Clustering Moyen = " + averageClusteringCoefficient(graph));
        //System.out.println("Coefficient de Clustering de tous les noeuds = " + Arrays.toString(clusteringCoefficients(graph)));
        System.out.println("* Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen sera le même \n\t Car : C = 3*DegréMoyen/tailleréseau " );




       // graph.display();
    }
}

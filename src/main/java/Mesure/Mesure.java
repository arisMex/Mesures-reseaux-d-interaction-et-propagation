package Mesure;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;

import java.io.IOException;
import java.net.URL;

public class Mesure {

    public static void main(String[] args) {

        System.setProperty("org.graphstream.ui", "swing");

        // Créer un graphe avec GraphStream
        Graph graph = new SingleGraph("MonGraphe");

        // Obtenez le chemin du fichier à partir des ressources
        URL resourceUrl = Mesure.class.getResource("/com-dblp.ungraph.txt");
        if (resourceUrl == null) {
            System.out.println("File not found: com-dblp.ungraph.txt");
            return;
        }

        String filePath = resourceUrl.getFile();
         filePath = "/home/etudiant/ma204380/IdeaProjects/Mesures/target/classes/data.dgs";

        System.out.println(filePath);

        FileSource fs = null;
        try {
            fs = FileSourceFactory.sourceFor(filePath);
            if (fs == null) {
                System.out.println("Unable to create FileSource for " + filePath);
                return;
            }

            fs.addSink(graph);
            fs.begin(filePath);

            while (fs.nextEvents()) {
                // Optionally some code here ...
            }

            fs.end();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                fs.removeSink(graph);
            }
        }

        graph.display(false);
    }
}

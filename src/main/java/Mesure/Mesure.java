package Mesure;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.*;
import java.net.URL;

import static org.graphstream.algorithm.Toolkit.degreeDistribution;




public class Mesure {

    public static void distributionDegres( Graph graph) throws IOException {
        String filename = "target/classes/distributionDegres.dat";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Écrire les données
            int k = 0;
            for (double distribution : degreeDistribution(graph)) {
                writer.write(k + "  " + distribution + "\n");
                k++;
            }


            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error saving data to " + filename);
        }

    }
    // * */ generer un fichier plt pour une simulation
    public static void genererFichierPLT(String filepath, String fileSource, String graphName, String typeGraph) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Écrire les données
            int k = 0;
            String type = (typeGraph == "log-log")?"set logscale xy\n":"";


            writer.write("# Nom du fichier de sortie de l'image\n" +
                    "set terminal png\n" +
                    "set output '"+graphName+"'\n" +
                    "\n" +
                    "# Titre du graphique\n" +
                    "set title 'Distribution Degrés'\n" +
                    type+
                    "set xlabel 'Degré'\n" +
                    "set ylabel 'nb sommets'\n" +
                    "set grid\n" +
                    "\n" +
                    "# Plots\n" +
                    "plot '"+fileSource+"' using 1:2 with linespoints title '"+typeGraph+"' ");

            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to " + filepath);
        }
    }
    // * */ executer un fichier plt et générer un graphe
    public static void genererGraphe(String nomFichier) {
        try {
            String command = "gnuplot " + nomFichier;
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {

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
        int nbEdges = graph.getEdgeCount();
        System.out.println("Nombre d'arêtes : " + nbEdges);
        Double degreMoyen = averageDegree(graph);
        System.out.println("Degré Moyen = " + degreMoyen);
        System.out.println("Densité = " + density(graph));
        System.out.println("Coefficient de Clustering Moyen = " + averageClusteringCoefficient(graph));
        //System.out.println("Coefficient de Clustering de tous les noeuds = " + Arrays.toString(clusteringCoefficients(graph)));
        System.out.println("* Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen sera le même \n\t Car : C = 3*DegréMoyen/tailleréseau " );
        System.out.println("\n");


        System.out.println("3.Connexité : ");
        System.out.println((isConnected(graph))?"Ce graphe connexe ✅ "  :"Ce graphe n'est pas connexe ❌");
        System.out.println("Un graphe aléatoir de telle taille et tel degré moyen est connexe avec une probabilité de : "+degreMoyen/nbEdges);

        System.out.println(Arrays.toString(degreeDistribution(graph)));
        


        //* 4.
        distributionDegres(graph);
        String  pltFileName = "target/classes/distrib.plt";
        String  pltFileName2 = "target/classes/distrib_loglog.plt";
        String graphName = "distribs.png";String graphName2 = "distribs_loglog.png";
        String filename = "target/classes/distributionDegres.dat";
        //graphe linéaire
        genererFichierPLT( pltFileName, filename, graphName, "linéair");
        //graphe log-log
        genererFichierPLT( pltFileName2, filename, graphName2, "log-log");
        //genererGraphe(pltFileName);

        System.out.println("Le tracé en échelle log-log de la distribution des degrés  forme  une ligne semi-droite,\n" +
                " cela dit que la distribution des degrés  suit  une loi de puissance ");



        // graph.display();
    }
}



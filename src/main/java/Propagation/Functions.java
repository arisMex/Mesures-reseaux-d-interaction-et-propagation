package Propagation;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.*;
import java.util.*;

public class Functions {

    public static Set<Node> nodesSet(Graph graph) {
        Set<Node> nodeSet = new HashSet<>();
        for (Node node : graph) {
            nodeSet.add(node);
        }

        return nodeSet;
    }

    private static Collection<Node> nodesCollection(Graph graph) {
        Collection<Node> nodeCollection = new HashSet<>();
        for (Node node : graph) {
            nodeCollection.add(node);
        }
        return nodeCollection;
    }

    public static List<Node> nodesList(Graph graph) {
        List<Node> nodeList = new ArrayList<>();
        for (Node node : graph) {
            nodeList.add(node);
        }
        return nodeList;
    }



    protected static void infectionDistribToFile( List<Collection<Node>> data, String file) throws IOException {

        String filename = "Data/Propagation/"+file;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Écrire les données
            int k = 1;
            for (Collection<Node> d : data) {
                // Écrire chaque valeur de la collection sur une ligne dans le fichier
                writer.write(String.format("%d %d\n", k++, d.size()));
            }


            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error saving data to " + filename);
        }

    }

    protected static void scenariosGenererFichierPLT(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Plot/Propagation/Graph_"+id+"_Scenarios.plt"))) {
            // Écrire les données
            int k = 0;



            writer.write("# Nom du fichier de sortie de l'image\n" +
                    "set terminal png\n" +
                    "set output '../../GraphsImages/Propagation/Graph_"+id+"_Scenarios.png'\n" +
                    "\n" +
                    "# Titre du graphique\n" +
                    "set title 'Graph "+id+" (Scénarios)'\n" +
                    "set xlabel 'jours'\n" +
                    "set ylabel 'infectés'\n" +
                    "set grid\n" +
                    "\n" +
                    "# Plots\n" +
                    "plot '../../Data/Propagation/G"+id+"_S1.dat' using 1:2 with linespoints title 'Scénario 1', " +
                    "'../../Data/Propagation/G"+id+"_S2.dat' using 1:2 with linespoints title 'Scénario 2', " +
                    "'../../Data/Propagation/G"+id+"_S3.dat' using 1:2 with linespoints title 'Scénario 3' ");

            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to file");
        }
    }
    // * */ executer un fichier plt et générer un graphe
    protected static void genererGraphe(String nomFichier) {
        try {
            String command = "cd Plot/Propagation && gnuplot " + nomFichier;
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



}

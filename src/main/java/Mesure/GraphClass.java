package Mesure;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.BreadthFirstIterator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.graphstream.algorithm.Toolkit.degreeDistribution;


public class GraphClass {

    protected static void distributionDegres(Graph graph, String file) throws IOException {
        String filename = "Data/Mesures/"+file;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Écrire les données
            int  taille = graph.getNodeCount();
            int k = 0;
            for (double distribution : degreeDistribution(graph)) {
                writer.write(k + "  " + distribution/taille + "\n");
                k++;
            }




            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error saving data to " + filename);
        }

    }

    //to File  : map => fichier
    protected static void distributionDistancesToFile( HashMap<Integer, Double> map, String file) throws IOException {

        String filename = "Data/Mesures/"+file;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Écrire les données
            int k = 1;
            for (Map.Entry<Integer, Double> m : map.entrySet()) {
                Integer dist = m.getKey();
                Double nb = m.getValue();
                writer.write(dist + "  " + nb + "\n");
            }


            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error saving data to " + filename);
        }

    }

    // * */ generer un fichier plt pour une simulation
    protected static void genererFichierPLT(String filepath, String fileSource, String graphName, String typeGraph) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Plot/"+filepath))) {
            // Écrire les données
            int k = 0;
            String type = (typeGraph == "log-log")?"set logscale xy\n":"";


            writer.write("# Nom du fichier de sortie de l'image\n" +
                    "set terminal png\n" +
                    "set output '../GraphsImages/Mesures/"+graphName+".png'\n" +
                    "\n" +
                    "# Titre du graphique\n" +
                    "set title 'Distribution Degrés'\n" +
                    type+
                    "set xlabel 'Degré'\n" +
                    "set ylabel 'nb sommets'\n" +
                    "set grid\n" +
                    "\n" +
                    "# Plots\n" +
                    "plot '../Data/Mesures/"+fileSource+"' using 1:2 with linespoints title '"+typeGraph+"' ");

            // System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to " + filepath);
        }
    }
    // * */ executer un fichier plt et générer un graphe
    protected static void genererGraphe(String nomFichier) {
        try {
            String command = "cd Plot && gnuplot " + nomFichier;
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

    public static List<Node> getEchantillon(Graph graphe, int tailleEchantillon) {
        return  Toolkit.randomNodeSet(graphe, tailleEchantillon);

    }


        public static double AVGdist( List<Node> echantillon, int tailleEchantillon) {
        //List<Node> echantillon = Toolkit.randomNodeSet(graphe, tailleEchantillon);

        double sommeDistancesMoyennes = 0;

        for (Node n : echantillon) {
            System.out.format("\r(%d/%d)", echantillon.indexOf(n) + 1, tailleEchantillon);
            double sommeLocale = 0;
            int nbVisites = 0;
            BreadthFirstIterator iterateur =   new BreadthFirstIterator(n);


            while (iterateur.hasNext()) {
                Node noeudCourant = iterateur.next();
                sommeLocale += iterateur.getDepthOf(noeudCourant);
                nbVisites++;
            }

            sommeDistancesMoyennes += sommeLocale / nbVisites;
        }

        System.out.println();
        return sommeDistancesMoyennes / tailleEchantillon;
    }


    public static HashMap<Integer, Double> distanceDistribution( List<Node> randomNodes, int n) {
       // List<Node> randomNodes = Toolkit.randomNodeSet(g, n);

        HashMap<Integer, Double> distribution = new HashMap<>();


        System.out.println( "Loading : ");

        int i = 0;

        for (Node noeud : randomNodes) {

            BreadthFirstIterator iter = new BreadthFirstIterator(noeud);

            while (iter.hasNext()) {
                int depth = iter.getDepthOf(iter.next());
                Double depthCount = distribution.get(depth);
                if (depthCount == null)
                    distribution.put(depth, 1.0);
                else
                    distribution.put(depth, depthCount + 1);
            }
            i++;
            System.out.format("\r(%d/%d)", i, n);

        }

        System.out.println();

        Double tot = distribution.values().stream().reduce(0.0, Double::sum);

        return distribution;
    }




}



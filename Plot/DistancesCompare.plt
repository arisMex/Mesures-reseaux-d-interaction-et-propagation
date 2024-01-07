# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/DistribDistancesCompare.png'

# Titre du graphique
set title 'Distribution Distances'
set xlabel 'd'
set ylabel 'p(d)'
set grid

# Plots
plot '../Data/Mesures/Graph1_distributionDist.dat' using 1:2 with linespoints title 'R-Collaboration', \
    '../Data/Mesures/Random_distributionDist.dat' using 1:2 with linespoints title 'R-Random', \
    '../Data/Mesures/Barabasi_distributionDist.dat' using 1:2 with linespoints title 'R-Barabasi'

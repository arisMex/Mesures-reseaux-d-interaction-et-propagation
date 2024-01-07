# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/DistribDegresCompare.png'

# Titre du graphique
set title 'Distribution Degrés'
set xlabel 'k'
set ylabel 'p(k)/N'
set grid
set logscale xy

# Plots
plot '../Data/Mesures/Graph1_distributionDegres.dat' using 1:2 with linespoints title 'R-Collaboration', \
    '../Data/Mesures/Random_distributionDegres.dat' using 1:2 with linespoints title 'R-Random', \
    '../Data/Mesures/Barabasi_distributionDegres.dat' using 1:2 with linespoints title 'R-Barabasi'

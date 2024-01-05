# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Graph1_distribs_loglog.png'

# Titre du graphique
set title 'Distribution Degrés'
set logscale xy
set xlabel 'Degré'
set ylabel 'nb sommets'
set grid

# Plots
plot '../Data/Mesures/Graph1_distributionDegres.dat' using 1:2 with linespoints title 'log-log' 
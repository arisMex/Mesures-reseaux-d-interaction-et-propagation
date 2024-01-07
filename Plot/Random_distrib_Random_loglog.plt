# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Random_distribs_Degre_loglog.png'

# Titre du graphique
set title 'Distribution Degrés'
set logscale xy
set xlabel 'd'
set ylabel 'p(d)'
set grid

# Plots
plot '../Data/Mesures/Random_distributionDegres.dat' using 1:2 with linespoints title 'log-log' 
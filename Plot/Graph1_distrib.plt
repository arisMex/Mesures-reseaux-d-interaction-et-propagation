# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Graph1_distribs.png'

# Titre du graphique
set title 'Distribution Degrés'
set xlabel 'd'
set ylabel 'p(d)'
set grid

# Plots
plot '../Data/Mesures/Graph1_distributionDegres.dat' using 1:2 with linespoints title 'linéaire' 
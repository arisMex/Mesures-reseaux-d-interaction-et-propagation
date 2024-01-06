# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Random_distribDist.png'

# Titre du graphique
set title 'Distribution Degrés'
set xlabel 'Degré'
set ylabel 'nb sommets'
set grid

# Plots
plot '../Data/Mesures/Random_distributionDist.dat' using 1:2 with linespoints title 'linéaire' 
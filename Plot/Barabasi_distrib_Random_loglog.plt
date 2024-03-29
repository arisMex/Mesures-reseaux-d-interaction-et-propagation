# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Barabasi_distribs_Degre_loglog.png'

# Titre du graphique
set title 'Distribution Degrés'
set logscale xy
set xlabel 'Degré'
set ylabel 'nb sommets'
set grid

# Plots
plot '../Data/Mesures/Barabasi_distributionDegres_Random.dat' using 1:2 with linespoints title 'log-log' 
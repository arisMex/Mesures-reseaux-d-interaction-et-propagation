# Nom du fichier de sortie de l'image
set terminal png
set output '../../GraphsImages/Propagation/Graph_3_Scenarios.png'

# Titre du graphique
set title 'Graph 3 (Scénarios)'
set xlabel 'jours'
set ylabel 'infectés'
set grid

# Plots
plot '../../Data/Propagation/G3_S1.dat' using 1:2 with linespoints title 'Scénario 1', '../../Data/Propagation/G3_S2.dat' using 1:2 with linespoints title 'Scénario 2', '../../Data/Propagation/G3_S3.dat' using 1:2 with linespoints title 'Scénario 3' 
# Nom du fichier de sortie de l'image
set terminal png
set output '../../GraphsImages/Propagation/Graph_2_Scenarios.png'

# Titre du graphique
set title 'Graph 2 (Scénarios)'
set xlabel 'jours'
set ylabel 'infectés'
set grid

# Plots
plot '../../Data/Propagation/G2_S1.dat' using 1:2 with linespoints title 'Scénario 1', '../../Data/Propagation/G2_S2.dat' using 1:2 with linespoints title 'Scénario 2', '../../Data/Propagation/G2_S3.dat' using 1:2 with linespoints title 'Scénario 3' 
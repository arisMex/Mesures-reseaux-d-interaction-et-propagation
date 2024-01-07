# Nom du fichier de données
data_file = '../Data/Mesures/Graph1_distributionDegres.dat'

# Paramètre de la distribution de Poisson (lambda)
# Degré moyen
lambda = 6.62208890914917
alpha = lambda / (lambda - 1)
C = 1.0

# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Graph1_Mesures.png'

# Titre du graphique
set title 'Distribution des Degres '

# Étiquettes des axes
set xlabel 'k'
set ylabel 'p(k)'

# Style de lignes
set style data linespoints

# Fonction de probabilité de Poisson
poisson(x, lambda) = exp(-lambda) * lambda**x / gamma(x + 1)

# Fonction de loi de puissance (à ajuster)
power_law(x, a, c) = c * x**(-a)

# Plot des données externes et de la distribution de Poisson
plot data_file using 1:2 with linespoints title 'Log-log', \
     poisson(x, lambda) with linespoints title sprintf('Poisson'), \
     power_law(x, alpha, C) with lines title sprintf('Puissance ')


# Affichage du graphique
replot

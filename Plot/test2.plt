# Nom du fichier de données
data_file = '../Data/Mesures/Random_distributionDegres.dat'

# Paramètre de la distribution de Poisson (lambda)
# Degré moyen
lambda = 6.62208890914917
alpha = lambda / (lambda - 1)
C = 1.0

# Nom du fichier de sortie de l'image
set terminal png
set output '../GraphsImages/Mesures/Random_Mesures.png'

# Titre du graphique
set title 'Distribution de Poisson et Loi de Puissance'

# Étiquettes des axes
set xlabel 'Nombre de succès'
set ylabel 'Probabilité'

# Style de lignes
set style data linespoints

# Fonction de probabilité de Poisson
poisson(x, lambda) = exp(-lambda) * lambda**x / gamma(x + 1)

# Fonction de loi de puissance (à ajuster)
power_law(x, a, c) = c * x**(-a)

# Plot des données externes et de la distribution de Poisson
plot data_file using 1:2 with linespoints title 'Données externes', \
     poisson(x, lambda) with linespoints title sprintf('Poisson(%.1f)', lambda), \
     power_law(x, alpha, C) with lines title sprintf('Loi de Puissance (alpha=%.2f)', alpha)

# Affichage du graphique
replot

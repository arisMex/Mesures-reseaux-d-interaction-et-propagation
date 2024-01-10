# Nom du fichier de données
data_file = '../Data/Mesures/Graph1_distributionDegres.dat'

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

set logscale xy
set yrange [1e-6:1]


# Poisson
lambda = 6.62208890914917
poisson(k) = lambda ** k * exp(-lambda) / gamma(k + 1)


# on va fitter une fonction linéaire en log-log

f(x) = lc - gamma * x
fit f(x) data_file using (log($1)):(log($2)) via lc, gamma

c = exp(lc)
power(k) = c * k ** (-gamma)


# Plot des données externes et de la distribution de Poisson
plot data_file using 1:2 with linespoints title 'Log-log', \
    poisson(x) title 'Poisson law', \
      power(x) title 'Power law'



# Affichage du graphique
replot

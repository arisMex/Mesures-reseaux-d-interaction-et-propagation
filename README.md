## Mesures de réseaux d'intéraction (réseau de collaboration scientifique en informatique)

## Question 2

Quelues Mesures de base :
```
|N| = 317080
|L| = 1049866
<k>  = 6.62208890914917
Densité = 2.0884666810161434E-5
C  = 0.6324308280637396
```
## Question 3

Le réseau scientifique est bel et bien connexe. 
Car on a \<k\> >= ln(N) 
Le seuil critique pour qu'un réseau aléatoire devienne connexe est atteint lorsque le nombre moyen de liaisons par nœud **\<k\>** est égal ou supérieur à **ln(N)**, où **N** représente le nombre total de nœuds dans le réseau.
**\<k\> >= ln(N)**

## Question 4
![Distribution des degrés](GraphsImages/Mesures/Graph1_Mesures.png)

## Question 5 

La distance moyenne, calculée à partir d'un échantillon de 1000 nœuds, est de 6.789172899583702. Pour déterminer si ce réseau peut être considéré comme un réseau petit monde, il est nécessaire de vérifier si sa distance moyenne est approximativement égale à **ln⁡(N) / ln⁡⟨k⟩**. En comparaison, la distance moyenne dans un réseau aléatoire présentant des caractéristiques similaires de taille et de degré moyen est d'environ ln(317080) / ln(6.62208890914917) ≈ 6.700611818856679. Étant donné que **ln(⁡N)/ln⁡⟨k⟩ est proche de ⟨d⟩**, ce réseau peut être qualifié de réseau **petit monde**.

L'hypothèse des six degrés de séparation se confirme partiellement, car bien que la distance moyenne entre les individus du réseau soit proche de 6, il existe des distances qui dépassent cette valeur. Cela suggère une certaine variabilité dans les connexions, avec une tendance générale à respecter l'idée des six degrés de séparation, mais des exceptions existent sous la forme de distances plus importantes que 6 entre certains noeuds.

Dans un réseau aléatoire avec les mêmes caractéristiques de degré moyen, nombre de nœuds et distance moyenne, la distance moyenne peut être approximée par la formule de la distance moyenne dans un réseau aléatoire. Cette formule est donnée par : ⟨d⟩≈ln(N)/ln(⟨k⟩). qui est la même dans nnotre réseau , alors la distance moyenne d'un réseau aléatoire avec es mêmes caractéristiques sera similaire.

![Distribution des distances](GraphsImages/Mesures/Graph1_distribDist.png)

Après avoir tracé la distribution des distances dans un réseau, l'hypothèse est que la forme de cette distribution ressemble à une loi normale, qui suggère que la plupart des distances entre les nœuds du réseau se situent autour d'une moyenne d'environ 6.9. En d'autres termes, la majorité des relations entre les nœuds semblent suivre une tendance centrale avec une concentration significative autour de cette valeur moyenne. Ce résultat peut indiquer une certaine régularité ou normalité dans la manière dont les nœuds du réseau sont connectés, ce qui est souvent associé à des propriétés spécifiques, telles que celles observées dans les réseaux de type "petit monde".






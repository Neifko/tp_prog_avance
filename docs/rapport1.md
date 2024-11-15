# Programmation parallèle sur machine à mémoire partagée RAPPORT 1

> GUILLERAY Victor  
> INF3FA  
> 15/11/2024  

## Introduction

Présentation du travail sur la programmation parallèle sur des machines à mémoire partagée.


Pour les travaux réalisés, nous avons utilisé IntelliJ de la suite des IDE de JetBrains. Il nous permet de gérer
convenablement un projet et de mêler la documentation, le code et les tests.
Nous avons utilisé GIT pour la gestion de version des travaux et pour plus facilement se retrouver sur les étapes
de développement et sur son avancement sur les exercices.
Pour la conception, on utilise StarUML pour réaliser des diagrammes et générer du code.
On a utilisé le langage Java avec la classe Thread de Java et la librairie Swing pour les interfaces.


## Instructions

Quelques instructions données pendant les cours pour les travaux à réaliser.
On doit réaliser les tests et la gestion de version GIT du projet, comme indiqué aussi sur le cours.

Avec la conception et le code.

Le tout divisé en 2 rapports qui contiennnt : une synthèse de cours des tp, et un rapport des tp.

Et pour la vérification des acquis un controle toutes les 5 seances (2 controles en tout).

Pour la SAE, nous allons réaliser un cluster de raspberry.

Pour la séance 5 : 
Ajuster la boite à lettre avec la boulangerie pour utiliser les queues
https://blog.paumard.org/cours/java-api/chap05-concurrent-queues.html


## Programmation répartie

### Seance 1

Pour imager certains propos du cours, nous avons brièvement parlé de paradigme et de framework, voici donc une
définition de ces deux termes.

Un paradigme est une structure algorithmique, ou encore la façon d'approcher la programmation pour la résolution d'un
problème et sa formalisation dans un langage approprié.

Un framework est un ensemble d'outils, de composants et de bibliothèques préconçues donnant une
structure de base pour le développement d'un logiciel.


Lorsqu'une application demande beaucoup de performance, on peut utiliser plusieurs serveurs pour répartir la charge
et ainsi optimiser la performance de l'application au sein d'une infrastructure. On appelle ça la répartition de
charge ou load balancing. Cela devrait pouvoir être mis en application dans le cadre de la SAE.

Pour traiter les processus, les processeurs se sont améliorés. Au début ils avaient qu'un seul cœur ou unité de calcul
et maintenant ils en ont plusieurs. On peut donc traiter plusieurs processus en même temps. On a donc des processeurs
multicœur. Un cœur est composé de plusieurs transistors, et la fréquence d'un processeur est le tick d'horloge.

Les ordinateurs les plus puissants sont les supercalculateurs. Ils sont utilisés pour des calculs sur des très grands
nombres. Aujourd'hui, le plus puissant est le Fugaku, un supercalculateur japonais.

Distinguer la différence de quand utiliser cpu et gpu pour les calculs
Le CPU permet de réaliser des calculs permettant le fonctionnement de l'ordinateur ou de l'infrastructure, c'est
l'unité centrale du PC. Le GPU est destiné au traitement des tâches graphiques. Tandis que le CPU gère toutes les
tâches nécessaires au fonctionnement, le GPU peut traiter un grand volume d'instructions et ceci à vitesse élevée.
Il est donc plus adapté pour les calculs parallèles.

On a parlé de FPGA qui est un circuit intégré configurable. Il peut être programmé pour réaliser des tâches.
Les tâches sont des bouts de code, les processus sont ce qui va porter la tache et le mettre en mémoire, et le
processeur est ce qui exécute le processus.

Dans la salle de TP G26 il existe 2 architectures différentes de
machine.
Une première qui correspond à la quasi-totalité des machines de la salle, et une deuxième qui correspond à 3 ou 4
machines.

Comme bilan de la première machine voici les caractéristiques :
Ordinateur : Dell Inc. OptiPlex 7050
Carte graphique : Intel HD Graphics 630
Processeur : Intel Core I7-7700 3.60GHz 4 cœurs 8 Threads
Disque : SanDisk X400 512GB
RAM : 32Go 2400 MHz

La deuxième avait un processeur différent.

On a ensuite comparé les deux architectures notamment sur la partie du processeur, et on s'est rendu compte que la
deuxième était plus récente et un peu meilleur en termes de performance théorique.

On a aussi comparé les architectures de nos téléphones et on s'est rendu compte que les processeurs que l'on possède
sont plus puissant que des supercalculateur de l'époque. (année 60)

## Programmation parallèle

### TP1

#### Exercice 1 : 

On commence par la conception, le diagramme se trouve dans le dossier conception. Il faut l'ouvrir avec star uml.

Analyse de la demande :
UnMobile est l'abstraction du mobile dans la fenêtre qui se déplacera.

UneFenetre est l'abstraction de la fenêtre dans laquelle le mobile est.

TpMobile est la classe permettant l'exécution de la fenêtre.

UnMobile a une méthode run, c'est donc un thread.

Largeur et hauteur de la fenêtre n'est pas static car on souhaiterait en créer de taille différente.

Dans la méthode run du mobile, on gère son déplacement. C'est donc ici qu'on crée les boucles for permettant
qu'il se déplace de gauche à droite.


#### Exercice 2 : 

On ajoute un bouton pour suspendre ou reprendre l'exécution des processus. On doit pouvoir observer qu'un mobile bouge
pendant que l'autre reste immobile.

L'exercice n'étant pas demandé finalement, on ne le réalise pas.

#### Exercice 3 :

Pour l'exercice 3, on adapte la taille de la fenêtre au nombre de mobile voulu. Ensuite, on imagine le système
permettant d'imager une ressource critique sur la fenêtre, on dit qu'une colonne au centre du parcours
ne peut être traversé seulement que par N mobile à la fois. Le code présent dans le run du mobile est modifié.
On a 6 boucle for, 3 à l'aller et 3 au retour. La deuxième et la cinquième boucle for sont des sections critiques.
Elles correspondent à la colonne centrale du parcours. Voir la suite dans la séance 2.


### Seance 2

Pour clôturer l'exercice 3, on a vu les notions de ressource critique et section critique. La ressource critique
est une ressource qui ne peut être utilisée que par un seul processus à la fois. La section critique est
la portion de code dans laquelle ne s’exécute qu’un thread à la fois. Une section critique est utilisée lorsque
plusieurs threads accèdent à une même ressource.
Les différents mobiles qui souhaitent accéder à la ressource critique doivent attendre que le mobile qui est en train
d'utiliser la ressource critique ait fini. L'accès implique donc l'exclusion des autres mobiles de cette zone
et réciproquement, on dit alors que les mobiles sont en exclusion mutuelle.

On peut aussi réaliser cette protection avec un verrou MUTEX en Java. On utilise le mot-clé synchronized pour verrouiller.
l'accès à une ressource critique.

Les principes du verrou MUTEX sont les suivants :
- A un instant T, un processus au plus peut se trouver en section critique.
- Si un processus est bloqué en dehors de la section critique, un autre processus doit pouvoir entrer en section
  critique.
- Si plusieurs processus sont bloqués en attente d’entrée dans une
  section critique et qu’aucun processus n’est en section critique, alors
  l’un des processus doit pouvoir y entrer au bout d’un temps fini.
- La solution doit être la même pour tous.

#### TP 2 Exercice 1
La ressource critique repérée est System.out. Cette ressource est dans une boucle for permettant l'affichage des lettres.
La boucle for contenant la ressource est la section critique. On utilise synchronized autour de la boucle for
avec Sytem.out comme argument pour finir l'affichage de la suite de lettres correctement avant la suivante.

#### Exercice 2

Pour l'exercice 2 n utilise un système de sémaphore, il permet de contrôler l'accès à une ou plusieurs ressources.
Le nombre d'accès est limité.
Il doit être supérieur à 1, mais borné.
Il existe le sémaphore binaire, pour des ressources à accès unique. (1 par 1)
Mais il y a aussi le sémaphore général pour des ressources à accès multiple.

Il possède un entier qui caractérise le nombre de ressource disponible.

On a donc une classe Semaphore avec les méthodes wait et signal : wait permet l'accès à une ressource en
décrémentant le nombre de ressource disponible, signal permet de libérer la ressource en l'incrémentant.


Le semaphore binaire hérite de semaphore de syncwait et sync signal, on réécrit signal, mais on laisse wait intact.
De manière à avoir un seul accès à la ressource critique.

Les sémaphores permettent la réalisation de la colonne centrale du parcours de l'exercice 3 du TP1.


On revoit l'exercice 2 du tp 2 pour l'adapter avec les sémaphores.
On utilise wait juste avant la section critique et on signal à la fin pour libérer la section critique.


### Seance 3
Étant donné l'assemblée générale, nous n'avons pas avancé.


### Seance 4

Je n'avais pas encore compris le sémaphore binaire, il fallait déclarer le sémaphore en static, car il y a un seul
sémaphore pour tous les threads (processus). Et sa valeur initiale ne peut pas être 0 car sinon il reçoit un wait,
il faut donc l'initialiser à 1 et ensuite quand le premier thread arrive alors le sémaphore passe à 0
et le thread peut s'exécuter tranquillement dans la section critique, quand il a fini, il signal tout le monde
et le sémaphore repasse à 1, et le prochain thread peut passer comme expliqué précédemment.

En testant avec synchronized sur la section critique du tp sur les mobiles à la place des sémaphores, cela fonctionne
aussi.
Pour cela, on utilise l'instance de la classe JPanel à synchroniser.
`synchronize (JPanel.class){boucle for (section critique)}`



#### TP 3

Pour le tp 3 nous avons vu les moniteurs, les producteurs et les consommateurs.
Le moniteur est un object de synchronisation qui permet :
- l'exclusion mutuelle entre opérations sur des données, donc un seul thread peut accéder à la fois à une ressource
- mais aussi d'attendre qu'une condition soit validée pour permettre l'accès aux données

Autrement dit, il possède des ressources et gère leur protection.
C'est une structure de donnée avec des méthodes qui sont le point d’entrée des Threads.

Un seul processus peut être actif à la fois dans un moniteur. Les autres processus sont en attente tant que le
processus n'est pas sorti du moniteur.

On a la classe BoiteALettre abstraction du moniteur.
Avant sa modification avec les queues, on avait la ressource critique lettre abstraction du buffer (mémoire du moniteur)
Et la variable boiteVide abstraction de la variable de synchronisation (permet de dire si le moniteur est
libre ou non)

Le moniteur a deux méthodes : déposer et retirer pour mettre une lettre dans la boite ou en retirer une.

On crée une classe Producteur abstraction d’un producteur qui écrit dans lettre
On crée une classe Consommateur abstraction d’un consommateur qui lit dans lettre
Pour gérer les accès à lettre, on utilise la classe Moniteur

Ces classes utilisent synchronized.

Voir le diagramme de conception.

Après avoir adapté le programme en utilisant les blocking queue, on retire les synchronized car la synchronisation est
gérée dans les queues. Notamment à l'aide du blog sur la boulangerie.

Pour l'exercice 2, il est demandé de traverser l'alphabet dans le programme avec un caractère d'arrêt.
Avec des sleep plus long pour le consommateur, on atteint bien le bout de la mémoire tampon.
La queue se remplit est se vide plus doucement, jusqu'à atteindre le caractère d'arrêt. Je tente de le déposer plusieurs
fois, car il se fait rejeter le temps que le tampon se vide, ensuite les derniers caractères s'ajoutent dans la queue
puis le caractère d'arrêt le processus se terminent.



## Séance 5

On voit que la classe Boulanger utilisé est une interface de la classe BlockingQueue car la queue est un moniteur 
en soit.
La blocking queue est une manière plus complexe que nous pour la parallélisation.

La variable lettre qui était dans la BoiteALettre a disparu, car elle est en tant que buffer dans la queue.

En faisant notre adaptation de la BoiteALettre, on a un wrapper de la BlockingQueue avec la BAL.



## Conclusion

Pour conclure, nous avons vu les différents matériels permettant la programmation parallèle et surtout l'évolution
de ces matériels. Nous avons vu les bases et les différentes manières de gérer la programmation parallèle.
Et donc d'exécuter des processus en parallèle et de les manipuler.
On a découvert la classe Thread de Java, puis l'utilisation de synchronized, des sémaphores et des moniteurs. Et 
l'encapsulation des BlockingQueues par notre propre moniteur.
Nous avons vu comment les appliquer sur nos programmes et les avantages et inconvénients de chacun.

## Sources
- Le cours
- Les TPs
- https://aws.amazon.com/fr/compare/the-difference-between-gpus-cpus/#:~:text=Les%20GPU%20et%20CPU%20poss%C3%A8de,de%20signaux%20num%C3%A9riques%20appel%C3%A9s%20bits.





# Programmation parallèle sur machine à mémoire partagée RAPPORT 2

> GUILLERAY Victor  
> INF3FA  
> 13/12/2024


## Introduction

Ce travail pratique a pour objectif d'explorer les concepts et techniques de la 
programmation parallèle sur une architecture à mémoire partagée et une architecture
à mémoire distribuée.  
Nous avons abordé plusieurs aspects théoriques et pratiques permettant de mieux 
comprendre comment optimiser l'exécution d'un programme en exploitant le parallélisme.

Dans un premier temps, nous avons vu les différents modèles et paradigmes de 
programmation parallèle. Ensuite, nous avons mis en pratique ces notions à travers divers exercices et implémentations en Java.  
Enfin, nous avons analysé la performance des algorithmes à l'aide de métriques 
telles que le **speedup** et la **scalabilité**.

Ce rapport présente donc les différents concepts abordés, les implémentations réalisées ainsi que l'analyse des performances obtenues.


## Modèles et paradigmes de programmation parallèle

### Notions fondamentales

La programmation parallèle repose sur l'exécution simultanée de plusieurs tâches afin d'améliorer les performances d'un programme. Un programme initialement séquentiel peut être converti en programme parallèle en suivant certaines méthodes et en respectant des contraintes spécifiques.

Mais attention, on sait qu'un paradigme est une structure algorithmique et qu'il ne faut ne faut donc pas mélanger modèle et paradigme.

### Suite du cours : Modèles et paradigmes de programmation parallèle

Le projet n'est pas adapté à un système en mémoire distribuée. Des modifications importantes seraient nécessaires, ce qui entraînerait plusieurs façons d'écrire le code pour répondre à divers besoins, comme différentes architectures ou qualités algorithmiques.

Comment rendre parallèle un algorithme séquentiel ?

- Analyser l'algorithme en cherchant des endroits pour paralléliser l'algo et trouver des tâches qui peuvent s'exécuter
  de manière indépendante et comment sont exprimées leurs interactions.
- On distingue deux grandes classes de modèle de programmation :
    - Parallélisme de tâche
    - Parallélisme de donnée


### Explication de monte carlo pour calculer PI



### Paradigmes de Programmation

- **Parallélisme de tâche** : Ce paradigme consiste à décomposer une tâche en plusieurs sous-tâches qui peuvent être exécutées en parallèle. Les sous-tâches peuvent partager des variables ou communiquer par message. Ce paradigme est adapté aux applications où les tâches peuvent être clairement définies et séparées.

- **Parallélisme de donnée** : Ce paradigme consiste à décomposer les données en plusieurs parties qui peuvent être traitées en parallèle. Chaque partie des données est traitée indépendamment, ce qui permet d'accélérer les calculs. Ce paradigme est adapté aux applications de traitement de données volumineuses.

Par exemple, les cas d'utilisation sont des tâches, car ce sont des scénarios qui en appellent d'autres, donc des tâches qui se divisent en sous tâches.

### Modèles de Programmation

1. **SISD (Single Instruction Single Data)** : Une seule instruction est exécutée sur un seul jeu de données. Ce modèle est le plus simple et est utilisé dans les systèmes où une seule tâche est exécutée à la fois. Il est principalement utilisé pour des tâches simples ne nécessitant pas de parallélisme. Il correspond surtout au ancien processeur mono coeur.

2. **SIMD (Single Instruction Multiple Data)** : Une seule instruction est exécutée sur plusieurs jeux de données. Ce modèle est typiquement utilisé dans les cartes graphiques où la même opération est appliquée à une grille de données sur plusieurs processeurs. Il est efficace pour les tâches répétitives et homogènes.

3. **MIMD (Multiple Instruction Multiple Data)** : Plusieurs instructions sont exécutées sur plusieurs jeux de données. Ce modèle est le plus flexible et est utilisé dans les systèmes où différentes tâches peuvent être exécutées simultanément. Il est adapté aux applications complexes nécessitant une grande variété de traitements. Il correspond à nos processeurs multicoeurs.

### Analyse de programme

Un code comporte deux phases principales : le calcul et l'échange de données. Les tâches peuvent se chevaucher entre le traitement des données et le calcul. Si les sous-tâches d'une boucle sont indépendantes, elles peuvent être exécutées en parallèle, ce qu'on appelle l'itération parallèle.

La méthode "diviser pour régner" est une structure algorithmique récursive qui utilise de nombreuses petites tâches pour accomplir une grande tâche.

Un pipeline est constitué de plusieurs processus exécutés en parallèle, mais chaque processus doit attendre la fin du précédent. Par exemple, laver le linge, le sécher, le repasser et le plier, chacun dans l'ordre.


## Implémentations Pratiques

### TP 4 : Réorganisation des Fichiers et Classes

Exo 1 et 2 sur le cahier

Nous avons utilisé le code de Steve Kaultz pour Assignement 102 et Pi. Ensuite nous avons réorganisé les fichiers et classes en plusieurs fichiers pour améliorer la structure du code. Cette réorganisation a permis de clarifier les responsabilités de chaque classe et de faciliter la maintenance du code. La modularité du code a été améliorée, ce qui est essentiel pour le développement de programmes parallèles.

### Assignement 102

Le code d'Assignement 102 utilise l'API concurrent l'appelation nAtomSuccess correspond au n_cible habituel.

Une tache monte carlo est une iteration de notre boucle, le code utilise le paradigme des iterations parallèles.

Il fonctionne en créant des taches de monte carlo et en les attribuants à un thread.

Cependant le programme passe trop de temps dans la section critique, on peut tout de même accelerer un peu en disant les fleches qui tombent hors de la cible au lieu de dedans. On passe de 75% des flèches tombées environ à 25% donc 50% de flèche en moins à traiter.

Malheureusement l'acceleration reste minimal et n'augmente pas suffisamment les performances.

Assignement 102 utilise le vole de tache car il peut être adapter dans ce contexte cependant il passe beaucoup de temps dans les communications d'où la lenteur de ce programme.

Le programme à un nombre de tâche correspondant au nombre de flêche, puis le système les séparent en un nombre P de processus. En assignant les taches au processus, cela prends beaucoup de temps quand on met une grande quantité de fleche.


### Pour PI 
Le programme Pi utilise ArrayList, List et random de la classe Util (pas de math).

La class Pi avec la method main permet d'instancier Master et execute doRun. Cette méthode dépend de la classe Master.
La méthode doRun permet d'executer avec un certain nombre de flèche ici total count à divise en plusieurs processus qui est numworkers. Pour determiner le temps d'execution on a starttime et stoptime.

On crée tasks qui est la liste des taches qui seront effectuées, en respectant le paradigme choisi chaque tache est un worker. Les worker sont des Callable qui sont des Runnable qui renvoie un resultat qui est ajouté dans une liste qui est une Future.


## Analyse des codes


On peut retrouver les diagrammes de classes des codes réalisés dans le dossier conception.

Quel algorithme est implementer, quelle paradigme

On avait vu les Future -> objet qui stock les resultats pour les donner, dans concurrent c'est on a besoin d'un 
runnable qui renvoie un resultat => callable donc thread qui renvoie un resultat
on l'appelle future pour l'asynchronisme mais doit etre synchroniser avec resuolution des dependances entre les 
taches qui peut etre fait quan don connaiot le resultat d'une tache; données doit etre recuperere avant de connaitre 
la suite du programme; on appelle future qu'on va chauiner dans une liste et quand ils arrvent on les recupere pour 
debloquer la dependance. monte carlo master worker , les worker renvoie le resultat car ce sont des future qui
renvoie le resultat a un instant t ; resultat arrive mis dans une liste (reduction ici) 

openmp lib pour faire prog en memoire partage

Comment est implementer le master worker ici ?

Operation Atomic (atomic integer) : operation d'incrementation est section critique, on utilise moniteur qui est
atomic et donc objet qui protege un entier avec des points d'entrer comme incrementAndGet

### speedup

tc temps calcul theorique different a chaque fois

Temps d'execution : 
T1 = ntot * ti                # temps d'execution mesurer avec 1 processus
T2 = ntot/2 * ti + (tc2)
Tp = ntot/p * ti + (tcp)      # temps mesurer avec plusieurs processus
Sp = T1/Tp  = (ntot*ti) / (ntot/p * ti) + (tc)

Sp = speedup

Tp = le temps moyen pour le nombre de processus p

on croit aller p fois plus vite

c'est scalabilité forte (voir cahier) zone negative veut dire je ralenti

Tp > T1
Sp < 1
ntot/2 + 3/4 * ntot

(si on met dans le petit zone 1/4 donc remplace 3/4 par 1/4 alors Tp<T1 Sp > 1)

Le super lineaire existe seulement quand la charge processeur est beaucoup trop haute en sequentiel;
proc doit recup info du tableau dans la ram, si tableau petit alors il le met dans le cache et travaille que dans le 
cache mais si tableau tres grand alors doit le saucissonner et mettre bout de tableau un par un dans le cache et
dans la ram pour pouvouir le traiter en entier, processus font des aller retour cache ram cache ram qui prennent 
du temps, avantage des worker qui peuvent chacun avoir un morceau qui rentre dans le cache

### Consignes : 

pouvoir faire varier le nombre de proc
a la main ou boucle pour traiter different ntot et proc

1) executer les codes -  assignement 102 et pi et passer les parametres d'entrée (A102 : ntot et nb processor;
Pi : ntot/p et p = nbworker) 
2) s'assurer qu'on peut traiter les infos de ces codes - avoir les meme sorties pour les 2 codes (avoir les meme print)
erreur piapproc - piexact / piexact ; ntot ; nb process; temps exec ; 
+ recommendation : print des sorties dans un fichier avec append (out_ass102 et out_pi_salle_4ou8c(oeur)).txt
3) speedup grossier, est ce que le code se comporte comme attendu ; voir l'evolution du temps au fur et a mesure


Voir les txt out.txt


### Resume : 
#### Sur architecture a memoire partage
prog parallele et distribuee
concurrence
architecture memoire partagé
java thread
monte carlo avec api concurrent -> modele et paradigme de programmation parallele
on peut pas avoir plus de coeur physique ou de coeur logique

il faut augmenter les ressources physique en utilisant d'autres machines

#### Sur architecture a memoire distribue
utilisation des sockets java sur plusieurs machines



### Suite :


faire un cours sur les sockets dans le rapport et petit document de conception sur l'explication du worker et master

On peut lancer plusieurs worker en cochant la case allow multiple instances, on peut lui mettre un arguement pour
l'execution, on met le port d'écoute du worker
Il reste à implementer le montecarlo

Le master on l'execute et on lui donne le nombre de machine et le port des machines

## Evaluation de la performance

scalabilité etc resume de qualité de developpement


lire le fichier texte et calculer le sp et les moyenne de tp etc et faire un plot


## Reutilisation de composant logiciel

On argumente pour choisir de reutiliser pi.java (en utilisant les experiences)

integrer le monte carlo de pi avec de la conception pour appeler le code et le mettre dans masterworker

on commence avec 4 worker avec 1 thread pi.java par worker pour les 4 coeurs mais plus tard 1 worker avec 4 thread 
sur pi.java

1) Validation MC avec javasocket
2) Conception integrer pi.java dans worker socket
3) Validation 
4) Scalabilité sur 1 poste
5) Mise en oeuvre sur N postes

Faire le diagramme de classe

Faire un dessin explication de la parallelisation a plusieurs niveau

Critere qualité reutilisation de composant, reutilisation de pi.java

l'ecart entre la courbe de scalabilité et les courbes obtenu avec mws est du au latence du reseau donc pas les meme
raison que pour masterworker du pi.java

### Execution distribuée

sur centos car windows bloque les comm entre machine

on desactive firewall de centos `sudo systemctl stop firewalld`

on execute les workers puis on execute le master avec les ips des machines workers


## 10/02/2025

### A quoi  correspondent les mesures de scalabilité dans la norme IEC 25010 ?

Est ce que la scalabilité d'un code parallele permet d'evaluer un critere de qualité au sens de la norme IEC 25010 ?

metrique : 
- temps d'exec
- Speedup

tracer l'erreur de pi pour le nombre de n_cible


Mesure de effectiveness et efficiency dans iec 25022

Si le temps de ref est celui qu'on souhaite obtenir alors
temps cible / temps calculer
Tchap / Tp = 0,5 / 0.75 = 1/2 * 4/3 = 2/3
exemple : si T1 = 1sec alors T2 = 0.5sec

efficacité parallele

a combien de pourcent est ce que on est parallele ?

Tchap - Tp / Tchap

On a mesuré time efficiency
les metrique qu'on a mesurer enfaite finalement correspondent bien a nos courbes de speedup

faire reference au document et au code de metrique : au sens de la norme IEC 25022
100 % efficacité
faire pour Pi.java et Ass102

efficteveness et efficiency lequel est le meilleur ?

Faire les courbes : 
- tracer erreur en echelle log10

Faire des paragraphes pour expliquer les resultats obtenus

est ce que ce qu'on a calculer donne le bon resultat

essayer de faire un boxplot (boite a moustache) pour l'affichage des erreurs


## Conclusion

Ce TP a permis d'explorer la programmation parallèle et ses implications sur la performance des algorithmes.  
Nous avons vu que la parallélisation n'apporte pas toujours une amélioration linéaire et que des facteurs comme la synchronisation ou la communication entre threads influencent fortement les résultats.

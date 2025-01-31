# Programmation parallèle sur machine à mémoire partagée RAPPORT 2

> GUILLERAY Victor  
> INF3FA  
> 13/12/2024



## Modèles et paradigmes de programmation parallèle

Nous avons fait le début du cours, voici les premières notes.

Il y a des méthodes à suivre pour passer d'un code séquentiel à un code en parallèle

Un paradigme est une structure algorithmique
donc il ne faut pas mélanger modèle et paradigme

2 aspects en séquentielle
en parallèle, il y a 3 aspects
SIMD ou MIMD
Ils sont expliqués plus bas.

### Suite du cours : Modèles et paradigmes de programmation parallèle

Le projet n'est pas portable sur un système en mémoire distribuée. Il faudrait faire des modifications importantes. Cela
créer plusieurs façons d'écrire le code si on veut l'adapter pour plein de besoins (architectures différentes, qualité
algorithmique).

Comment rendre parallèle un algorithme séquentiel ?

- Analyser l'algorithme en cherchant des endroits pour paralléliser l'algo et trouver des tâches qui peuvent s'exécuter
  de manière indépendante et comment sont exprimées leurs interactions.
- On distingue deux grandes classes de modèle de programmetion :
    - Parallélisme de tâche
    - Parallélisme de donnée

Parallélisme de tâche :
- Décomposition d’une tâche en plusieurs sous-tâches
- Variables partagées entre les (sous-)tâches OU communication par message entre tâche, on doit identifier la
  méthode à utiliser
- On associe une tâche à un processus (support d’exécution de la tâche)

Les cas d'utilisation sont des tâches, car ce sont des scénarios qui en appellent d'autres,
donc des tâches qui se divisent en sous tâches.

Remarque liée à l'architecture :


SISD : Single Instruction Single Data


SIMD :
Single Instruction Multiple Data (carte graphique -> une grille de donnée qu'on exécute sur plusieurs processeurs)


MIMD : Multiple Instruction Multiple Data


Dans un code il y a deux phases principales, une phase de calcul et une phase d'échange de données, les tâches
peuvent avoir un recouvrement de temps entre le traitement des données et le traitement du calcul.
Si les sous tâches d'une boucle sont indépendantes alors, je peux faire du parallèle, appelé de l'itération parallèle.
Divisé pour régner est une structure algorithmique recursive en utilisant plein de petites tâches pour faire une grande
tâche.
Pipeline : 4 processus qui s'exécute en parallèle (je lave le linge, je seche, je repasse, je plie) 1 par 1 en
respectant la dépendance.

code de steve kaultz 

### tp 4 
exercice 1 et 2 fait sur le cahier

reorganiser les fichiers et classes en plusieurs fichiers

### Assignement 102

API concurrent
n_cible <=> nAtomSuccess

Une tache monte carlo est une iteration

Algo des iterations parallèle

Creer tache monte carlo et lui associé un thread

On a choisit algo avec vole de tache car adapter ici


### Pour PI 
on utilise arraylist list et random de util (pas de math)

class pi avec method main => instancie master et execute doRun
main depend de classe master

master a method dorun => total count ? numworkers ?
start time ?

on cree tasks liste de tache

chaque tache est un worker

les worker sont des callable

callable est un runnable qui renvoie un resultat

liste de resultat future

Executor a algorithme pour vol de tache, ordonnancement, etc

## Seance 13/12

Analyser chaque code (pi monte carlo) + diagramme de classe

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








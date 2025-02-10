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

trop de temps dans section critique, on peut accelerer en disant les fleches qui tombent hors de la cible
75% on passe a 25%

cependant cela n'accelere pas beaucoup

On a choisit algo avec vole de tache car adapter ici cependant on passe beaucoup de temps dans les communications

Le système à un nombre de tache correspondant au nombre de fleche, puis le systeme separent en p processus
en assignant les taches au proc, cela prends beaucoup de temps quand on met une grande quantité de fleche




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




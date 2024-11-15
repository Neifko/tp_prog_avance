

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

# Programmation parallèle sur machine à mémoire partagée

> GUILLERAY Victor  
> INF3FA  

## Introduction

todo


## TP0
https://docs.google.com/document/d/1r0L3JKQ7UOadBcObPoSn3bUpckpMJYGbU_cf3JRc0bY/edit


## TP1


### Exercice 1 : 

On commence par la conception,
Diagramme et analyse disponible dans le dossier conception.

UnMobile est l'abstraction du mobile dans la fenêtre qui se déplacera

UneFenetre est l'abstraction de la fenêtre dans lequel le mobile est

TpMobile est la classe permettant l'execution de la fenêtre

UnMobile a une méthode run, c'est donc un thread

Largeur et hauteur de la fenetre n'est pas static car on 
souhaiterait en creer de taille differente


### Exercice 2 : 

On ajoute un bouton pour suspendre ou reprendre l'execution 
des processus.


## TP 2 :

Ressource critique, section critique et Exclusion mutuelle.

Verrou MUTEX -> synchronized

On synchronise system.out car il s'agit de la ressource critique


Semaphore :  


Semaphore binaire :  
on herite de semaphore de syncwait et sync signal
on reecrit signal  mais on laisse wait intact

Ressource critique ne peut etre acceder que par 1 seul thread

Notre ressource critique est System.out

La section critique est la boucle ou se trouve le System.out

On wait juste avant la section critique et on signal a la fin pour liberer la section critique

## TP 3 : 

Assemble generale


## TP 4 :

J'avais pas compris semaphore binaire, il fallait declare le semaphore en static car il y a un seul semaphore pour
tous les threads. Et sa valeur initial ne peut pas etre 0 car sinon il recoit un wait, il faut donc l'initialiser à 1
et ensuite quand le premier thread arrive alors le semaphore passe à 0 et le thread peut s'executer tranquillement
dans la section critique, quand il a fini, il signal tout le monde et le semaphore repasse a 1, et le prochain thread 
peut passer.



## Conclusion

todo



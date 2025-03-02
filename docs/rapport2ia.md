






Les exercices 1 et 2 ont été réalisés sur le cahier, suivis d'une 



### Assignement 102 : API Concurrent

L'assignement 102 a permis de mettre en pratique les concepts de programmation parallèle en utilisant l'API concurrent de Java. Une tâche Monte Carlo a été implémentée comme une itération, avec une gestion des sections critiques pour optimiser les performances. Les sections critiques sont des parties du code où l'accès concurrent doit être contrôlé pour éviter les conflits. L'utilisation de l'API concurrent a permis de simplifier la gestion des threads et d'améliorer l'efficacité du programme.

### Implémentation de Pi

L'algorithme de calcul de Pi a été implémenté en utilisant des `ArrayList` et `Random` de la bibliothèque `util`. La classe `Pi` instancie un `Master` qui exécute les tâches en parallèle. Les `Worker` sont des `Callable` qui renvoient des résultats, gérés par un `Executor` pour le vol de tâche et l'ordonnancement. Le vol de tâche permet de redistribuer les tâches entre les threads pour équilibrer la charge de travail et améliorer les performances.

## Analyse des Performances

### Speedup et Scalabilité

Le speedup (`Sp`) est une mesure de l'accélération obtenue grâce au parallélisme. Il est calculé comme suit :

\[ Sp = \frac{T1}{Tp} = \frac{ntot \times ti}{(ntot/p \times ti) + tc} \]

où `T1` est le temps d'exécution avec un processus, `Tp` est le temps d'exécution avec `p` processus, `ntot` est le nombre total de tâches, `ti` est le temps d'exécution d'une tâche, et `tc` est le temps de calcul théorique.

Le speedup permet de quantifier les gains de performance obtenus grâce au parallélisme. Un speedup linéaire signifie que le temps d'exécution est réduit proportionnellement au nombre de processus utilisés. Cependant, des facteurs tels que la synchronisation et la communication entre threads peuvent limiter le speedup.

### Résultats et Interprétation

Les résultats obtenus montrent que la parallélisation n'apporte pas toujours une amélioration linéaire. Des facteurs tels que la synchronisation et la communication entre threads influencent fortement les performances. Par exemple, si les threads passent beaucoup de temps à attendre l'accès à une ressource partagée, les gains de performance seront limités.

Le super linéaire est observé lorsque la charge processeur est très élevée en séquentiel. Dans ce cas, le processeur doit récupérer les informations du tableau dans la RAM. Si le tableau est petit, il le met dans le cache et travaille que dans le cache. Mais si le tableau est très grand, il doit le saucissonner et mettre bout de tableau un par un dans le cache et dans la RAM pour pouvoir le traiter en entier. Les processus font des allers-retours cache-RAM qui prennent du temps. Les workers peuvent chacun avoir un morceau qui rentre dans le cache, ce qui améliore les performances.

## Réutilisation de Composants Logiciels

### Intégration de `pi.java`

La réutilisation de `pi.java` a été justifiée par les expériences précédentes. L'intégration du Monte Carlo de Pi dans le modèle Master Worker a été réalisée en suivant une conception modulaire, permettant d'appeler le code existant de manière efficace. La réutilisation de composants logiciels permet de réduire le temps de développement et d'améliorer la fiabilité du code.

### Validation et Scalabilité

La validation de l'implémentation a été réalisée en variant le nombre de processus et en analysant les résultats obtenus. La scalabilité a été évaluée sur un poste, puis mise en œuvre sur plusieurs postes pour observer les performances globales. La scalabilité est une mesure de la capacité d'un programme à maintenir ses performances lorsque le nombre de processus augmente.

## Exécution Distribuée

### Environnement de Test

L'exécution distribuée a été réalisée sur CentOS, après désactivation du firewall pour permettre les communications entre machines. Les workers ont été exécutés en premier, suivis du master avec les IPs des machines workers. L'exécution distribuée permet de tirer parti des ressources de plusieurs machines pour améliorer les performances.

### Résultats et Latence

L'écart entre les courbes de scalabilité et les résultats obtenus est dû à la latence du réseau, ce qui diffère des résultats obtenus en mémoire partagée. La latence du réseau peut introduire des délais dans la communication entre les machines, ce qui peut limiter les gains de performance. Il est donc important de minimiser la communication entre les machines pour améliorer les performances.

## Conclusion

Ce travail pratique a permis d'explorer en profondeur la programmation parallèle et ses implications sur la performance des algorithmes. Les résultats obtenus montrent que la parallélisation nécessite une analyse fine des algorithmes et une gestion efficace des ressources pour obtenir des gains de performance significatifs. Les concepts théoriques et les implémentations pratiques réalisées ont permis de mieux comprendre les défis et les opportunités offertes par la programmation parallèle.

Ce rapport démontre l'acquisition des compétences nécessaires pour concevoir et implémenter des solutions parallèles efficaces, en tenant compte des contraintes architecturales et des métriques de performance. La programmation parallèle est un domaine complexe qui nécessite une compréhension approfondie des modèles et paradigmes de programmation, ainsi qu'une expérience pratique pour optimiser les performances des programmes.

---

Ce rapport détaillé permet de comprendre les différentes étapes et considérations nécessaires pour implémenter des solutions parallèles efficaces.

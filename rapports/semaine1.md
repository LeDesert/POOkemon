#  Rapport Semaine 1 
____

## Réalisation : 
- Réalisation du plant UML.
- Implémentation de la classe Entite.
- Implémentation des types des Pokémon.
- Possibilité pour les Pokémon de s'attaquer.

____
## Choix de conception

Dans un premier temps, nous avons décidé de réaliser un plan UML pour faciliter le développement du code.
Nous avons opté pour une conception assez avancée dès le début afin de mieux visualiser la direction à prendre.



Nous avons choisi de créer une classe abstraite Entité pour représenter les Pokémon. 
Pour les types, nous avons créé une classe distincte pour chacun d'eux, héritant de la classe Entité. 
Cette approche simplifie l'ajout de nouveaux types et la gestion des avantages et désavantages en fonction des types des Pokémon attaqués. 
Nous avons décidé d'utiliser une chaîne de caractères par défaut pour stocker les forces et faiblesses des différents types.


Pour la gestion des joueurs, nous avons opté pour une classe abstraite Joueur avec une méthode abstraite jouer(), qui sera implémentée dans les classes Humain et Ordinateur. 
Cette approche permet une implémentation différente de la méthode jouer() en fonction du joueur, en plus de faciliter l'ajout de nouveaux joueurs avec des règles de jeu différentes sans modifier le code existant.


___

## Travail effectué 

- Réalisation d'un diagramme de classes.
- possibilité de créer une entité (un Pokemon) en lui attribuant un nom et en choisissant son type. 
  Le reste des attributs peut être généré aléatoirement ou non.
- Gestion des attaques entre Pokémon en fonction de leurs types.

___

## Difficultés rencontrées 

Nous avons toujours des avis mitigés concernant l'utilisation de chaînes de caractères pour la gestion des affinités des types. 

___

## Planification pour le prochain rendu

Pour le prochain rendu, nous prévoyons de réaliser : 
1) La génération aléatoire complète des Pokémon (actuellement limitée aux points de vie et à la force d'attaque).
2) La gestion des mains des joueurs.
3) La gestion des pioches et des défausses.
4) La gestion des terrains.
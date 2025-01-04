# Rapport Semaine 2 



## Réalisation :
- Completion du plant UML.
- Implémentation de la classe Joueur.
- Implémentation des différents types de joueurs (Humain, Ordinateur).
- Génération aléatoire des Pokémon.
- Gestion de la distribution des cartes.


____
## Choix de conception

Nous avons ajusté le diagramme de classe en fonction des problématiques posées avant et pendant la programmation.

Concernant la classe Joueur, nous avons opté pour une classe abstraite afin de faciliter les modifications dans le déroulement des tours. 
Cette conception nous offre également la flexibilité d'ajouter facilement d'autres joueurs avec des règles de tour différentes.

Actuellement, dans la fonction main, nous avons un accès direct aux attributs de toutes les classes. 
Cette approche a été choisie pendant la phase de développement pour faciliter les tests. 
Cependant, nous avons instauré une règle d'encapsulation. 
Dans les prochaines étapes, nous prévoyons d'organiser notre code en packages pour résoudre les problèmes d'encapsulation et renforcer la sécurité.

Nous avons décidé de centraliser la gestion de l'initialisation du jeu dans la classe Joueur afin de réduire la redondance de code 
et d'alléger les autres classes. De même, la génération des cartes présentes dans la main du joueur est effectuée dans cette classe pour éviter les répétitions de code.



___

## Travail effectué

- La génération aléatoire complète des Pokémon.
- La gestion des mains des joueurs.
- La gestion des attaques entre Pokémon en fonction de leurs types.
- La gestion des pioches, des défausses et des terrains.
- Adaptation du diagramme de classes. 
- Possibilité de commencer le jeu.
___

## Difficultés rencontrées

Nous avons rencontré des divergences concernant la façon d'implémenter la gestion des cartes et l'initialisation du jeu.

___

## Planification pour le prochain rendu

Pour le prochain rendu, nous prévoyons de réaliser :
1) Mise en place de l'affichage du jeu.
2) Implémentation de la gestion des tours (permettre de jouer jusqu'à la fin du jeu).
3) Correction des éventuelles erreurs.

___
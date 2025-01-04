# Rapport Semaine 3

## Réalisation :
- Complétion du plan UML.
- Correction de bugs.
- Insertion d'erreurs contrôlées.
- Remplacement des strings pour la gestion d'attaque par des énumérations.
- Début d'implémentation des sorts.

____
## Choix de conception

Nous avons ajusté le diagramme de classe en fonction des problématiques posées avant et pendant la programmation.

Nous avons décidé de faire une interface pour les sorts et non une classe abstraite car dans chacun des sorts, l'implémentation peut beaucoup varier, ce qui simplifie donc la programmation.

Pour implémenter les sorts, nous avons pensé en faire une instance de la classe entité, pour permettre à un Pokémon ou non d'avoir un sort. De plus, il peut en changer plus simplement. Pour l'instant, il n'est pas encore possible de jouer avec les sorts car nous devons encore choisir quand ceux-ci seront intégrés à la boucle de jeu.

___
## Travail effectué

- Correction de bugs.
- Gestion supplémentaire d'erreurs.
- Adaptation du diagramme de classes.
- Début d'intégration des sorts (toujours pas jouable).
- Implémentation du sort Ferveur Guerrière.
- Implémentation de divers sorts de soin : soin de zone, soin permanent, soin simple, soin total.
- Ajout de setters pour répondre à l'implémentation des nouveaux sorts.
___

## Difficultés rencontrées

Nous avons pris du temps pour revoir notre conception et choisir la meilleure façon d'implémenter les futurs sorts dans notre conception du jeu.

## Planification pour le prochain rendu

- Implémentation des nouveaux sorts.
- Possibilité de jouer avec.
___
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * j'ai fais une classe bug juste pour que les todo soit trouvé par netbean
 * @author Nikolai
 */
public class Bugs {
 /*   @todo BUGS

BUG 1 :

Si la grille n'est pas carré ça pose problème 

Probleme de clique avec la liste de bateaux

Ca lag quand la grille est grande et les buttons sont grand
    
    
@todo thomas cf test3 testDao, la liste renvoyé ne contient que des games nulls
    
    
BUG 5 :
    
    penser à MAJ la liste des parties et scores quand ya modif du model
    
    
    
    */
    
    
/*
   @todo Ticket : [[ ticket : 1 ]]

Test n°1 : Creer une partie
Préconditions : Aucune
Scénario :
    Action 1 : Je clique sur le bouton 'Créer Partie'
    Action 2 : J'entre un nom dans le champ 'nom'
    Action 3 : Je choisis l'Epoque XX
    Action 4 : Je choisis la taille de la grille 10
    Action 5 : Je choisis la difficulté randombot
    Action 6 : J'appuie sur le bouton 'valider'
   
Postconditions : La grille est chargee
Conclusion du test :  
Commentaires sur le test:
    Au lancement de l'application, centrer la fenetre
    Specifier la nature des champs pour les listes deroulantes (epoque,...)
Le test est t'il réussi ? OUI
    */    

/* 
   Ticket : [[ ticket : 2 ]]

Test n°2 : Placer les bateaux sur la grille
Préconditions : Avoir cree une partie
Scénario :
    Action 1 : Je clique sur une case de la grille -> un message d'erreur 
               demandant de choisir un bateau s'affiche
    Action 2 : Je clique sur OK pour fermer la fenêtre d'erreur
    Action 3 : Je choisis le bateau 'Porteavion' 
    Action 4 : Je choisis une case -> cette case (de coordonnees (x,y))
               represente le nez du bateau elle est marquee par 'head'
    Action 5 : Je choisis une case ( de coordonnees (x',y')) de la grille 
               (pour la queue du bateau) -> seules les cases qui satisfont         
               la condition ( (x-x')/(y-y') = taille(Porteavion) -> le bateau s'affiche
    Action 6 : Je choisis un 'SousMarin'
    Action 7 : Je choisis une case pour le nez du bateau
    Action 8 : Je choisis une case pour la queue, de maniere a ce que le bateau 
               soit en diagonale
    Action 9 : Je clique sur le bouton 'valider'
    
Postconditions : Les deux grilles s'affichent ainsi que les noms des bateaux places
Conclusion du test : Il est possible de placer des bateaux en diagonale.
Commentaires sur le test:
    
Le test est t'il réussi ? OUI
    */
    
/* 
   Ticket : [[ ticket : 3 ]]

Test n°3 : Placer les bateaux sur la grille
Préconditions : Avoir cree une partie
Scénario :
    Action 1 : Je choisis le bateau 'Porteavion' -> cette case (de coordonnees (x,y))
               represente le nez du bateau elle est marquee par un 'h'
    Action 4 : Je choisis une case pour la queue du bateau. Je place le 
               bateau au milieu de la grille de maiere a la diviser en deux
               -> le bateau s'affiche
    Action 5 : Je choisis un second bateau, Porteavion
    Action 6 : Je choisis une case
    Action 7 : Un message d'erreur d'affiche, "impossible de placer la tete de ce 
               bateau ici"
    
Postconditions :
Conclusion du test : Si la grille ne peut pas contenir un bateau, un erreur est 
                     affichee et les boutons "cliquable" sont regeneres.
Commentaires sur le test:
    
Le test est t'il réussi ? OUI
    */

/* 
   Ticket : [[ ticket : 4 ]]

Test n°4 : Placer les bateaux sur la grille
Préconditions : Avoir cree une partie
Scénario :
    Action 1 : Je choisis le bateau 'Porteavion'
    Action 2 : Je choisis une case-> cette case represente le nez du bateau 
               elle est marquee par un 'head'
    Action 3 : Je choisis une case pour la queue du bateau)-> le bateau s'affiche
    Action 4 : Je choisis un 'Fregate' 
    Action 5 : Pour placer la tete du bateau, je choisis une des cases ou est 
               positionne le Porteavion -> impossible de cliquer sur une de 
               ces cases. Je place le Fregate autre part.
    Action 6 : Je choisis un 'Jetski'
    Action 7 : Les cases ou se trouve le Porte avion deviennent fonctionnelles.
               J'en choisis une, un message d'erreur s'affiche "impossible de 
               placer la tete de ce bateau ici"
    Action 8 : Je clique sur le bouton 'valider'
    
Postconditions :
Conclusion du test : Impossible de placer deux bateaux sur une meme case
Commentaires sur le test:
Le test est t'il réussi ? OUI
    */
    
/* 
   @todo  Ticket : [[ ticket : 5 ]]

Test n°5 : Placer les bateaux sur la grille
Préconditions : Avoir cree une partie
Scénario :
    Action 1 : Je choisis le bateau 'Porteavion'
    Action 2 : Je choisis une case-> cette case represente le nez du bateau 
               elle est marquee par un 'head'
    Action 3 : Je choisis une case pour la queue du bateau)-> le bateau s'affiche
    Action 4 : Je clique sur le bouton 'Retour' -> La page d'acceuil s'affiche,
               les champs ne sont pas reinitialises
    Action 5 : Je clique sur valider
    Action 6 : La grille s'affiche et elle a ete reinitialisee

Postconditions :
Conclusion du test : Il n'y a pas de message d'erreur prevenant que la grille 
                     sera reinitialisee
Commentaires sur le test:
Le test est t'il réussi ? OUI ~
*/
    
/*
   @todo Ticket : [[ ticket : 6 ]]

Test n°6 : Creer une partie
Préconditions : Avoir cree une partie
Scénario :
    Action 1 : Je remplis entierement la grille de bateau
   
Postconditions :
Conclusion du test :  Aucun message d'erreur n'indique que la grille est pleine
Commentaires sur le test: Mettre un message d'erreur
Le test est t'il réussi ? OUI ~
    */    
    
/*
   Ticket : [[ ticket : 7 ]]

Test n°7 : Enregistrer une partie
Préconditions : Avoir cree une partie et place les bateaux
Scénario :
    Action 1 : Je clique sur le menu 'Fichier'
    Action 2 : Je clique sur enregistrer
   
Postconditions :
Conclusion du test : La partie est enregistree
Commentaires sur le test:
Le test est t'il réussi ? OUI ~
    */    
    
/*
   Ticket : [[ ticket : 8 ]]

Test n°8 : Charger une partie
Préconditions : Avoir enregistre une partie
Scénario :
    Action 1 : Je clique sur le bouton 'Charger Partie'
    Action 2 : Je choisis une partie parmi celles enregistrees
   
Postconditions :
Conclusion du test : La partie enregistree s'affiche
Commentaires sur le test:
Le test est t'il réussi ? OUI
    */    
    
/*
   Ticket : [[ ticket : 8 ]]

Test n°8 : Enregistrer une partie puis la charger
Préconditions : Avoir cree une partie et place les bateau
Scénario :
    Action 1 : Je clique sur le menu 'Fichier'
    Action 2 : Je clique sur enregistrer
    Action 3 : Je clique sur le menu 'Fichier
    Action 4 : Je clique sur Accueil -> un message s'affiche : "Voulez-vous enregistrer ?"
    Action 5 : Je clique sur Oui
    Action 6 : Je clique sur 'Charger Partie' 
    
Postconditions : La partie est enregistree
Conclusion du test : 
Commentaires sur le test:
Le test est t'il réussi ? OUI

    */    
   
/*
    Ticket : [[ ticket : 9 ]]

Test n°9 : Charger une partie
Préconditions : Avoir sauvegarde une partie
Scénario :
    Action 1 : Je clique sur le bouton 'Charger Partie' -> La liste des parties 
               sauvegardees 
    Action 2 : Je choisis une partie -> Une fenetre me demande si je veux charger 
               cette partie
    Action 3 : Je clique sur oui
    
Postconditions : La partie selectionnee s'affiche
Conclusion du test : 
Commentaires sur le test:
Le test est t'il réussi ? OUI    
*/
    
/*
  @todo  Ticket : [[ ticket : 10 ]]

Test n°10 : Charger une partie
Préconditions : Avoir sauvegarde une partie
Scénario :
    Action 1 : Je clique sur le bouton 'Charger Partie' -> La liste des parties 
               sauvegardees 
    Action 2 : Je choisis une partie -> Une fenetre me demande si je veux charger 
               cette partie
    Action 3 : Je clique sur oui
    Action 4 : Je clique sur le menu 'Fichier'
    Action 5 : Je clique sur Acceuil
    Action 6 : Je clique sur le bouton 'Créer Partie'
    Action 7 : J'entre un nom dans le champ 'nom'
    Action 8 : Je choisis l'Epoque XX
    Action 9 : Je choisis la taille de la grille 10
    Action 10 : Je choisis la difficulté randombot
    Action 11 : J'appuie sur le bouton 'valider'
    Action 12 : Je place les bateaux
    Action 13 : Je valide
Postconditions : 
Conclusion du test : La grille affichee n'est pas celle cree mais celle chargee 
Commentaires sur le test:
Le test est t'il réussi ? NON
*/    
    

/*
    Ticket : [[ ticket : 11 ]]

Test n°11 : Voir les scores
Préconditions : Avoir sauvegarde une partie
Scénario :
    Action 1 : Je clique sur le bouton 'Voir scores'
   
Postconditions : La grille est chargee
Conclusion du test :  
Commentaires sur le test:
Le test est t'il réussi ? OUI
    */    
    
    
    /*
    @todo bug 12
    grille 10*10: si bateau de taille max -> boucle 
    */
}

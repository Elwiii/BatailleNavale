/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * j'ai fais une classe bug juste pour que les todo soit trouvé par netbean
 *
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
     Ticket : [[ ticket : 12 ]]

     Test n°12 : Enregistrer une partie puis la charger
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
     @todo Ticket : [[ ticket : 13 ]]

     Test n°13 : Jouer une partie
     Préconditions : 
     Scénario :
     Action 1 : Je crée une partie avec un seul bateau
     Action 2 : Je valide la grille
     Action 3 : Je choisis mon bateau
     Action 4 : Je clique sur une case de la grille adverse
    
     Postconditions : La partie est bloquee
     Conclusion du test : On ne peut plus rien faire
     Commentaires sur le test:
     Le test est t'il réussi ? NON
 
     */
    
    /*
      @todo Ticket : [[ ticket : 14 ]]

     Test n°14 : Jouer une partie
     Préconditions : Avoir cree une partie (difficulte CROSSBOT, taille 5,
                     epoque XVIII) et place les bateaux
     Scénario :
     Action 1 : Je place deux bateaux (barque et transport)
     Action 2 : Je clique sur une case de la grille adverse 
    
     Postconditions : Grille bloquee
     Conclusion du test : Une java.lang.NullPointerException est levee
     Commentaires sur le test: 
     Le test est t'il réussi ? NON BUG!!!!
    
        !!!! Le crossbot bug !!!!
     */
    
    
    /*
      @todo Ticket : [[ ticket : 15 ]]

     Test n°15 : Jouer une partie
     Préconditions : Avoir cree une partie (difficulte RANDOMBOT, taille 5,
                     epoque XX)
     Scénario :
     Action 1 : Je place deux bateaux (fregate et jetski)
     Action 2 : Je clique sur une case de la grille adverse 
    
     Postconditions : Apres avoir decouvert les bateaux aucune info n'est donnee
     Conclusion du test : 
     Commentaires sur le test: 
     Le test est t'il réussi ? NON BUG!!!!
     */
    
    /*
    @todo ticket 16
    Epoque XX, Taille 20, Difficultee RandomBot
    Epoque sciencefiction, Taille 10, Difficultee RandomBot
    Apres avoir joue plusieurs coups, la grille est bloquee
    Quand je recharge la partie, la grille est debloquee
    cf. Repertoire Bugs -> Image map2, map3
    */
    
    
     /*
      Ticket : [[ ticket : 17 ]]

     Test n°17 : Jouer une partie
     Préconditions : Avoir cree une partie (Epoque: XVIII, Taille: 5, 
                    Difficulté: Poseidon, Bateaau: Barque) 
     Scénario :
     Action 1 : Je choisis le bateau qui tire (Barque)
     Action 2 : Je clique sur une case de la grille adverse --> j'ai touché le bâteau 
                adverse
     Action 3 : Je répéte l'Action 2
    
     Postconditions : Deux cas se présentent:
                        1. A chaque coup je touche le bâteau adverse --> quand tout 
                            bâteau a été touché, la partie est gagnée. Trois actions
                            sont proposées, 'Voir les scores', 'Rejouer', 'Quitter'
                        2. Au deuxième coup je ne touche pas le bâteau adverse -->
                            
     Conclusion du test : 
     Commentaires sur le test: 
     Le test est t'il réussi ? OUI
     */
    
    
    /*
      @todo Ticket : [[ ticket : 18 ]]

     Test n°18 : Voir les scores
     Préconditions : Avoir gagné une partie (un pop-up s'affiche)
     Scénario :
     Action 1 : Je choisis de 'Voir les scores'
     Action 2 : Je clique sur OK
    
     Postconditions : La grille des scores s'affiche.
     Conclusion du test : 
     Commentaires sur le test: 
     Le test est t'il réussi ? OUI
     */

    /*
    @todo BUG 19
    Si le répertoire Game n'ets pas créé, une exception est levée.
    */

    /*
    @todo BUG 20
    On peut crérer une partie sans nom
    */
    
    /*
    @todo BUG 21
    Epoque: XVIII, Taille: 5, Difficulté: Poseidon ---> Bugs/map4
    La grille bug impossible de jouer.
    IMPOSSIBLE d'enregistrer la partie via Fichier->Enregistrer
    Je reviens sur Acceuil, on me propose de l'enregistrer.
    Je la charge et je peux continuer à jouer.
    
    ERREURS:
    
    
    Poseidon a tiré en (2,0) et a eu le resultat suivant  : DESTROYED_SHIP
Poseidon a tiré en (0,1) et a eu le resultat suivant  : UNREACHABLE
model.getState() : JOUEUR2
avr. 20, 2014 12:48:13 PM view.JPanelJouer$JButtonFire$1 actionPerformed
SEVERE: null
java.lang.ArrayIndexOutOfBoundsException: -1
Etat du modèle après le tir de l'humain : JOUEUR1
flotte du bot : [TRANSPORT, TRANSPORT]
	at java.util.ArrayList.elementData(ArrayList.java:371)
	at java.util.ArrayList.get(ArrayList.java:384)
	at model.Flotte.fire(Flotte.java:44)
	at model.player.Human.fire(Human.java:28)
	at model.BatailleNavale.fire(BatailleNavale.java:97)
	at view.JPanelJouer$JButtonFire$1.actionPerformed(JPanelJouer.java:92)
	at javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:2018)
	at javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2341)
	at javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:402)
	at javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:259)
	at javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:252)
	at java.awt.Component.processMouseEvent(Component.java:6505)
	at javax.swing.JComponent.processMouseEvent(JComponent.java:3321)
	at java.awt.Component.processEvent(Component.java:6270)
	at java.awt.Container.processEvent(Container.java:2229)
	at java.awt.Component.dispatchEventImpl(Component.java:4861)
	at java.awt.Container.dispatchEventImpl(Container.java:2287)
	at java.awt.Component.dispatchEvent(Component.java:4687)
	at java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4832)
	at java.awt.LightweightDispatcher.processMouseEvent(Container.java:4492)
	at java.awt.LightweightDispatcher.dispatchEvent(Container.java:4422)
	at java.awt.Container.dispatchEventImpl(Container.java:2273)
	at java.awt.Window.dispatchEventImpl(Window.java:2719)
	at java.awt.Component.dispatchEvent(Component.java:4687)
	at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:729)
	at java.awt.EventQueue.access$200(EventQueue.java:103)
	at java.awt.EventQueue$3.run(EventQueue.java:688)
	at java.awt.EventQueue$3.run(EventQueue.java:686)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:76)
	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:87)
	at java.awt.EventQueue$4.run(EventQueue.java:702)
	at java.awt.EventQueue$4.run(EventQueue.java:700)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:76)
	at java.awt.EventQueue.dispatchEvent(EventQueue.java:699)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:242)
	at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:161)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:150)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:146)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:138)
	at java.awt.EventDispatchThread.run(EventDispatchThread.java:91)
    */


    /*
    @todo BUG 22
    
    */
    
    /*
    @todo BUG 
    */
}


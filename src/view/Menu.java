/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Thomas
 */
public class Menu {
    /* barre de menu */
    private JMenuBar menu_bar = new JMenuBar();
    /* menu */
    private JMenu menu = new JMenu("Menu");
    /* differents choix de chaque menu */
    private JMenuItem newGameMenu = new JMenuItem("Créer Partie");
    private JMenuItem loadGameMenu = new JMenuItem("Charger Partie");
    private JMenuItem scoreMenu = new JMenuItem("Voir les scores");
    private JMenuItem quitterPartieMenu = new JMenuItem("Quitter la partie");
    private JMenuItem quitterMenu = new JMenuItem("Quitter");
    
    public Menu(){
        /* Ajouter les choix au menu  */
        menu.add(newGameMenu);
        menu.add(loadGameMenu);
        menu.addSeparator();
        menu.add(scoreMenu);
        menu.addSeparator();
        menu.add(quitterPartieMenu);
        menu.add(quitterMenu);
                        /* Ajouter les menu sur la bar de menu */
        menu_bar.add(menu);
    }
    
    public void add(JFrame frame){
        frame.setJMenuBar(menu_bar);
    }
    
}

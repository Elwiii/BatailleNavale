/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Thomas
 */
public class Accueil {
    
    public static final int NEW = -100;
    public static final int LOAD = -200;
    public static final int SCORE = -300;
    
    private JFrame frame = new JFrame("Acceuil");
    private JPanel[] panels = new JPanel[4];
    /* barre de menu */
    JMenuBar menu_bar = new JMenuBar();
    /* menu */
    JMenu menu = new JMenu("Menu");
    /* differents choix de chaque menu */
    private JMenuItem newGameMenu = new JMenuItem("Créer Partie");
    private JMenuItem loadGameMenu = new JMenuItem("Charger Partie");
    private JMenuItem scoreMenu = new JMenuItem("Voir les scores");
    private JMenuItem quitterPartieMenu = new JMenuItem("Quitter la partie");
    private JMenuItem quitterMenu = new JMenuItem("Quitter");
    /* differents boutons */
    private JButton newGameButton = new JButton("Créer une partie");
    private JButton loadGameButton = new JButton("Charger une partie");
    private JButton scoreButton = new JButton(" Voir les scores ");
    
    public Accueil(){
        buildFrame();
    }
    
    public void buildFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = (JPanel) frame.getContentPane();
        
        frame.setSize(640, 480);
        
        // initialize panels 
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }
        
        /* Création des composants */                
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
                        /* Ajouter la bar du menu à la frame */
        frame.setJMenuBar(menu_bar);

        // layout = FlowLayout.CENTER
        panels[1].setLayout(new FlowLayout(FlowLayout.CENTER));
        panels[1].add(newGameButton);
        newGameButton.addActionListener(new Accueil.LocalListener(NEW));
        
        // layout = FlowLayout.CENTER 
        panels[2].setLayout(new FlowLayout(FlowLayout.CENTER));
        panels[2].add(loadGameButton);
        loadGameButton.addActionListener(new Accueil.LocalListener(LOAD));

        
        // layout = FlowLayout.CENTER 
        panels[3].setLayout(new FlowLayout(FlowLayout.CENTER));
        panels[3].add(scoreButton);
        scoreButton.addActionListener(new Accueil.LocalListener(SCORE));
               
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        for (JPanel jPanel : panels) {
            contentPanel.add(jPanel);
        }

        //frame.pack();
        frame.setVisible(true);
        
        
    }
    
    class LocalListener implements ActionListener {

        private int digit;

        public LocalListener(int digit) {
            this.digit = digit;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    public static void main (String[] args){
        Accueil a = new Accueil();
    }
    
}

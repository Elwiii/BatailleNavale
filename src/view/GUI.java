/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.MoveToPanelListener;
import control.QuitListener;
import control.SaveListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.BatailleNavale;
import persistance.DaoFactoryException;
import persistance.PersistanceException;

/**
 *
 * @author nikolai
 */
public class GUI extends JFrame {

    private static GUI instance = null;

    public static GUI getInstance() {
        if (instance == null) {
            try {
                instance = new GUI();
            } catch (DaoFactoryException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    private final JPanelWizard cards;
    private final BatailleNavale model;
    private final JMenuItem save;

    private GUI() throws DaoFactoryException {
        super("bataille navale");

        model = new BatailleNavale();

         //Create the panel that contains the "cards".
        cards = new JPanelWizard(model);

        add(cards, BorderLayout.CENTER);
  
         // les menus
        //Create the menu bar.
        JMenuBar menuBar = new JMenuBar();

        //Build the first menu.
        JMenu menu = new JMenu("Fichier");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        
        
        JMenuItem acceuil = new JMenuItem("acceuil");
        acceuil.addActionListener(new MoveToPanelListener(model, cards, JPanelAcceuil.id));
        menu.add(acceuil);
        

        JMenuItem newGame = new JMenuItem("nouvelle partie");
        newGame.addActionListener(new MoveToPanelListener(model, cards, JPanelCreer.id));
        menu.add(newGame);
        
        
        save = new JMenuItem("enregistrer");
        save.setEnabled(false);
        save.addActionListener(new SaveListener(model));
        menu.add(save);

        JMenuItem load = new JMenuItem("charger");
        load.addActionListener(new MoveToPanelListener(model, cards, JPanelParties.id));
        menu.add(load);

        JMenuItem score = new JMenuItem("score");
        score.addActionListener(new MoveToPanelListener(model, cards, JPanelScore.id));
        menu.add(score);
        
        
        JMenuItem quitter = new JMenuItem("quitter");
        quitter.addActionListener(new QuitListener(model, cards));
        menu.add(quitter);

        
        setJMenuBar(menuBar);
        //finalisation de la JFrame
//        setPreferredSize(new Dimension(650, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void updateMenu() {
        switch (cards.getCurrentPanelId()) {
            case JPanelJouer.id:
                save.setEnabled(true);
                break;
            default:
                save.setEnabled(false);
                break;
        }
    }

    public static void main(String[] args) {
        GUI.getInstance();
    }
}

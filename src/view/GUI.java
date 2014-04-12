/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
//    private final BatailleNavaleAdapter model_adapter;
    private final BatailleNavale model;
    private final JMenuItem save;

    private GUI() throws DaoFactoryException {
        super("bataille navale");
//        model_adapter = new BatailleNavaleAdapter();

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
        acceuil.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cards.getCurrentPanel().equals(JPanelJouer.id)) {
                    int n = JOptionPane.showConfirmDialog(
                            GUI.getInstance(),
                            "Voulez vous enregistrer ?",
                            "",
                            JOptionPane.YES_NO_OPTION);
                    switch (n) {
                        case 0:
                            try {
                                model.save();
                            } catch (PersistanceException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                }
                cards.show(JPanelAcceuil.id);
            }
        });
        
        menu.add(acceuil);
        
        
        save = new JMenuItem("enregistrer");
        save.setEnabled(false);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Saving ...");
                try {
                    model.save();
                } catch (PersistanceException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu.add(save);
        JMenuItem quitter = new JMenuItem("quitter");
        quitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int n;
                System.out.println("cards : " + cards);
                switch (cards.getCurrentPanel()) {
                    case JPanelJouer.id:
                        n = JOptionPane.showConfirmDialog(
                                GUI.getInstance(),
                                "Sauvergarder avant de quitter ?",
                                "",
                                JOptionPane.YES_NO_OPTION);
                        switch (n) {
                            case 0:
                                try {
                                    model.save();
                                } catch (PersistanceException ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;
                        }
                        System.exit(0);
                        break;
                    default:
                        n = JOptionPane.showConfirmDialog(
                                GUI.getInstance(),
                                "Voulez vous vraiment quitter l'application ?",
                                "",
                                JOptionPane.YES_NO_OPTION);
                        switch (n) {
                            case 0:
                                System.exit(0);
                        }
                        break;
                }
            }
        });
        menu.add(quitter);

        
        setJMenuBar(menuBar);
        //finalisation de la JFrame
        setPreferredSize(new Dimension(650, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void updateMenu() {
        switch (cards.getCurrentPanel()) {
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

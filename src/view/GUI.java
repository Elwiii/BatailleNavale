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
//        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        JMenuItem save = new JMenuItem("enregistrer");
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
        menu.add(quitter);
        setJMenuBar(menuBar);
        //finalisation de la JFrame
        setPreferredSize(new Dimension(650, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        GUI.getInstance();
    }
}

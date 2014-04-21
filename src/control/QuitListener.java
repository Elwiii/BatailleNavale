/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.BatailleNavale;
import persistance.PersistanceException;
import view.GUI;
import view.JPanelJouer;
import view.JPanelWizard;

/**
 *
 * @author Nikolai
 */
public class QuitListener implements ActionListener {

    private final JPanelWizard cards;
    private final BatailleNavale model;

    public QuitListener(final BatailleNavale model, final JPanelWizard wizard) {
        this.model = model;
        this.cards = wizard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n;
        switch (cards.getCurrentPanelId()) {
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
}

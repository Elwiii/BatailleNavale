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
import view.JPanelAcceuil;
import view.JPanelJouer;
import view.JPanelWizard;

/**
 *
 * @author Nikolai
 */
public class BackToAcceuilListener implements ActionListener{

        private final JPanelWizard cards;
    
    private final BatailleNavale model;
    
    public BackToAcceuilListener(BatailleNavale model, JPanelWizard wizard){
        this.model = model;
        this.cards = wizard;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
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
    
}

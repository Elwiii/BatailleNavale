/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.MoveToPanelListener;
import javax.swing.JButton;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JButtonBackToAcceuil extends JButton {

    public JButtonBackToAcceuil(final BatailleNavale model,final JPanelWizard wizard) {
        super("retour");

        addActionListener(new MoveToPanelListener(model, wizard, JPanelAcceuil.id));

    }
}
    
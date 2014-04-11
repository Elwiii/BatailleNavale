/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author nikolai
 */
public class JButtonBackToAcceuil extends JButton {

    public JButtonBackToAcceuil(final JPanelWizard wizard) {
        super("retour");

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelAcceuil.id);
            }
        });
    }
}
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
import model.BatailleNavale;
import persistance.PersistanceException;
import view.GUI;

/**
 *
 * @author Nikolai
 */
public class SaveListener implements ActionListener {

    private final BatailleNavale model;

    public SaveListener(final BatailleNavale model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            model.save();
        } catch (PersistanceException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JPanelCreer extends JPanel implements Observer{

    public static final String id = "jpanelcreer";
    private final JTextField nom;
    private final JComboBox epoque;
    private final JComboBox longueur;
    private final JComboBox largeur;
    
    public JPanelCreer(final BatailleNavale model,final JPanelWizard wizard){
        super();
        model.addObserver(this);
        add(new JLabel(id));
        nom = new JTextField("nom");
        add(nom);
        add(new JButtonBackToAcceuil(wizard));
        epoque = new JComboBox();
        longueur = new JComboBox();
        largeur =new JComboBox();
        add(epoque);
        add(longueur);
        add(largeur);
        
        JButton valider = new JButton("valider");
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelPlacement.id);
            }
        });
        add(valider);
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

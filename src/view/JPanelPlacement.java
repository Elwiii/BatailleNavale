/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.BatailleNavaleAdapter;

/**
 *
 * @author nikolai
 */
public class JPanelPlacement extends JPanel implements Observer{
    
    public static final String id = "jpanelplacement";
    
    public JPanelPlacement(final BatailleNavaleAdapter model,final JPanelWizard wizard){
        super(new BorderLayout());
        model.addObserver(this);
        add(new JLabel(id));
        JPanel south = new JPanel();
        JButton backToCreer = new JButton("retour");
        backToCreer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelCreer.id);
            }
        });
        south.add(backToCreer);
        
        JButton valider =new JButton("valider");
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelJouer.id);
            }
        });
        south.add(valider);
        add(south, BorderLayout.SOUTH);
        JPanel grille = new JPanel(new GridLayout(10,10/* à recup sur model)*/));
        for (int i = 0; i < 100; i++) {
            grille.add(new JButtonPlacementBateau());
            
        }
        
        add(grille, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

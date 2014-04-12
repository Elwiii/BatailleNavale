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
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JPanelAcceuil extends JPanel implements Observer{
    
    public static final String id = "jpanelacceuil";

    public JPanelAcceuil(final BatailleNavale model,final JPanelWizard wizard){
        super();
        model.addObserver(this);
//        add(new JLabel(id));
        JButton creerPartie = new JButton("Créer Partie");
        creerPartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelCreer.id);
            }
        });
        add(creerPartie);
        
        JButton chargerPartie = new JButton("Charger Partie");
        chargerPartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelParties.id);
            }
        });
        add(chargerPartie);
        
        JButton voirScore = new JButton("Voir scores");
        voirScore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wizard.show(JPanelScore.id);
            }
        });
        add(voirScore);
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
        System.out.println("UPDATE JPANELACCUEIL");
    }
}
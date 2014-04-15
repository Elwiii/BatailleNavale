/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import control.MoveToPanelListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
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

        JButton creerPartie = new JButton("Créer Partie");
        creerPartie.addActionListener(new MoveToPanelListener(model, wizard, JPanelCreer.id));
        add(creerPartie);
        
        JButton chargerPartie = new JButton("Charger Partie");
        chargerPartie.addActionListener(new MoveToPanelListener(model, wizard, JPanelParties.id));
        add(chargerPartie);
        
        JButton voirScore = new JButton("Voir scores");
        voirScore.addActionListener(new MoveToPanelListener(model, wizard, JPanelScore.id));
        add(voirScore);
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
    }
}
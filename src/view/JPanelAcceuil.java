/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.MoveToPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JPanelAcceuil extends JPanel implements Observer {

    public static final String id = "jpanelacceuil";

    public JPanelAcceuil(final BatailleNavale model, final JPanelWizard wizard) {
        super(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(3, 1));
        model.addObserver(this);

        JButton creerPartie = new JButton("Créer Partie");
        creerPartie.addActionListener(new MoveToPanelListener(model, wizard, JPanelCreer.id));
        buttons.add(creerPartie);

        JButton chargerPartie = new JButton("Charger Partie");
        chargerPartie.addActionListener(new MoveToPanelListener(model, wizard, JPanelParties.id));
        buttons.add(chargerPartie);

        JButton voirScore = new JButton("Voir scores");
        voirScore.addActionListener(new MoveToPanelListener(model, wizard, JPanelScore.id));
        buttons.add(voirScore);
        
        buttons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Bienvenue", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));

//        buttons.setSize(new Dimension(100,100));
//        buttons.setMaximumSize(new Dimension(20,20));
        add(buttons, BorderLayout.CENTER);
        
        
    }

    @Override
    public void update(Observable o, Object o1) {
    }
}

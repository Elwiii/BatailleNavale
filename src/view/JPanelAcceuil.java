/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.MoveToPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
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
//        SpringLayout sl = new SpringLayout();
        JPanel buttons = new JPanel(new GridLayout(3,1)/*sl*/);
//        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        model.addObserver(this);
//buttons.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        JButton creerPartie = new JButton("Créer Partie");
//        creerPartie.setOpaque(false);
        creerPartie.addActionListener(new MoveToPanelListener(model, wizard, JPanelCreer.id));
        buttons.add(creerPartie);
//        sl.putConstraint(SpringLayout.NORTH, creerPartie, 50, SpringLayout.NORTH, buttons);
        
        
        JButton chargerPartie = new JButton("Charger Partie");
        chargerPartie.addActionListener(new MoveToPanelListener(model, wizard, JPanelParties.id));
        buttons.add(chargerPartie);

        JButton voirScore = new JButton("Voir scores");
        voirScore.addActionListener(new MoveToPanelListener(model, wizard, JPanelScore.id));
        buttons.add(voirScore);
        
        buttons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Bienvenue", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));

//        setSize(new Dimension(100,100));
//        buttons.setMaximumSize(new Dimension(20,20));
        add(buttons, BorderLayout.CENTER);
//        SpringUtilities.makeCompactGrid(buttons, 1,
//                                buttons.getComponentCount(),
//                                6, 6, 6, 6);
        
//        SpringUtilities.makeCompactGrid(buttons,
//                                1,3, //rows, cols
//                                50, 50,        //initX, initY
//                                6, 6);       //xPad, yPad
        
    }

    @Override
    public void update(Observable o, Object o1) {
    }
}

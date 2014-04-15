/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import model.BatailleNavale;
import model.player.Difficulty;
import model.ship.Epoque;
import persistance.PersistanceException;

/**
 *
 * @author nikolai
 */
public class JPanelCreer extends JPanel implements Observer {

    public static final String id = "jpanelcreer";
    private final JTextField nom;
    private final JComboBox epoque;
    private final JComboBox longueur;
    private final JComboBox largeur;
    private final JComboBox difficulty;
    private final String[] longeurs = {"5", "10", "20"};
    private final String[] largeurs = {"5", "10", "20"};//todo dans config
    private int lon;
    private int lar;
    private Epoque epo;
    private Difficulty diff;

    public JPanelCreer(final BatailleNavale model, final JPanelWizard wizard) {
        super(new BorderLayout());
        JPanel panelOption = new JPanel(new GridLayout(4,1));
        JPanel south = new JPanel(new GridLayout(1, 2));
        model.addObserver(this);

        nom = new JTextField("votre pseudo");
        panelOption.add(nom);

        south.add(new JButtonBackToAcceuil(model, wizard), BorderLayout.SOUTH);
        epoque = new JComboBox(Epoque.values());
        epoque.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                epo = (Epoque) cb.getSelectedItem();
            }
        });
        epoque.setSelectedIndex(0);
        difficulty = new JComboBox(Difficulty.values());
        difficulty.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                diff = (Difficulty) cb.getSelectedItem();
            }
        });
        difficulty.setSelectedIndex(3);
        longueur = new JComboBox(longeurs);
        longueur.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                lon = Integer.parseInt((String) cb.getSelectedItem());
            }
        });
        longueur.setSelectedIndex(0);
        largeur = new JComboBox(largeurs);
        largeur.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                lar = Integer.parseInt((String) cb.getSelectedItem());
            }
        });
        largeur.setSelectedIndex(0);
        panelOption.add(epoque);
        panelOption.add(longueur);
//        panelOption.add(largeur); @todo
        panelOption.add(difficulty);

        JButton valider = new JButton("valider");
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
//                System.out.println("Ep : " + epo + " la : " + lar + "lon " + lon + " diff : " + diff);
                model.setDifficulty(diff);
                model.setPseudoHumun(nom.getText());
                model.setLongeurGrille(lon);
                model.setLargeurGrille(/*@todo*/lon/*lar*/);
                try {
                    model.constructPlayers();
                } catch (Exception ex) {
                    Logger.getLogger(JPanelCreer.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    model.newGame();
                } catch (PersistanceException ex) {
                    Logger.getLogger(JPanelCreer.class.getName()).log(Level.SEVERE, null, ex);
//                    System.out.println("NEW GAME ERREUR");
                }
                wizard.getJpanelPlacement().constuctGrille(lon, lon/*lar @todo*/);
                wizard.getJpanelPlacement().constructList(epo);
                wizard.show(JPanelPlacement.id);
//                System.out.println("model : "+model.getPseudoHumun());
            }
        });
        south.add(valider);
        add(panelOption, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        nom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Pseudo", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));
        longueur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Taille", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));
        epoque.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Longeur", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));
        difficulty.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Difficultée", TitledBorder.LEFT, TitledBorder.TOP, new Font(Font.SERIF, Font.ITALIC, 16), Color.GRAY));

    }

    @Override
    public void update(Observable o, Object o1) {
    }

}

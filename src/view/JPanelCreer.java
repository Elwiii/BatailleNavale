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
import model.player.Difficulty;
import model.ship.Epoque;

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
    private final String[] longeurs = {"5", "10", "20", "40", "80"};
    private final String[] largeurs = {"5", "10", "20", "40", "80"};
    private int lon;
    private int lar;
    private Epoque epo;
    private Difficulty diff;

    public JPanelCreer(final BatailleNavale model, final JPanelWizard wizard) {
        super();
        model.addObserver(this);
        add(new JLabel(id));
        nom = new JTextField("nom");
        add(nom);
//        nom.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
        add(new JButtonBackToAcceuil(wizard));
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
        difficulty.setSelectedIndex(0);
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
        add(epoque);
        add(longueur);
        add(largeur);
        add(difficulty);

        JButton valider = new JButton("valider");
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
//                System.out.println("Ep : " + epo + " la : " + lar + "lon " + lon + " diff : " + diff);
                model.setDifficulty(diff);
                model.setPseudoHumun(nom.getText());
                model.setLongeurGrille(lon);
                model.setLargeurGrille(lar);
                model.constructPlayers();
                wizard.getJpanelPlacement().constuctGrille(lon, lar);
                wizard.getJpanelPlacement().constructList(epo);
                wizard.show(JPanelPlacement.id);
                System.out.println("model : "+model.getPseudoHumun());
            }
        });
        add(valider);

    }

    @Override
    public void update(Observable o, Object o1) {
    }

}

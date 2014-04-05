/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import model.BatailleNavale;
import model.Coordinate;
import model.ship.Ship;
import model.ship.Ship.Etat;

/**
 * @todo thomas
 * @author nikolai
 */
public class JPanelJouer extends JPanel implements Observer {

    public static final String id = "jpaneljouer";
    
    private JPanel grilleEnnemi;
    private JPanel grilleFlotte;
    private final BatailleNavale model;

    public JPanelJouer(final BatailleNavale model) {
        super(new BorderLayout());
        model.addObserver(this);
        this.model = model;
        

    }
    
    public void initialize(){
        add(new JLabel(id));
        JPanel center = new JPanel();
        grilleEnnemi = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        grilleFlotte = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        for (int i = 0; i < model.getLargeurGrille(); i++) {
            for (int j = 0; j < model.getLongeurGrille(); j++) {
                grilleEnnemi.add(new JButtonFire(model, new Coordinate(i, j)));
            }
        }
        for (int i = 0; i < model.getLongeurGrille() * model.getLargeurGrille(); i++) {
            JButton j = new JButton("b");
            j.setEnabled(false);
            grilleFlotte.add(j);

        }
        center.add(grilleEnnemi);
        center.add(grilleFlotte);
        add(center, BorderLayout.CENTER);
        
        
        JList list = new JList(); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        add(list,BorderLayout.EAST);
        
        updateGrilleEnnemi();
        updateGrilleFlotte();
    }
    
    private void updateGrilleEnnemi(){
        
    }
    
    private void updateGrilleFlotte(){
//        grilleFlotte.removeAll();
        for(Ship s : model.getJ1().getFlotte().getVaisseaux()){
            for(Etat e : s.getEtats()){
                JButton b = new JButton();
                b.setEnabled(false);
                String t = "";
                switch(e.getEtat()){
                    case Ship.DAMAGED :
                        t = "X";
                        break;
                    case Ship.SAFE :
                        t = "";
                        break;
                }
                b.setText(t);
                b.setBackground(s.getRepresentationGraphique());
                System.out.println("x : "+e.getC().x);
                System.out.println("y : "+e.getC().y);
                int pos = e.getC().y+ model.getLargeurGrille() * e.getC().x;
                System.out.println("pos : "+pos);
                grilleFlotte.remove(pos);
                grilleFlotte.add(b,pos);
            }
        }
        
    }

    @Override
    public void update(Observable o, Object o1) {
    }
}

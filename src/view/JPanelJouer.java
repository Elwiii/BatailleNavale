/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.BatailleNavale;
import model.Coordinate;
import model.Flotte;
import model.ship.Epoque;
import model.ship.Ship;
import model.ship.Ship.Etat;
import model.ship.TypeShip;

/**
 * @todo thomas
 * @author nikolai
 */
public class JPanelJouer extends JPanel implements Observer {

    public static final String id = "jpaneljouer";
    
    private JPanel grilleEnnemi;
    private JPanel grilleFlotte;
    private final BatailleNavale model;
    private JList list;
    private List<JButtonFire> listTir;
    private final int SHIP_SELECTED = 0;
    private final int HEAD_SELECTED = 1;
    private final int TAIL_SELECTED = 2;
    private final int NOTHING_SELECTED = 3;
    private int state = NOTHING_SELECTED;
    private Ship selectedShip;

    
//    public class JButtonFire extends JButton implements Observer{
//        private final Coordinate c;
//
//        public JButtonFire(final BatailleNavale model,final Coordinate c){
//            super("?");
//            this.c  = c;
//            model.addObserver(this);
//            addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent ae) {
//                    switch (state) {
//                        case NOTHING_SELECTED:
//                            break;
//                        case SHIP_SELECTED:                            
//                            if(selectedShip.estAporteeDeTir(getC())){
//                                try {
//                                    selectedShip.fire(model.getJ2().getFlotte(), c);
//                                    System.out.println("TIR");
//                                } catch (Exception ex) {
//                                    Logger.getLogger(JPanelJouer.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                            else
//                                System.out.println("TROP LOIN");
//                              
//                                 
//                            
//                    }
//                        
//                    System.out.println(""+c);
//                    System.out.println(""+model.getJ1().getFlotte().getVaisseaux().get(0));
//                    System.out.println("state = "+state);
//                }
//            });
//        }
//        
//        public Coordinate getC(){
//            return this.c;
//        }
//
//        @Override
//        public void update(Observable o, Object o1) {
//        }
//
//    }

    public JPanelJouer(final BatailleNavale model) {
        super(new BorderLayout());
        model.addObserver(this);
        this.model = model;
        listTir = new ArrayList<>();
    }
    
    public void initialize(){
        add(new JLabel(id));
        JPanel center = new JPanel();
        grilleEnnemi = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        grilleFlotte = new JPanel(new GridLayout(model.getLargeurGrille(), model.getLongeurGrille()));
        for (int i = 0; i < model.getLargeurGrille(); i++) {
            for (int j = 0; j < model.getLongeurGrille(); j++) {
                JButtonFire jbf = new JButtonFire(model, new Coordinate(i, j));
                grilleEnnemi.add(jbf);
                listTir.add(jbf);
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
        
//        Ship[] tabShip = new Ship[model.getJ1().getFlotte().getVaisseaux().size()];
//        int i = 0;
//        for(Ship s : model.getJ1().getFlotte().getVaisseaux()){
//            tabShip[i]=s;
//            i++;
//        }
//        final JList list = new JList(tabShip); //data has type Object[]
//        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        list.setVisibleRowCount(-1);
//        JScrollPane listScroller = new JScrollPane(list);
//        listScroller.setPreferredSize(new Dimension(250, 80));
//              list.addListSelectionListener(new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println("BATEAU CHOISI!");
//                selectedShip = (Ship) list.getSelectedValue();
//                state = SHIP_SELECTED;
//                System.out.println("clique flotte ");
//                for(JButtonFire jbf : listTir){
//                if(selectedShip.estAporteeDeTir(jbf.getC())){
//                    jbf.setEnabled(true);
//                }
//                else
//                    jbf.setEnabled(false);
//
//            }
//            }
//        });
//        
//        add(list,BorderLayout.EAST);
        
        updateGrilleEnnemi();
        updateGrilleFlotte();
        //updateList();
        
        

    }
    
    private void updateGrilleEnnemi(){
        System.out.println("UPDATE GRILLE ENNEMI");
        for(JButtonFire jbf : listTir){
            for(Ship s : model.getJ1().getFlotte().getVaisseaux())
            {
                if(s.estAporteeDeTir(jbf.getC())){
                    jbf.setEnabled(true);
                    System.out.println("OUI");
                }
                else{
                    jbf.setEnabled(false);
                }
            }
        }
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
//                updateList();
            }
        }
        
    }
    
    public void updateList(){
        Flotte f = model.getJ1().getFlotte();
        if (list != null) {
            remove(list);
        }
//        list.setSelectedIndex(0);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
  
    }
    
//    private List<JButtonFire> tailsImpossibles(Ship s) {
//        List<JButtonFire> listTir = new ArrayList<>();
//        for(int i = 0; i<model.getLargeurGrille();i++){
//            for(int j=0;j<model.getLongeurGrille();j++){
//                if(!(s.estAporteeDeTir(new Coordinate(i,j)))){
//                    listTir.add(grilleEnnemi[i][j]);
//                    grilleEnnemi.remove(new JButtonFire(new Coordinate(i,j)));
//                }
//            }
//        }
//    }


    @Override
    public void update(Observable o, Object o1) {
        System.out.println("UPDATE JPANELJOUER");
        updateGrilleFlotte();
        updateGrilleEnnemi();
    }
}

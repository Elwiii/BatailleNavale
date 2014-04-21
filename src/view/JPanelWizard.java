/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.BatailleNavale;
import persistance.DaoFactoryException;

/**
 *
 * @author nikolai
 */
public class JPanelWizard extends JPanel implements Observer {

//    private final GUI gui;
    private final CardLayout cl;
    private JPanelPlacement jpanelPlacement;
    private JPanelJouer jpanelJouer;
    private JPanelAcceuil jpanelAcceuil;
    private JPanelCreer jpanelCreer;
    private JPanelParties jpanelParties;
    private JPanelScore jPanelScore;

    private String currentPanelId = null;
    private JPanel currentPanel;
    
    private BatailleNavale model;

    public JPanelWizard(BatailleNavale model) {
        super();
        this.model = model;
        //gui = GUI.getInstance();
        cl = new CardLayout();
        this.setLayout(cl);
        model.addObserver(this);

        jpanelPlacement = new JPanelPlacement(model, this);
        jpanelJouer = new JPanelJouer(model, this);
        jpanelAcceuil = new JPanelAcceuil(model, this);
        jpanelCreer = new JPanelCreer(model, this);
        jpanelParties = new JPanelParties(model, this);
        jPanelScore = new JPanelScore(model, this);

        add(jpanelAcceuil, JPanelAcceuil.id);
        add(jpanelCreer, JPanelCreer.id);
        add(jpanelJouer, JPanelJouer.id);
        add(jpanelParties, JPanelParties.id);
        add(jPanelScore, JPanelScore.id);
        add(jpanelPlacement, JPanelPlacement.id);

        currentPanelId = JPanelAcceuil.id;
        currentPanel = jpanelAcceuil;
    }

    public void show(String id) {
        switchJPanel(id);
        cl.show(this, id);
        GUI.getInstance().updateMenu();
        GUI.getInstance().pack();
    }

    private void switchJPanel(String id) {
        currentPanelId = id;
        switch (id) {
            case JPanelAcceuil.id:
                currentPanel = jpanelAcceuil;
                GUI.getInstance().setPreferredSize(new Dimension(250, 200));
                break;
            case JPanelCreer.id:
                currentPanel = jpanelCreer;
                GUI.getInstance().setPreferredSize(new Dimension(250, 200));
                break;
            case JPanelJouer.id:
                currentPanel = jpanelJouer;
                GUI.getInstance().setPreferredSize(new Dimension(800, 450));
                break;
            case JPanelParties.id:
                currentPanel = jpanelParties;
                GUI.getInstance().setPreferredSize(new Dimension(250, 200));
                break;
            case JPanelPlacement.id:
                currentPanel = jpanelPlacement;
                GUI.getInstance().setPreferredSize(new Dimension(440, 400));
                break;
            case JPanelScore.id:
                currentPanel = jPanelScore;
                GUI.getInstance().setPreferredSize(new Dimension(250, 200));
                break;
        }
    }

    public void clean(BatailleNavale model) {
        jpanelAcceuil.removeAll();
        jpanelJouer.removeAll();
        jpanelCreer.removeAll();
        jpanelPlacement.removeAll();
        jpanelParties.removeAll();
        jPanelScore.removeAll();
        
        jpanelPlacement = new JPanelPlacement(model, this);
        jpanelJouer = new JPanelJouer(model, this);
        jpanelAcceuil = new JPanelAcceuil(model, this);
        jpanelCreer = new JPanelCreer(model, this);
        jpanelParties = new JPanelParties(model, this);
        jPanelScore = new JPanelScore(model, this);

        add(jpanelAcceuil, JPanelAcceuil.id);
        add(jpanelCreer, JPanelCreer.id);
        add(jpanelJouer, JPanelJouer.id);
        add(jpanelParties, JPanelParties.id);
        add(jPanelScore, JPanelScore.id);
        add(jpanelPlacement, JPanelPlacement.id);

        currentPanel = jpanelAcceuil;
        currentPanelId = JPanelAcceuil.id;
    }

    @Override
    public void update(Observable o, Object o1) {
    }

    /**
     * @return the jpanelPlacement
     */
    public JPanelAcceuil getJpanelAcceuil() {
        return jpanelAcceuil;
    }

    /**
     * @return the jpanelPlacement
     */
    public JPanelPlacement getJpanelPlacement() {
        return jpanelPlacement;
    }

    /**
     * @return the jpanelJouer
     */
    public JPanelJouer getJpanelJouer() {
        return jpanelJouer;
    }

    /**
     * @return the currentPanelId
     */
    public String getCurrentPanelId() {
        return currentPanelId;
    }

    /**
     * @return the currentPanel
     */
    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    /**
     * @param currentPanel the currentPanel to set
     */
    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }

}

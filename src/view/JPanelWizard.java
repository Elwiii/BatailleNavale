/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.BatailleNavale;

/**
 *
 * @author nikolai
 */
public class JPanelWizard extends JPanel implements Observer {

    private final CardLayout cl;
    private final JPanelPlacement jpanelPlacement;
    private final JPanelJouer jpanelJouer;
    private final JPanelAcceuil jpanelAcceuil;
    private final JPanelCreer jpanelCreer;
    private final JPanelParties jpanelParties;
    private final JPanelScore jPanelScore;

    private String currentPanelId = null;
    private JPanel currentPanel;

    public JPanelWizard(final BatailleNavale model) {
        super();
        cl = new CardLayout();
        this.setLayout(cl);
        model.addObserver(this);

        jpanelPlacement = new JPanelPlacement(model, this);
        jpanelJouer = new JPanelJouer(model);
        jpanelAcceuil = new JPanelAcceuil(model, this);
        jpanelCreer = new JPanelCreer(model, this);
        jpanelParties = new JPanelParties(model, this);
        jPanelScore = new JPanelScore(model, this);

        add(jpanelAcceuil,JPanelAcceuil.id);
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
        System.out.println("showing ...");
        cl.show(this, id);
        GUI.getInstance().updateMenu();
    }

    private void switchJPanel(String id) {
        currentPanelId = id;
        switch (id) {
            case JPanelAcceuil.id:
                currentPanel = jpanelAcceuil;
                break;
            case JPanelCreer.id:
                currentPanel = jpanelCreer;
                break;
            case JPanelJouer.id:
                currentPanel = jpanelJouer;
                break;
            case JPanelParties.id:
                currentPanel = jpanelParties;
                break;
            case JPanelPlacement.id:
                currentPanel = jpanelPlacement;
                break;
            case JPanelScore.id:
                currentPanel = jPanelScore;
                break;
        }
    }

    @Override
    public void update(Observable o, Object o1) {
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

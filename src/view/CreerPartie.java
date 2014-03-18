/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Thomas
 */
public class CreerPartie {
    private JFrame frame = new JFrame("Acceuil");
    private JPanel[] panels = new JPanel[4];
    private Menu menu = new Menu();
    private JTextField nom = new JTextField("Nom");
    private JTextField longueur = new JTextField("longeur");
    private JTextField largeur = new JTextField("largeur");
    private String objects[] = {"Car", "Bike", "Dragon", "Bus", "Mouse", "Cheese",
                        "Unicycle", "Sinclair C5", "Pony"};
    private JComboBox listEpoque = new JComboBox(objects);
    
    private JButton validateButton = new JButton("Valider");
    
    public CreerPartie(){
        buildFrame();
    }
    
    public void buildFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = (JPanel) frame.getContentPane();
        
        frame.setSize(640, 480);
        
        // initialize panels 
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }
        
        menu.add(frame);
        
        panels[0].setLayout(new FlowLayout(FlowLayout.CENTER));
        panels[0].setSize(300, 50);
        nom.setSize(300, 50);
        panels[0].add(nom);
        
        panels[1].setLayout(new FlowLayout(FlowLayout.CENTER));
        panels[1].add(listEpoque);
        
        panels[2].setLayout(new GridLayout(2,1));
        //panels[2].setLayout(new FlowLayout(FlowLayout.CENTER));
        //longueur.setSize(20, 10);
        panels[2].add(longueur);
        panels[2].add(largeur);
        panels[2].setBorder(BorderFactory.createTitledBorder("Taille de la grille"));
        
        panels[3].setLayout(new FlowLayout(FlowLayout.CENTER));
        panels[3].add(validateButton);
        
        
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        for (JPanel jPanel : panels) {
            contentPanel.add(jPanel);
        }

        //frame.pack();
        frame.setVisible(true);
        
        
        
    }
    
    public static void main (String[] args){
        CreerPartie c = new CreerPartie();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Nikolai
 */
public class ScoreManager implements TableModel{

    private final String[] columsName = {"Pseudo","Score"};
    
    private final Class[] columsClass={String.class,Integer.class};
    
    private HashMap<String, Integer> hm;

    private static ScoreManager instance;

    private ScoreManager() {
        FileInputStream fichier = null;
        try {
            fichier = new FileInputStream("score.ser");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            hm = (HashMap) ois.readObject();
            fichier.close();
        } catch (FileNotFoundException ex) {
            hm = new HashMap();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getScoreOf(String pseudo) {
        return hm.get(pseudo);
    }

    public void udpate(String pseudo, int score) {
        if (hm.containsKey(pseudo)) {
            int ancien_score = hm.get(pseudo);
            if (ancien_score < score) {
                hm.put(pseudo, score);
            }
        } else {
            hm.put(pseudo, score);
        }
    }
    
    public void save() {
        try {
            FileOutputStream fichier = new FileOutputStream("score.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(hm);
            oos.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    
    @Override
    public int getRowCount() {
        return hm.size();
    }

    @Override
    public int getColumnCount() {
        return columsName.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columsName[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columsClass[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch(columnIndex){
            case 0 :
                return hm.keySet().toArray()[rowIndex];
            case 1:
                return hm.values().toArray()[rowIndex];
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    class Couple implements Comparable{
//        String pseudo;
//        Integer score;
//        Couple(String pseudo, int score){
//            this.score = score;
//            this.pseudo = pseudo;
//        }
//        
//        @Override
//        public int compareTo(Object o){
//            return this.score.compareTo(((Couple)o).score);
//        }
//    }
//    
//    public List<Couple> getOrderListOfScore(){
//        List<Couple> orderList = new ArrayList<>();
//        for(Map.Entry<String, Integer> cursor : hm.entrySet()){
//            orderList.add(new Couple(cursor.getKey(),cursor.getValue()));
//        }
//        Collections.sort(orderList);
//        return orderList;
//    }
    
    /**
     * @return the hm
     */
    public HashMap<String, Integer> getHm() {
        return hm;
    }

    /**
     * @param hm the hm to set
     */
    public void setHm(HashMap<String, Integer> hm) {
        this.hm = hm;
    }


}

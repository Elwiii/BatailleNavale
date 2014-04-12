/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Permet de conserver des valeurs rapidement accessible pour l'application
 * equivalent d'un @session bean
 * @author nikolai
 */
public class Metadata {

    private final Properties prop;
    private static Metadata instance = null;
    int ids = 0;

    private Metadata() {
        prop = new Properties();
    }

    public static Metadata getInstance() {
        if (instance == null) {
            instance = new Metadata();
        }
        instance.load();
        return instance;
    }

    public void save() {
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("ids", ids + "");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void load() {
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            ids = Integer.parseInt(prop.getProperty("ids"));

        } catch (java.io.FileNotFoundException ex) {
            // au cas où le fichier a disparu
            // @todo il faudrait determiner un new id en fonction des noms des fichiers deja existant
            save();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

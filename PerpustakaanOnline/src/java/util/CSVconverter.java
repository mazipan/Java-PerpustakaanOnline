/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jitzu
 */
public class CSVconverter {

    public CSVconverter() {

    }

    public static void createCSV(String[] header, String[][] data, String path) {
        try {
            File newFile = new File(path);
            CSVWriter writer = new CSVWriter(new FileWriter(newFile), ',');
            writer.writeNext(header);
            for (String[] strings : data) {
                writer.writeNext(strings);
            }

            try {
                writer.close();
            } catch (IOException ex) {
            }

        } catch (IOException ex) {
            Logger.getLogger(CSVconverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

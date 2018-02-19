/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.njit.cs634.apriori.external;

import edu.njit.cs634.gui.GUI;
import edu.njit.cs634.helper.Mapping;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Reader {

    public static File tmpFile;
    
    public static void convertAndMergeFile(File[] files) 
    {
        String write = "";
        
        try {
            for (File file : files) 
            {
                GUI.transactionTextArea.append("File: " + file.getAbsolutePath() + "\n");
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
                
                while (reader.ready()) 
                {
                    String line = reader.readLine();
                    GUI.transactionTextArea.append("{ " + line + " }\n");
                    
                    line = Mapping.getLineMapping(line);
                    
                    write += line +"\n";                    
                }                
                GUI.transactionTextArea.append("\n");
                reader.close();
            }
            
            writeToTempFile(write);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeToTempFile(String toWrite)
    {
        try {
            tmpFile = File.createTempFile("mergedTransactions", ".tmp");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
            bw.write(toWrite);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

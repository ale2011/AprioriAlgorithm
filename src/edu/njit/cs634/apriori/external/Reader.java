
package edu.njit.cs634.apriori.external;

import edu.njit.cs634.apriori.gui.GUI;
import edu.njit.cs634.apriori.helper.Mapping;
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
 * Reader.java
 * 
 * The Reader class contains methods to read external text files that contains the 
 * transactions. While reading the transactions, the Reader will combine transactions 
 * from all of the external files into one single files for easy retrieval.
 * 
 * @author Ashley Le
 * @version 20180222
 */
public class Reader {

    public static File tmpFile;
    
    /**
     * Read multiple transactions Files and combine them into 1 
     * @param files the array that contains the files
     */
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
    
    /**
     * Write a string to the tempFile
     * @param toWrite the string to be written
     */
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

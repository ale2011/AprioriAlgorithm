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
 * This class contains methods that read the transactions from multiple text files 
 *  and combine it into a single file while doing other calculation to assist the
 *  Apriori algorithm
 * 
 * @author Ashley Le
 * @version 20180220
 */
public class Reader 
{
    public static File tmpFile; // the temp file
    
    /**
     * Convert all text to its assigned integer number
     * @param files the array that contains all files
     */
    public static void convertAndMergeFile(File[] files) 
    {
        try 
        {
            String write = "";
        
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
     * Write the converted transactions (or any string) to the temp file
     * @param toWrite the string
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

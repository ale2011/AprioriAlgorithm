package edu.njit.cs634.apriori.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * Configurations.java 
 * This class contains method that does the configuration for Apriori program
 * 
 * @author Ashley Le
 * @version 20180220 
 */
public class Configurations 
{    
    /**
     * Calculate and set the required configuration data for Apriori
     * @param myFile the temp file
     */
    public static void config(File myFile)
    {          
        try
        {           
            Apriori.NUM_ITEMS = 0;
            Apriori.NUM_TRANSACTIONS = 0;
            
            // The reader that is going to be used to read the file
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            
            // read each line of the file
            while (reader.ready()) 
            {
                String line = reader.readLine();    // the current line in the file
                
                // if this is an empty line
                if (line.matches("\\s*")) 
                    continue; // skip the rest and continue on.

                Apriori.NUM_TRANSACTIONS++; // increase the transaction number by 1

                // split the values by the parameter - spaces
                StringTokenizer tokenizer = new StringTokenizer(line, " ");

                // loop through all of the tokens inside the tokenizer
                while (tokenizer.hasMoreTokens()) 
                {
                    int token = Integer.parseInt(tokenizer.nextToken());                        
                    if (token + 1 > Apriori.NUM_ITEMS) 
                        Apriori.NUM_ITEMS = token + 1;                        
                }
            }            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
        }
    }
}
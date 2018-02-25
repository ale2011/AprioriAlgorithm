package edu.njit.cs634.apriori.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.HashMap;
import edu.njit.cs634.apriori.gui.GUI;

/**
 * Itemset.java
 * 
 * This class represents the itemsets and associating methods to generate itemsets
 * 
 * @author Ashley Le
 * @version 20180220 
 */
public class Itemset
{
    private static final DecimalFormat df2 = new DecimalFormat(".##");
        
    /**
     * Create the initial itemset
     * Set all itemset to size of 1 - each contains only 1 item from the dataset
     */
    public static void generateInitialItemset() 
    {
        Apriori.itemsets = new ArrayList<int[]>();
        
        for (int index = 0; index < Apriori.NUM_ITEMS; index++) 
        {
            int[] candidate = {index};
            Apriori.itemsets.add(candidate);
        }
    }
    
    /**
     * Calculate the frequency of sets in an itemset
     * Check for their minimum support but only keep the ones that meet the support requirements
     */
    public static void calculateFrequentItemsets()
    {        
        try 
        {
            List<int[]> frequentCandidates = new ArrayList<>();     // Store the frequent candidates            
            int count[] = new int[Apriori.itemsets.size()]; //the number of successful matches
            boolean match = false;  // True if the transaction has all the items in an itemset
            
            System.out.println(Apriori.aFile);
            
            BufferedReader reader = new BufferedReader(new FileReader(Apriori.aFile ));
            
            // read the file 
            while (reader.ready()) 
            {
                boolean[] bTransactions = new boolean[Apriori.NUM_ITEMS];
                
                // loop through each transactions
                for (int i = 0; i < Apriori.NUM_TRANSACTIONS; i++)
                {
                    // boolean[] trans = extractEncoding1(data_in.readLine());
                    String line = reader.readLine();
                    
                    // if this is an empty line
                    if (line.matches("\\s*")) 
                        continue; // skip the rest and continue on.

                    convertToBooleanArray(line, bTransactions);
                    
                    // check each candidate
                    for (int candidateIndex = 0; candidateIndex < Apriori.itemsets.size(); candidateIndex++) 
                    {
                        match = true; // reset match to false
                        
                        // tokenize the candidate to identify what item need to be present for a match
                        int[] itemset = Apriori.itemsets.get(candidateIndex);
                        
                        // check each item in the itemset to see if it is present in the transaction
                        for (int candidate : itemset) 
                        {
                            if (bTransactions[candidate] == false) 
                            {
                                match = false;
                                break;
                            }
                        }
                        
                        // if at this point it is a match, increase the count
                        if (match)  
                            count[candidateIndex]++;                        
                    }
                }
            }   
            reader.close(); // close the reader
            
            for (int index = 0; index < Apriori.itemsets.size(); index++)
            {
                // if the count% is larger than the minSup%, add to the candidate to
                // the frequent candidates
                if ((count[index] / (double) (Apriori.NUM_TRANSACTIONS)) >= Apriori.SUPPORT) 
                {
                    foundFrequentItemSet(Apriori.itemsets.get(index), count[index]);
                    frequentCandidates.add(Apriori.itemsets.get(index));
                } 
            }   
            Apriori.itemsets = frequentCandidates;    
        } 
        catch (FileNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
        }         
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    /**
     * if m is the size of the current itemsets, generate all possible itemsets
     * of size n+1 from pairs of current itemsets replaces the itemsets of
     * itemsets by the new ones
     */
    public static void createNewItemsetFromPrev() 
    {
        int currentSizeOfItemsets = Apriori.itemsets.get(0).length;
       
        HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>(); //temporary candidates

        // compare itemsets
        for (int index1 = 0; index1 < Apriori.itemsets.size(); index1++) 
        {
            for (int index2 = index1 + 1; index2 < Apriori.itemsets.size(); index2++) 
            {
                int[] set1 = Apriori.itemsets.get(index1);
                int[] set2 = Apriori.itemsets.get(index2);
 
                assert (set1.length == set2.length);

                //make a string of the first n-2 tokens of the strings
                int[] newCand = new int[currentSizeOfItemsets + 1];
                
                // fill in values 
                for (int index = 0; index < newCand.length - 1; index++) 
                {
                    newCand[index] = set1[index];
                }

                int ndifferent = 0;
                
                // Find the missing value
                for (int s1 = 0; s1 < set2.length; s1++) 
                {
                    boolean found = false;
                    
                    // check to see whether set2[s1] is in set1
                    for (int s2 = 0; s2 < set1.length; s2++) 
                    {
                        if (set1[s2] == set2[s1]) 
                        {
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) // set2[s1] is not in set1
                    { 
                        ndifferent++;
                        // we put the missing value at the end of newCand
                        newCand[newCand.length - 1] = set2[s1];
                    }
                }

                // find at least 1 different, 
                assert (ndifferent > 0);
                if (ndifferent == 1) 
                {
                    Arrays.sort(newCand);
                    tempCandidates.put(Arrays.toString(newCand), newCand);
                }
            }
        }
        //set the new itemsets
        Apriori.itemsets = new ArrayList<int[]>(tempCandidates.values());        
    }
    
    /**
     * Add the frequent itemset to the tupples list once it's been found
     * @param itemset   the itemset
     * @param support   minimum support 
     */
    public static void foundFrequentItemSet(int[] itemset, double support) 
    {
        String str1 = Arrays.toString(itemset);
        String str2 = str1.substring(0, str1.length() - 1) + ", " + support + "]";
        
        Apriori.tupples.add(str2);
        str1 = Mapping.reverseMapping(str1);
        
        // display results        
        String output = String.format("%s        	(%s - Occurance = %s)", str1, df2.format((support / (double) Apriori.NUM_TRANSACTIONS * 100)), support);
        GUI.resultTextArea.append(output +"\n");        
        
    }
    
    /**
     * Convert values in a line to boolean values - Stores the boolean value in the transactions
     *      True - if this item is in the line
     *      False - if this item is not in the line
     * @param line the line that contains the items
     * @param bTransactions the boolean array that store the boolean values from the line
     */
    public static void convertToBooleanArray(String line, boolean[] bTransactions) 
    {
        // initialize the boolean array
        Arrays.fill(bTransactions, false);  // fill all possible values with false
         
        // split the current line to tokens
        StringTokenizer tokenizer = new StringTokenizer(line, " "); 
        
        // Look at each token in the tokenizer
        while (tokenizer.hasMoreTokens())
        {
            int item = Integer.parseInt(tokenizer.nextToken());
            
            //if it is not a 0, assign the value to true
            bTransactions[item] = true; 
        }
    }    
}
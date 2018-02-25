package edu.njit.cs634.apriori.helper;

import java.io.*;
import java.util.*;
import edu.njit.cs634.apriori.gui.GUI;

/**
 * Apriori.java
 * This class contains methods to assist the Apriori algorithm
 * 
 * @author Ashley Le
 * @version 20180220
 */
public class Apriori
{
    public static List<int[]> itemsets;    // list of current item set
    public static File aFile;              // transaction files
    public static int NUM_ITEMS;           // number of different items in the datasets
    public static int NUM_TRANSACTIONS;    // number of transactions in transactions files
    public static double SUPPORT;          // min support percentage
    public static double CONFIDENCE;       // min confidence percentage
    
    public static List<String> tupples;   // stores the values from the frequent itemsets

    /**
     * Constructor
     * @param file  The file contains combined the transactions
     * @param support   minimum support percentage in decimal
     * @param confidence minimum confidence percentage in decimal
     */
    public Apriori(File file, double support, double confidence)
    {
        tupples = new ArrayList< String>();
        aFile = file;               // set the file
        SUPPORT = support;          // set the support
        CONFIDENCE = confidence;    // set the confidence
        
        Configurations.config(aFile);   // run the configuration
        
        execute();
    }
    
    public static void reset()
    {
        aFile = null;               // set the file
        SUPPORT = 0;          // set the support
        CONFIDENCE = 0;  
        NUM_ITEMS = 0;           // number of different items in the datasets
        NUM_TRANSACTIONS = 0;
        itemsets = null;
        aFile = null;
        tupples = new ArrayList< String>();
    }

    /**
     * Start the Apriori process
     */
    private void execute()
    {        
        Itemset.generateInitialItemset(); // create the initial itemset of size 1
        
        int itemsetNumber = 1; //the current itemset being looked at
        int nbFrequentSets = 0;

        while (itemsets.size() > 0) 
        {
            Itemset.calculateFrequentItemsets();
            if (itemsets.size() != 0) 
            {
                nbFrequentSets += itemsets.size();
                Itemset.createNewItemsetFromPrev();
            }
            itemsetNumber++;
        }        
        runAssociation();
    }
    
    /**
     * Iterate the itemset and apply the association rules
     */
    public static void runAssociation() 
    {
        int tupple1size = tupples.size();
        if (tupple1size == 0) 
        {
            System.exit(0);
            System.out.println("ERROR - runAssociation - Tupple size is 0. App is exiting...");            
        }
        
        int index1, index2,k = 0,m = 0;        
        String aTupple = tupples.get(tupple1size - 1);        
        int tupple2size = ((aTupple.substring(1, aTupple.length() - 1).split(", ")).length);
        
        int[] array1 = new int[tupple1size];
        int[][] array2 = new int[tupple1size][tupple2size - 1];        
        
        for (index1 = 0; index1 < tupple1size; index1++) 
        {
            aTupple = tupples.get(index1);
            String[] candidate = aTupple.substring(1, aTupple.length() - 1).split(", ");
            
            for (index2 = 0; index2 < candidate.length - 1; index2++) 
                array2[index1][index2] = Integer.parseInt(candidate[index2]);
            
            array1[index1] = Integer.parseInt(candidate[index2].replace(".0", ""));
            
            if ((index2 + 1) == tupple2size && k == 0) 
                k = index1;
            
            candidate = null;
        }
        
        GUI.associationTextArea.append("Association Rules - Minimum Confidence = " + CONFIDENCE * 100 + "%\n");
        
        for (index1 = k; index1 < tupple1size; index1++) 
        {
            for (index2 = 0; index2 < k; index2++) 
                m += printAssociation(array2[index1], array2[index2], array1[index1], array1[index2]);
        }
        
        if (m == 0) 
            GUI.associationTextArea.append("No association rules passed the minimum confidence of " + CONFIDENCE * 100 + "%\n");
    }

    /**
     * Print the association
     * @return 
     */
    public static int printAssociation(int[] tupple1, int[] tupple2, int tupple1size, int tupple2size) 
    {
        String yesValue = "[ ", noValue = "[ ";
        int index1, index2, index = 0;
        int[] loss = new int[tupple1.length];
        
        for (index1 = 0; index1 < tupple2.length && tupple2[index1] != 0; index1++) 
        {
            index = 1;
            yesValue = yesValue + tupple2[index1] + " ";
            for (index2 = 0; index2 < tupple1.length; index2++) 
            {
                if (tupple2[index1] == tupple1[index2]) 
                {
                    index = 0;
                    loss[index2] = 1;
                }
            }
        }
        
        yesValue = yesValue.substring(0, yesValue.length() - 1) + " ]";
        for (index1 = 0; index1 < tupple1.length; index1++) 
        {
            if (loss[index1] == 0) 
            {
                noValue = noValue + tupple1[index1] + " ";
            }
        }
        
        noValue = noValue.substring(0, noValue.length() - 1) + " ]";
        if (index == 0) 
        {
            double currentConfidence = (double) tupple1size / tupple2size;
            if (currentConfidence > CONFIDENCE) 
            {                
                // TODO
                yesValue = Mapping.reverseMapping(yesValue);
                noValue = Mapping.reverseMapping(noValue);
                
                String output = String.format("%s ==> %s 	%.2f%c", yesValue, noValue, currentConfidence * 100, 37);

                GUI.associationTextArea.append(output + "\n");
                
                return 1;
            }
        }
        return 0;
    }
    
}

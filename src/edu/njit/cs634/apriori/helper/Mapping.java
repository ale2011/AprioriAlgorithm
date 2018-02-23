package edu.njit.cs634.apriori.helper;

import java.util.ArrayList;

/**
 * Mapping.java
 * This class map the items to an associating integer
 * 
 * @author Ashley Le
 * @version 20180220
 */
public class Mapping 
{
    /*****************
     * positions:
     * 1 = soda
     * 2 = water
     * 3 = beer
     * 4 = peanuts 
     * 5 = chips
     * 6 = chocolate
     * 7 = yogurt 
     * 8 = icecream
     * 9 = popsicle
     * 10 = sneakers 
     * 
     *****************/        
    
    
    /**
     * Convert line with transactions in words to integer
     * @param line string with transactions in words
     * @return string with transactions in integer
     */
    public static String getLineMapping(String line)
    {        
        String tmp = line.toLowerCase();
        tmp = tmp.replace("mints", "10");
        tmp = tmp.replace("soda", "1");
        tmp = tmp.replace("water", "2");
        tmp = tmp.replace("beer", "3");
        tmp = tmp.replace("peanuts", "4");
        tmp = tmp.replace("chips", "5");
        tmp = tmp.replace("chocolate", "6");
        tmp = tmp.replace("yogurt", "7");
        tmp = tmp.replace("icecream", "8");
        tmp = tmp.replace("popsicle", "9");
        return tmp;                    
    }
    
    /**
     * Convert line with transactions in integer to words
     * @param line string with transactions in integer
     * @return string with transactions in words
     */
    public static String reverseMapping(String line)
    {
        String tmp = line;
        tmp = tmp.replace("10", "mints");
        tmp = tmp.replace("1", "soda");
        tmp = tmp.replace("2", "water");
        tmp = tmp.replace("3", "beer");
        tmp = tmp.replace("4", "peanuts");
        tmp = tmp.replace("5", "chips");
        tmp = tmp.replace("6", "chocolate");
        tmp = tmp.replace("7", "yogurt");
        tmp = tmp.replace("8", "icecream");
        tmp = tmp.replace("9", "popsicle");
        return tmp;
    }
}

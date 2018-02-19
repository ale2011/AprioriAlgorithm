/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.njit.cs634.helper;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Mapping 
{
    private static ArrayList <String> mapping = new ArrayList<String>() {{
        add("");
        add("soda");
        add("water");
        add("beer");
        add("peanuts");
        add("chips");
        add("chocolate");
        add("yogurt");
        add("icecream");
        add("popsicle");
        add("mints");
    }};
    
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

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
        add("beer");
        add("yogurt");
        add("water");
        add("sneakers");
        add("chocolate");
        add("kitkat");
        add("milk");
        add("popsicle");
        add("soda");
        add("peanuts");
    }};
    
    public static String getLineMapping(String line)
    {
        
        String tmp = line.toLowerCase();
        System.out.println(tmp);
        tmp = tmp.replace("peanuts", "10");
        tmp = tmp.replace("beer", "1");
        tmp = tmp.replace("yogurt", "2");
        tmp = tmp.replace("water", "3");
        tmp = tmp.replace("sneakers", "4");
        tmp = tmp.replace("chocolate", "5");
        tmp = tmp.replace("kitkat", "6");
        tmp = tmp.replace("milk", "7");
        tmp = tmp.replace("popsicle", "8");
        tmp = tmp.replace("soda", "9");
        System.out.println(tmp);
        return tmp;                    
    }
    
    public static String reverseMapping(String line)
    {
        String tmp = line;
        System.out.println(tmp);
        tmp = tmp.replace("10", "peanuts");
        tmp = tmp.replace("1", "beer");
        tmp = tmp.replace("2", "yogurt");
        tmp = tmp.replace("3", "water");
        tmp = tmp.replace("4", "sneakers");
        tmp = tmp.replace("5", "chocolate");
        tmp = tmp.replace("6", "kitkat");
        tmp = tmp.replace("7", "milk");
        tmp = tmp.replace("8", "popsicle");
        tmp = tmp.replace("9", "soda");
        System.out.println(tmp);
        return tmp;
    }
}

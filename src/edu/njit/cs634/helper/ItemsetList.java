package edu.njit.cs634.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemsetList {

    List <Set <String>> itemsetList;
    
    public ItemsetList()
    {
        itemsetList = new ArrayList<>();
    }
    
    public void addItem(String item)
    {
        itemsetList.add(new HashSet<>(Arrays.asList(item.split(","))));
    }    
    
    public void clear()
    {
        itemsetList.clear();
    }
}
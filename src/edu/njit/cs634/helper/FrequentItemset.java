package edu.njit.cs634.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the frequent itemset
 * 
 * @author Ashley Le
 */
public class FrequentItemset<String> {

    private final List<Set<String>> frequentItemsetList;    // the list that contains the frequent itemset
    private final Map<Set<String>, Integer> supportCountMap;    // the support count mapping
    private final double minimumSupport;    // minimum support 
    private final int numberOfTransactions; // total number of transactions

    /**
     * Initilize the frequent itemset
     * @param frequentItemsetList   list that contains itemsets
     * @param supportCountMap   the map that contains the support count
     * @param minimumSupport    the minimum support
     * @param transactionNumber     the transactions number 
     */
    public FrequentItemset(List<Set<String>> frequentItemsetList,  Map<Set<String>, Integer> supportCountMap,
                    double minimumSupport, int transactionNumber) 
    {
        this.frequentItemsetList = frequentItemsetList;
        this.supportCountMap = supportCountMap;
        this.minimumSupport = minimumSupport;
        this.numberOfTransactions = transactionNumber;
    }

    /**
     * Get the frequent itemset list
     * @return the frequent itemset list
     */
    public List<Set<String>> getFrequentItemsetList() {
        return frequentItemsetList;
    }

    /**
     * Get the support count mapping
     * @return the map that contains the support count
     */
    public Map<Set<String>, Integer> getSupportCountMap() {
        return supportCountMap;
    }

    /**
     * Get the minimum support
     * @return the minimum support
     */
    public double getMinimumSupport() {
        return minimumSupport;
    }

    /**
     * get the transaction number
     * @return the transaction number
     */
    public int getTransactionNumber() {
        return numberOfTransactions;
    }

    /**
     * Get the support of an itemset
     * @param itemset the itemset
     * @return the support of the given itemset
     */
    public double getSupport(Set<String> itemset) {
        return 1.0 * supportCountMap.get(itemset) / numberOfTransactions;
    }
}
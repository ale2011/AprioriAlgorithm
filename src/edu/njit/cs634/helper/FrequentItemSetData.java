package edu.njit.cs634.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class holds the result information of a data-mining task.
 * @param <I>
 */
public class FrequentItemSetData<I> {

    private final List<Set<I>> frequentItemSetList;
    private final Map<Set<I>, Integer> supportCountMap;
    private final double minimumSupport;
    private final int numberOfTransactions;

    FrequentItemSetData(List<Set<I>> frequentItemSetList, Map<Set<I>, Integer> supportCountMap, 
            int minimumSupport, int transactionNumber) 
    {
        this.frequentItemSetList = frequentItemSetList;
        this.supportCountMap = supportCountMap;
        this.minimumSupport = minimumSupport;
        this.numberOfTransactions = transactionNumber;
    }

    public List<Set<I>> getFrequentItemsetList() {
        return frequentItemSetList;
    }

    public Map<Set<I>, Integer> getSupportCountMap() {
        return supportCountMap;
    }

    public double getMinimumSupport() {
        return minimumSupport;
    }

    public int getTransactionNumber() {
        return numberOfTransactions;
    }

    public double getSupport(Set<I> itemset) {
        return 1.0 * supportCountMap.get(itemset) / numberOfTransactions;
    }
}
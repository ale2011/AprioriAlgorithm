package edu.njit.cs634.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 
 */
public class AprioriAlg<I> {

    /**
     * Generates the frequent itemSet data.
     * 
     * @param transactionList the list of transactions to mine.
     * @param minimumSupport  the minimum support.
     * @return the object describing the result of this task.
     */
    public FrequentItemSetData<I> generate(List<Set<I>> transactionList, double minimumSupport) 
    {
        Objects.requireNonNull(transactionList, "The itemSet list is empty.");

        if (transactionList.isEmpty()) {
            return null;
        }

        // Maps each itemSet to its support count. Support count is simply the 
        // number of times an itemSet appeares in the transaction list.
        Map<Set<I>, Integer> supportCountMap = new HashMap<>();

        // Get the list of 1-itemSets that are frequent.
        List<Set<I>> frequentItemList = findFrequentItems(transactionList,
                                                          supportCountMap,
                                                          minimumSupport);

        // Maps each 'k' to the list of frequent k-itemSets. 
        Map<Integer, List<Set<I>>> map = new HashMap<>();
        map.put(1, frequentItemList);

        // 'k' denotes the cardinality of itemSets processed at each iteration
        // of the following loop.
        int k = 1;

        do {
            k++;

            // First generate the candidates.
            List<Set<I>> candidateList = generateCandidates(map.get(k - 1));

            for (Set<I> transaction : transactionList) 
            {
                List<Set<I>> candidateList2 = subset(candidateList, transaction);

                for (Set<I> itemSet : candidateList2) 
                {
                    supportCountMap.put(itemSet, supportCountMap.getOrDefault(itemSet, 0) + 1);
                }
            }

            map.put(k, getNextItemSets(candidateList,
                                       supportCountMap, 
                                       minimumSupport, 
                                       transactionList.size()));

        } while (!map.get(k).isEmpty());

        return new FrequentItemSetData<>(extractFrequentItemSets(map), supportCountMap, minimumSupport, transactionList.size());
    }

    /**
     * This method simply concatenates all the lists of frequent itemSets into
     * one list.
     * 
     * @param  map the map mapping an itemSet size to the list of frequent itemSets of that size.
     * @return the list of all frequent itemSets.
     */
    private List<Set<I>> extractFrequentItemSets(Map<Integer, List<Set<I>>> map) 
    {
        List<Set<I>> ret = new ArrayList<>();

        for (List<Set<I>> itemSetList : map.values()) 
        {
            ret.addAll(itemSetList);
        }

        return ret;
    }

    /**
     * This method gathers all the frequent candidate itemSets into a single 
     * list.
     * 
     * @param candidateList   the list of candidate itemSets.
     * @param supportCountMap the map mapping each itemSet to its support count.
     * @param minimumSupport  the minimum support.
     * @param transactions    the total number of transactions.
     * @return a list of frequent itemSet candidates.
     */
    private List<Set<I>> getNextItemSets(List<Set<I>> candidateList, Map<Set<I>, Integer> supportCountMap,
                                         double minimumSupport, int transactions) 
    {
        List<Set<I>> ret = new ArrayList<>(candidateList.size());

        for (Set<I> itemSet : candidateList) 
        {
            if (supportCountMap.containsKey(itemSet)) 
            {
                int supportCount = supportCountMap.get(itemSet);
                double support = 1.0 * supportCount / transactions;

                if (support >= minimumSupport) 
                    ret.add(itemSet);
                
            }
        }

        return ret;
    }

    /**
     * Computes the list of itemSets that are all subsets of 
     * {@code transaction}.
     * 
     * @param candidateList the list of candidate itemSets.
     * @param transaction   the transaction to test against.
     * @return the list of itemSets that are subsets of {@code transaction}
     *         itemSet.
     */
    private List<Set<I>> subset(List<Set<I>> candidateList,  Set<I> transaction) 
    {
        List<Set<I>> ret = new ArrayList<>(candidateList.size());

        for (Set<I> candidate : candidateList) 
        {
            if (transaction.containsAll(candidate)) 
                ret.add(candidate);
        }

        return ret;
    }

    /**
     * Generates the next candidates. This is so called F_(k - 1) x F_(k - 1) 
     * method.
     * 
     * @param itemSetList the list of source itemSets, each of size <b>k</b>.
     * @return the list of candidates each of size <b>k + 1</b>.
     */
    private List<Set<I>> generateCandidates(List<Set<I>> itemSetList) 
    {
        List<List<I>> list = new ArrayList<>(itemSetList.size());

        for (Set<I> itemSet : itemSetList) 
        {
            List<I> l = new ArrayList<>(itemSet);
            Collections.<I>sort(l, ITEM_COMPARATOR);
            list.add(l);
        }

        int listSize = list.size();

        List<Set<I>> ret = new ArrayList<>(listSize);

        for (int i = 0; i < listSize; ++i) 
        {
            for (int j = i + 1; j < listSize; ++j) 
            {
                Set<I> candidate = tryMergeItemSets(list.get(i), list.get(j));

                if (candidate != null) 
                    ret.add(candidate);
            }
        }

        return ret;
    }

    /**
     * Attempts the actual construction of the next itemSet candidate.
     * @param itemSet1 the list of elements in the first itemSet.
     * @param itemSet2 the list of elements in the second itemSet.
     * 
     * @return a merged itemSet candidate or {@code null} if one cannot be 
     *         constructed from the input itemSets.
     */
    private Set<I> tryMergeItemSets(List<I> itemSet1, List<I> itemSet2) 
    {
        int length = itemSet1.size();

        for (int i = 0; i < length - 1; ++i) 
        {
            if (!itemSet1.get(i).equals(itemSet2.get(i))) 
                return null;            
        }

        if (itemSet1.get(length - 1).equals(itemSet2.get(length - 1))) 
            return null;
        
        Set<I> ret = new HashSet<>(length + 1);

        for (int i = 0; i < length - 1; ++i) 
        {
            ret.add(itemSet1.get(i));
        }

        ret.add(itemSet1.get(length - 1));
        ret.add(itemSet2.get(length - 1));
        return ret;
    }

    private static final Comparator ITEM_COMPARATOR = new Comparator() 
    {
        @Override
        public int compare(Object o1, Object o2) 
        {
            return ((Comparable) o1).compareTo(o2);
        }
    };

    /**
     * Computes the frequent itemSets of size 1.
     * 
     * @param itemSetList     the entire database of transactions.
     * @param supportCountMap the support count map to which to write the 
     *                        support counts of each item.
     * @param minimumSupport  the minimum support.
     * @return                the list of frequent one-itemSets.
     */
    private List<Set<I>> findFrequentItems(List<Set<I>> itemSetList, Map<Set<I>, Integer> supportCountMap, double minimumSupport) 
    {
        Map<I, Integer> map = new HashMap<>();

        // Count the support counts of each item.
        for (Set<I> itemSet : itemSetList) 
        {
            for (I item : itemSet) 
            {
                Set<I> tmp = new HashSet<>(1);
                tmp.add(item);

                if (supportCountMap.containsKey(tmp)) 
                    supportCountMap.put(tmp, supportCountMap.get(tmp) + 1);
                else 
                    supportCountMap.put(tmp, 1);

                map.put(item, map.getOrDefault(item, 0) + 1);
            }
        }

        List<Set<I>> frequentItemSetList = new ArrayList<>();

        for (Map.Entry<I, Integer> entry : map.entrySet()) 
        {
            if (1.0 * entry.getValue() / map.size() >= minimumSupport) 
            {
                Set<I> itemSet = new HashSet<>(1);
                itemSet.add(entry.getKey());
                frequentItemSetList.add(itemSet);
            }
        }
        return frequentItemSetList;
    }
   
}
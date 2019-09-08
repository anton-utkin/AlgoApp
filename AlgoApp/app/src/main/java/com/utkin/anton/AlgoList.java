package com.utkin.anton;

import java.util.LinkedList;
import java.util.List;

public class AlgoList {
    private static List<AlgorithmItem> mItems= new LinkedList<>();

    private static void initialize() {
        mItems.add(new AlgorithmItem("Merge Sort", "MergeSort.pdf"));
        mItems.add(new AlgorithmItem("Quick Sort", "QuickSort.pdf"));
    }

    public static List<AlgorithmItem> getItems(){
        if(mItems.isEmpty()){
            initialize();
        }
        return mItems;
    }

    public static AlgorithmItem getItemByTitle(String title){
        for(AlgorithmItem item: mItems){
            if(item.getTitle().equals(title)){
                return item;
            }
        }
        return null;
    }
}

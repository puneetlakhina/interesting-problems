package ds;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedLists {

    public static List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
        if(list1.isEmpty()) {
            return list2;
        }
        if(list2.isEmpty()) {
            return list1;
        }
        int list1Index = 0;
        int list2Index = 0;
        
        List<Integer> mergedList = new ArrayList<>();
        while(list1Index < list1.size() && list2Index < list2.size()) {
            if(list1.get(list1Index).intValue() < list2.get(list2Index).intValue()) {
                mergedList.add(list1.get(list1Index));
                list1Index++;
            } else {
                mergedList.add(list2.get(list2Index));
                list2Index++;
            }
        }
        while(list1Index < list1.size()) {
            mergedList.add(list1.get(list1Index));
            list1Index++;
        }
        while(list2Index < list2.size()) {
            mergedList.add(list2.get(list2Index));
            list2Index++;
        }
        return mergedList;
    }
}

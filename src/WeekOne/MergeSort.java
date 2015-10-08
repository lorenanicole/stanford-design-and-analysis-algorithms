package WeekOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lorenamesa on 10/8/15.
 */
public class MergeSort {

    public MergeSort() { }

    public static List<Integer> mergeSort(List<Integer> unsortedList) {
        Integer n = unsortedList.size();

        if (n <= 1) {
            return unsortedList;
        }

        List<Integer> leftHalf = new ArrayList<Integer>(unsortedList.subList(0,n/2));


        List<Integer> rightHalf = new ArrayList<Integer>(unsortedList.subList(n/2,n));

        leftHalf = mergeSort(leftHalf);
        rightHalf = mergeSort(rightHalf);

        List<Integer> sortedList = merge(leftHalf, rightHalf);
        return sortedList;

    }

    private static List<Integer> merge(List<Integer> leftHalf, List<Integer> rightHalf) {
        List<Integer> sortedList = new ArrayList<Integer>();

        Integer counterLeft = 0;
        Integer counterRight = 0;

        for(Integer i=0; i < (leftHalf.size() + rightHalf.size()); i++) {
            if (counterLeft >= (leftHalf.size())) {
                sortedList.addAll(rightHalf.subList(counterRight, rightHalf.size()));
                break;
            }
            if (counterRight >= (rightHalf.size())) {
                sortedList.addAll(leftHalf.subList(counterLeft, leftHalf.size()));
                break;
            }
            if (leftHalf.get(counterLeft) < rightHalf.get(counterRight)) {
                sortedList.add(i, leftHalf.get(counterLeft));
                counterLeft++;
            } else {
                sortedList.add(i, rightHalf.get(counterRight));
                counterRight++;

            }
        }
        return sortedList;
    }

    public static void main(String[] args) {
        System.out.println(MergeSort.mergeSort(Arrays.asList(1, 9, 5, 4, 8, 11, 999))
                .equals(Arrays.asList(1, 4, 5, 8, 9, 11, 999)));
        System.out.println(MergeSort.mergeSort(Arrays.asList(1))
                .equals(Arrays.asList(1)));
        System.out.println(MergeSort.mergeSort(Arrays.asList())
                .equals(Arrays.asList()));
    }
}

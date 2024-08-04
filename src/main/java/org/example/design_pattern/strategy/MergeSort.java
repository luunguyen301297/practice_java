package org.example.design_pattern.strategy;

import java.util.ArrayList;
import java.util.List;

public final class MergeSort implements SortStrategy {

    @Override
    public <T extends Comparable<T>> void sort(List<T> items) {
        if (items == null || items.size() < 2) {
            return;
        }
        List<T> tempList = new ArrayList<>(items);
        mergeSort(tempList, 0, items.size() - 1);
        for (int i = 0; i < items.size(); i++) {
            items.set(i, tempList.get(i));
        }
    }

    private <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    private <T extends Comparable<T>> void merge(List<T> list, int left, int mid, int right) {
        List<T> tempList = new ArrayList<>(list.subList(left, right + 1));
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (tempList.get(i - left).compareTo(tempList.get(j - left)) <= 0) {
                list.set(k++, tempList.get(i - left));
                i++;
            } else {
                list.set(k++, tempList.get(j - left));
                j++;
            }
        }

        while (i <= mid) {
            list.set(k++, tempList.get(i - left));
            i++;
        }

        while (j <= right) {
            list.set(k++, tempList.get(j - left));
            j++;
        }
    }

}

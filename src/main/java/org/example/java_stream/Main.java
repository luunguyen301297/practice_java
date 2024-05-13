package org.example.java_stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Tính tổng các số chẵn trong List
        int sum = list.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n)
                .sum();

        System.out.println(sum);

        // Get list element % 3 = 0
        List<Integer> list1= list.stream()
                .filter(n -> n % 3 == 0)
                .collect(Collectors.toList());

        System.err.println(list1);

        // sum of num in arr
        int total = list.stream()
                .reduce(0, Integer::sum);

        System.out.println(total);

        // sort desc
        List<Integer> sortedList = list.stream()
                .sorted((a, b) -> b - a)
                .limit(5)
                .skip(2)
                .toList();

        List<Integer> sortedList2 = list.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        System.err.println(sortedList);
        System.err.println(sortedList2);

        // filter
        List<String> nums = Arrays.asList("1", "2", "3");

        List<String> result = nums.stream()
                .filter(num -> !"2".equals(num))
                .toList();

        System.err.println(result);

        List<TestObj> objList = List.of(new TestObj(1, "a"),
                new TestObj(2, "b"),
                new TestObj(3, "c"));

        TestObj testObj = objList.stream()
                .filter(name -> name.getName().equalsIgnoreCase("a"))
                .findAny()
                .orElse(null);

        Integer id = objList.stream()
                .filter(name -> name.getName().equalsIgnoreCase("a"))
                .map(TestObj::getId)
                .findAny()
                .orElse(null);

        System.out.println(testObj);
        System.out.println(id);
    }

}

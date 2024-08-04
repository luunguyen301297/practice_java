package org.example.java_stream;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@FieldDefaults(level = AccessLevel.PUBLIC)
public class Main {

    static int sumOfNumInArrayList(List<Integer> list) {
        return list.stream()
                .reduce(0, Integer::sum);
    }

    static List<Integer> sortAsc(List<Integer> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(a -> a))
                .toList();
    }

    static List<Integer> sortDesc(List<Integer> list) {
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    static int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
            log.error("Arithmetic Exception: Division by Zero");
        }
        return result;
    }

    public static void main(String[] args) {
        // filter
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers= numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        int sum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n)
                .sum();

        // map: Chuyển đổi dữ liệu từ một định dạng sang một định dạng khác
        List<String> names = Arrays.asList("John", "Jane", "Alice", "Bob");
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .toList();

        // flatMap: Ánh xạ mỗi phần tử sang một stream mới và sau đó kết hợp các stream đó thành một stream duy nhất
        List<String> words = Arrays.asList("Hello", "World");
        List<String> letters = words.stream()
                .flatMap(word -> Stream.of(word.split("")))
                .toList();

        // sorted(Comparator<T> comparator): Sắp xếp các phần tử theo thứ tự được chỉ định bởi comparator
        List<User> objList = List.of(new User(1, "a"),
                new User(2, "b"),
                new User(3, "c"));
        List<User> sortedList = objList.stream()
                .sorted(Comparator.comparing(User::getId))
                .toList();

        // reduce with try catch
        int divider = 2;
        int result = numbers.stream()
                .reduce(0, (a, b) -> divide(a, divider) + divide(b, divider));

        // collect to map
        Map<Integer, String> mapWithValue = objList.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, User> mapObj = objList.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        // find
        User user = objList.stream()
                .filter(name -> name.getName().equalsIgnoreCase("a"))
                .findAny()
                .orElse(null);

        Integer id = objList.stream()
                .filter(name -> name.getName().equalsIgnoreCase("a"))
                .map(User::getId)
                .findAny()
                .orElse(null);
    }

}

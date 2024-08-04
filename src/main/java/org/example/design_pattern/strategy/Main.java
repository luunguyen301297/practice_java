package org.example.design_pattern.strategy;

public class Main {

    /**
        Cho phép định nghĩa tập hợp các thuật toán, đóng gói từng thuật toán lại, và dễ dàng thay đổi linh hoạt các thuật toán bên trong Object
        Cho phép thuật toán biến đổi độc lập khi người dùng sử dụng chúng
        Giúp tách rời phần xử lý một chức năng cụ thể ra khỏi Object
            -> Tạo ra một tập hợp các thuật toán để xử lý chức năng đó và lựa chọn thuật toán nào mà chúng ta cần khi thực thi chương trình.
        Thường được sử dụng để thay thế cho sự kế thừa, khi muốn chấm dứt việc theo dõi và chỉnh sửa một chức năng qua nhiều lớp con
    **/
    public static void main(String[] args) {
        SortedList list1 = new SortedList();
        list1.add(1);
        list1.add(4);
        list1.add(3);
        list1.add(2);
        System.out.println("Quick sort");
        list1.setSortStrategy(new QuickSort());
        list1.sort().forEach(System.out::println);

        SortedList list2 = new SortedList();
        list2.add(2);
        list2.add(1);
        list2.add(4);
        list2.add(3);
        System.out.println("Merge sort");
        list2.setSortStrategy(new MergeSort());
        list2.sort().forEach(System.out::println);
    }

}

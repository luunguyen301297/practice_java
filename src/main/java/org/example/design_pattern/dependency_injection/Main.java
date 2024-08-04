package org.example.design_pattern.dependency_injection;

public class Main {

    /**
        Object không tự khởi tạo các dependencies của nó
        Các dependencies được inject vào Object từ bên ngoài (thông qua constructor, setter, property)
        Cho phép thay đổi các dependencies mà không cần thay đổi code của lớp phụ thuộc
        -> Giảm sự phụ thuộc giữa các Class và tăng tính linh hoạt trong việc thay đổi các dependencies
    **/
    public static void main(String[] args) {
        MessageService messageService = new EmailService();
        UserController userController = new UserController(messageService);
        userController.sendMessage("Hello World");
    }

}

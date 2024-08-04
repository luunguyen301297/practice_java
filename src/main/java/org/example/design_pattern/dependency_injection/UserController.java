package org.example.design_pattern.dependency_injection;

public class UserController {

    private final MessageService messageService;

    public UserController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendMessage(String message) {
        messageService.sendMessage(message);
    }

}

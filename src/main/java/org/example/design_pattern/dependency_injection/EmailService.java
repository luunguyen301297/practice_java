package org.example.design_pattern.dependency_injection;

public class EmailService implements MessageService {

    @Override
    public void sendMessage(String message)  {
        System.out.println("Email :" + message);
    }

}

package org.example.design_pattern.dependency_injection;

@SuppressWarnings("unused")
public class SmsService implements MessageService {

    @Override
    public void sendMessage(String message)  {
        System.out.println("SMS :" + message);
    }

}

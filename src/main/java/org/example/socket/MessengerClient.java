package org.example.socket;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

import static org.example.MessageColor.*;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessengerClient {

    static String SERVER_IP = "127.0.0.1";
    static int SERVER_PORT = 8088;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            KeyPair keyPair = KeyGeneratorUtil.generateKeyPair();
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Receive the server's public key
            PublicKey serverPublicKey = CryptographyUtil.receivePublicKey(in);

            // Send the client's public key to the server
            out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));

            System.err.println("Type messages to send :");
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                String encryptedMessage = CryptographyUtil.encryptMessage(userInput, serverPublicKey);
                out.println(encryptedMessage);

                String encryptedResponse = in.readLine();
                String decryptedResponse = CryptographyUtil.decryptMessage(encryptedResponse, keyPair.getPrivate());
                System.out.println("Server response: " + ANSI_PURPLE.getCode() + decryptedResponse + ANSI_RESET.getCode());
                System.out.println(ANSI_RED.getCode() + "Type messages to response :" + ANSI_RESET.getCode());
            }
        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage());
        }
    }

}

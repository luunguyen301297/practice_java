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
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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
            String serverPublicKeyString = in.readLine();
            byte[] serverPublicKeyBytes = Base64.getDecoder().decode(serverPublicKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey serverPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(serverPublicKeyBytes));

            // Send the client's public key to the server
            out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
            hackerLordMessage();

            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                String encryptedMessage = CryptographyUtil.encryptMessage(userInput, serverPublicKey);
                out.println(encryptedMessage);

                String encryptedResponse = in.readLine();
                String decryptedResponse = CryptographyUtil.decryptMessage(encryptedResponse, keyPair.getPrivate());
                System.out.println("Server response: " + decryptedResponse);
            }
        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage());
        }
    }

    private static void hackerLordMessage() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.err.println("Hacking NASA: " + i * 20 + "%");
        }
        System.err.println("Success hacking NASA, Type messages to send :");
    }
}
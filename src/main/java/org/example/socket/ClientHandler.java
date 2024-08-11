package org.example.socket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.*;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.MessageColor.*;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ClientHandler extends Thread {
    Socket socket;
    KeyPair keyPair;
    AtomicBoolean running = new AtomicBoolean(true);

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Send the server's public key to the client
            out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));

            // Receive the client's public key
            PublicKey clientPublicKey = CryptographyUtil.receivePublicKey(in);

            // Start a new thread to read console input
            new ConsoleReader(out, clientPublicKey).start();

            // Read messages from the client
            String clientIP = socket.getInetAddress().getHostAddress();
            String message;
            while ((message = in.readLine()) != null) {
                String decryptedMessage = CryptographyUtil.decryptMessage(message, keyPair.getPrivate());
                System.out.println("Client (" + clientIP + ") message :"
                        + ANSI_PURPLE.getCode() + decryptedMessage + ANSI_RESET.getCode());
                System.out.println(ANSI_RED.getCode() + "Type messages to response :" + ANSI_RESET.getCode());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            running.set(false);
        }
    }

    // Inner class to handle console input
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
    class ConsoleReader extends Thread {
        PrintWriter out;
        PublicKey clientPublicKey;

        @Override
        public void run() {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                String input;
                while (running.get() && (input = consoleReader.readLine()) != null) {
                    String encryptedMessage = CryptographyUtil.encryptMessage(input, clientPublicKey);
                    out.println(encryptedMessage);
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

}

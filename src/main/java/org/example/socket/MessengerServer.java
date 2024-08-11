package org.example.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Slf4j
@SuppressWarnings("InfiniteLoopStatement")
public class MessengerServer {
    private static final int PORT = 8088;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            KeyPair keyPair = KeyGeneratorUtil.generateKeyPair();
            System.err.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, keyPair).start();
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }

}

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
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ClientHandler extends Thread {
    Socket socket;
    KeyPair keyPair;

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true))
        {
            // Send the server's public key to the client
            out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));

            // Receive the client's public key
            String clientPublicKeyString = in.readLine();
            byte[] clientPublicKeyBytes = Base64.getDecoder().decode(clientPublicKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey clientPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(clientPublicKeyBytes));

            String clientIP = socket.getInetAddress().getHostAddress();
            String message;

            while ((message = in.readLine()) != null) {
                String decryptedMessage = CryptographyUtil.decryptMessage(message, keyPair.getPrivate());
                System.err.println("Received from client: " + decryptedMessage);
                String response = clientIP + " : " + decryptedMessage;
                String encryptedResponse = CryptographyUtil.encryptMessage(response, clientPublicKey);
                out.println(encryptedResponse);
            }
        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage());
        }
    }

}
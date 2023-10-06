package org.example.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client1 {
    public static void main(String[] args) {
        InetAddress inetAddress = null;
        Socket socket = null;
        OutputStream outputStream = null;

        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
            //inetAddress = InetAddress.getByName("121.5.135.135");
            socket = new Socket(inetAddress, 9999);
            outputStream = socket.getOutputStream();
            outputStream.write("hello, I'm client.".getBytes(StandardCharsets.UTF_8));

            socket.shutdownOutput();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}

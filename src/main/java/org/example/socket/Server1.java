package org.example.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("server started, waiting client request..");
            while (true) {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();

                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[20];
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, length);
                }

                System.out.println(byteArrayOutputStream);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
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

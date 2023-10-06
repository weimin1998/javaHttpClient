package org.example.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        OutputStream outputStream = null;
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
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

                outputStream = socket.getOutputStream();
                outputStream.write("hello, I'm server.".getBytes(StandardCharsets.UTF_8));
                socket.shutdownOutput();

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
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}

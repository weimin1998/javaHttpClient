package org.example.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        InetAddress inetAddress = null;
        Socket socket = null;
        OutputStream outputStream = null;

        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
            //inetAddress = InetAddress.getByName("121.5.135.135");
            socket = new Socket(inetAddress, 8888);
            outputStream = socket.getOutputStream();
            outputStream.write("hello, I'm client.".getBytes(StandardCharsets.UTF_8));

            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[20];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, length);
            }

            System.out.println(byteArrayOutputStream);

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
        }


    }
}

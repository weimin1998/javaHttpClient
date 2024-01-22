package org.example.socket;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        String data = "Hello, I'm client!";
        byte[] bytes = data.getBytes();
        InetAddress targetAddress = InetAddress.getByName("127.0.0.1");
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, targetAddress, 9090);

        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}

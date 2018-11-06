package edu.COSC331.UDPDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServerPracticeOne {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] get = new byte[1024];
        byte[] send;

        while(true){
            DatagramPacket in = new DatagramPacket(get, get.length);
            socket.receive(in);

            String line = new String(in.getData());

            System.out.println("[GOT:] "+line);

            InetAddress IP = in.getAddress();
            int port = in.getPort();

            String response = "THIS IS AN AUTOMATED REPLY";
            send = response.getBytes();

            DatagramPacket out = new DatagramPacket(send, send.length, IP, port);
            socket.send(out);

        }

    }
}

package edu.COSC331.UDPDemo;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPClientPracticeOne {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        InetAddress IP;
        IP = InetAddress.getByName("localhost");

        byte[] send;
        byte[] get = new byte[1024];

        String line;
        line = inp.readLine();

        send = line.getBytes();

        DatagramPacket out = new DatagramPacket(send, send.length, IP, 9876);
        socket.send(out);

        DatagramPacket in = new DatagramPacket(get, get.length);
        socket.receive(in);

        String temp = new String(in.getData());
        System.out.println("[PEER:] "+temp);
        socket.close();

    }
}

package edu.COSC331.UDPDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception
    {

        DatagramSocket socket = new DatagramSocket(3001);
        byte[] buffer = new byte[1024];
        byte[] bufferIn = new byte[1024];
        Scanner inp = new Scanner(System.in);
        String in, out;
        InetAddress IP = InetAddress.getByName("localhost");

        System.out.println("Server  ready for chatting");

        while(true)
        {
            out = inp.nextLine();

            buffer = out.getBytes();


            DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, IP, 1234);


            socket.send(outPacket);

            if (inp.equals("bye"))
                break;
        }
        socket.close();
    }
}

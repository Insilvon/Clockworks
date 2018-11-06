package edu.COSC331.UDPDemo;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPServer
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket socket = new DatagramSocket(3000);
        System.out.println("Server  ready for chatting");
        byte[] buffer = new byte[1024];
        byte[] outBuffer = new byte[1024];
        Scanner inp = new Scanner(System.in);

        // reading from keyboard (keyRead object)
        // sending to client (pwrite object)

        // receiving from server ( receiveRead  object)

        while(true)
        {
            DatagramPacket in = new DatagramPacket(buffer, buffer.length);
            InetAddress IP = in.getAddress();
            int port = in.getPort();

            String line = in.getData().toString();

            if (line != null)
            {
                System.out.println(line);
            }

            String outgoing = inp.nextLine();
            outBuffer = outgoing.getBytes();
            DatagramPacket out = new DatagramPacket(outBuffer, outBuffer.length, IP, port);
            socket.send(out);
        }
    }
}
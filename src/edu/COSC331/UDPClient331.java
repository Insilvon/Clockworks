package edu.COSC331;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.HashSet;

public class UDPClient331 {
    DatagramSocket socket;
    InetAddress addr;
    HashSet<SocketAddress> group;
    String IP;
    boolean firstTime;

    public void run() throws Exception {
        firstTime = true;
        System.out.println("Server Started");
        int port = 4000;
        socket = new DatagramSocket(port);

//        try {
//            InetAddress addr = InetAddress.getLocalHost();
//            String hostName = addr.getHostName();
//            String hostAddress = addr.getHostAddress();
//            System.out.println("Port: "+port);
//            System.out.println("Host Name: "+hostName);
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }

        System.out.println("Client Activity");
        while (true){
            //get the message from clients
            byte[] buffer = new byte[1024];
            //create a new packet to get the data from
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            //receive the packet
            socket.receive(packet);


            group.add(packet.getSocketAddress()); //client socket address



            }
        }
    }

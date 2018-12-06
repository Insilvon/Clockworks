package edu.COSC331;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.HashSet;

public class UDPServer331 {
    static DatagramSocket socket;
    InetAddress addr;
    static HashSet<SocketAddress> group = new HashSet<SocketAddress>();
    String IP;
    static boolean firstTime;

    public static void main(String[] args) throws Exception {
        firstTime = true;
        System.out.println("Server Started");
        int port = 4000;
        socket = new DatagramSocket(port);

        InetAddress addr = InetAddress.getLocalHost();
        String hostName = addr.getHostName();
        String hostAddress = addr.getHostAddress();
        System.out.println("Port: " + port);
        System.out.println("Host Name: " + hostName);

        Listener2 client = new Listener2(socket);
        client.start();

        System.out.println("Client Activity");

    }

    static class Listener2 extends Thread {
        DatagramSocket socket;

        public Listener2(DatagramSocket socket2) {
            socket = socket2;
        }

        public void run() {
            while (true) {
                System.out.print(">");
                //get the message from clients
                byte[] buffer = new byte[1024];
                //create a new packet to get the data from
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                //receive the packet
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                group.add(packet.getSocketAddress()); //client socket address
                String temp1 = new String(packet.getData(), 0, packet.getLength());
                System.out.print(packet.getAddress()+": "+temp1);
                System.out.println();
                for (SocketAddress s : group) { //for every socket in the hashset...

                    if (firstTime == true) {
                        String g = "First Client Command";
                        byte[] temp = new byte[1024];
                        temp = g.getBytes();
                        DatagramPacket greet = new DatagramPacket(temp, temp.length, s);
                        //send the first command???
                        try {
                            socket.send(greet);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        firstTime = false;
                    }

                    DatagramPacket p = new DatagramPacket(buffer, buffer.length, s);
                    try {
                        socket.send(p); //not sure
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
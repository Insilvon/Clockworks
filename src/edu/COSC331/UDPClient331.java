package edu.COSC331;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Scanner;

public class UDPClient331 {
    static DatagramSocket socket;
    static InetAddress addr;
    String IP;
    boolean firstTime;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("What host to connect to?");
        String hostName = in.nextLine();
        System.out.println("What port to connect to? (4000)");
        int port = in.nextInt();
        System.out.println("Port to use?");
        int localPort = in.nextInt();
        System.out.println("Username?");
        Scanner newer = new Scanner(System.in);
        String username = newer.nextLine();
        System.out.println(InetAddress.getLocalHost());
        addr = InetAddress.getByName(hostName);
        socket = new DatagramSocket(localPort);

        Listener sender = new Listener(socket);
        sender.start();


        while(true){
            Scanner read = new Scanner(System.in);
            String line = username;
            line+=": "+read.nextLine();
            byte[] buffer = new byte[1024];
            buffer = line.getBytes();
            DatagramPacket s = new DatagramPacket(buffer, buffer.length, addr, port);

            socket.send(s);
        }
    }

}
class Listener extends Thread {
    DatagramSocket socket;
    public Listener(DatagramSocket socket2){
        socket = socket2;
    }
    public void run() {
        while(true){
            byte[] buffer = new byte[1024];
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(p);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String line = new String(p.getData(), 0, p.getLength());
            System.out.println(line);
        }

    }
}

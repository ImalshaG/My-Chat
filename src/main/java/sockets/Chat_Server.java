package sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Chat_Server {
    public static ArrayList<Socket> connectionArray= new ArrayList<Socket>();
    public static ArrayList<String> currentUsers= new ArrayList<String>();

    public static void main(String[] args) throws IOException{
        try{
            final int PORT = 444;
            ServerSocket SERVER = new ServerSocket(PORT);
            System.out.println("Waiting for clients ...");

            while (true){
                Socket SOCK = SERVER.accept();
                connectionArray.add(SOCK);
                System.out.println("Client connected from : "+SOCK.getLocalAddress().getHostName());

                addUserName(SOCK);

                Chat_Server_Return CHAT = new Chat_Server_Return(SOCK);
                Thread X = new Thread(CHAT);
                X.start();
            }
        }catch (Exception X){
            System.out.println(X);
        }
    }
    public static void addUserName(Socket S) throws IOException{
        Scanner input = new Scanner(S.getInputStream());
        String userName = input.nextLine();
        currentUsers.add(userName);

        for (int i=1; i<=Chat_Server.connectionArray.size(); i++){
            Socket tempSock = (Socket)connectionArray.get(i-1);
            PrintWriter out = new PrintWriter(tempSock.getOutputStream());
            out.println("#?!"+currentUsers);
            out.flush();
        }

    }
}

package sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Chat_Server_Return implements Runnable {
    Socket sock;
    private Scanner input;
    private PrintWriter out;
    String message = "";

    public  Chat_Server_Return(Socket X){
        this.sock = X;
    }
    public void checkConnection() throws IOException{
        if(!sock.isConnected()){
            for (int i=1; i<=Chat_Server.connectionArray.size();i++){
                if (Chat_Server.connectionArray.get(i) == sock){
                    Chat_Server.connectionArray.remove(i);
                }
            }
            for (int i=1; i<=Chat_Server.connectionArray.size();i++){
                Socket tempSock = (Socket)Chat_Server.connectionArray.get(i-1);
                PrintWriter tempOut = new PrintWriter(tempSock.getOutputStream());
                tempOut.println(tempSock.getLocalAddress().getHostName()+"Disconnected ! ");
                tempOut.flush();
                System.out.println(tempSock.getLocalAddress().getHostName()+"Disconnected ! ");
            }
        }
    }
    public void run(){
        try {
            try {
                input = new Scanner(sock.getInputStream());
                out = new PrintWriter(sock.getOutputStream());

                while (true){
                    checkConnection();

                    if (!input.hasNext()){
                        return;
                    }
                    message = input.nextLine();

                    System.out.println("Client said" + message);

                    for (int i=1; i<=Chat_Server.connectionArray.size();i++){
                        Socket tempSock = (Socket)Chat_Server.connectionArray.get(i-1);
                        PrintWriter tempOut = new PrintWriter(tempSock.getOutputStream());
                        tempOut.println(message);
                        tempOut.flush();
                        System.out.println("Sent to : "+tempSock.getLocalAddress().getHostName());
                    }
                }
            }finally {
                sock.close();
            }
        }catch (Exception X){
            System.out.println(X);
        }
    }
}

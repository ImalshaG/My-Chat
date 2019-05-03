package sockets;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Chat_Client implements Runnable {
    Socket sock;
    Scanner input;
    Scanner send = new Scanner(System.in);
    PrintWriter out;

    public Chat_Client(Socket X){
        this.sock = X;
    }
    public void run(){
        try{
            try{
                input = new Scanner(sock.getInputStream());
                out = new PrintWriter(sock.getOutputStream());
                out.flush();
                checkStream();
            }finally {
                sock.close();
            }
        }catch (Exception X){
            System.out.println(X);
        }
    }
    public void disconnect() throws IOException{
        out.println(Chat_Client_GUI.username+ " has disconnected");
        out.flush();
        sock.close();
        JOptionPane.showMessageDialog(null,"Yor are disconnected!");
        System.exit(0);
    }
    public void checkStream(){
        while (true){
            receive();
        }
    }
    public void receive(){
        if(input.hasNext()){
            String message = input.nextLine();

            if(message.contains("#?!")){
                String temp1 = message.substring(3);
                temp1 = temp1.replace("[","");
                temp1 = temp1.replace("]","");

                String[] currentUsers = temp1.split(", ");

                Chat_Client_GUI.JL_online.setListData(currentUsers);
            }else{
                Chat_Client_GUI.TA_conversation.append(message+"\n");
            }
        }
    }
    public void send(String X){
        out.println(Chat_Client_GUI.username+": "+X);
        out.flush();
        Chat_Client_GUI.TF_message.setText("");
    }
}

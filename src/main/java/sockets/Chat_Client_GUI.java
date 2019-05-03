package sockets;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

public class Chat_Client_GUI {
    private static Chat_Client ChatClient;
    public static String username = "Anonymous";

    public static JFrame mainWindow = new JFrame();
    private static JButton B_about = new JButton();
    private static JButton B_connect = new JButton();
    private static JButton B_disconnect = new JButton();
    private static JButton B_help = new JButton();
    private static JButton B_send = new JButton();
    private static JLabel L_message = new JLabel();
    public static JTextField TF_message = new JTextField(20);
    private static JLabel L_conversation = new JLabel();
    public static JTextArea TA_conversation = new JTextArea();
    private static JScrollPane SP_conversation = new JScrollPane();
    private static JLabel L_online = new JLabel();
    public static JList JL_online = new JList();
    private static JScrollPane SP_online = new JScrollPane();
    private static JLabel L_loggedInAs = new JLabel();
    private static JLabel L_loggedInAsBox = new JLabel();

    public static JFrame logInWindow = new JFrame();
    public static JTextField TF_userNameBox = new JTextField(20);
    private static JButton B_enter = new JButton("Enter");
    private static JLabel L_enterUserName = new JLabel("Enter User Name : ");
    private static JPanel P_login = new JPanel();

    public static void main(String args[]){
        buildMainWindow();
        initialize();
    }
    public static void connect(){
        try{
            final int PORT = 444;
            final String HOST = "Galactica";
            Socket sock = new Socket(HOST,PORT);
            System.out.println("You are connected to : "+HOST);

            ChatClient = new Chat_Client(sock);

            PrintWriter out = new PrintWriter(sock.getOutputStream());
            out.println(username);
            out.flush();

            Thread X = new Thread(ChatClient);
            X.start();

        }catch (Exception X){
            System.out.println(X);
            JOptionPane.showMessageDialog(null,"Server not responding...");
            System.exit(0);
        }
    }
    public static void initialize(){
        B_send.setEnabled(false);
        B_disconnect.setEnabled(false);
        B_connect.setEnabled(true);
    }
    public static void buildLogInWindow(){
        logInWindow.setTitle("Username : ");
        logInWindow.setSize(400,100);
        logInWindow.setLocation(250,200);
        logInWindow.setResizable(false);
        P_login = new JPanel();
        P_login.add(L_enterUserName);
        P_login.add(TF_userNameBox);
        P_login.add(B_enter);
        logInWindow.add(P_login);

        loginAction();
        logInWindow.setVisible(true);
    }

    public static void buildMainWindow(){
        mainWindow.setTitle(username+"'s Chat Box");
        mainWindow.setSize(450,500);
        mainWindow.setLocation(220,180);
        mainWindow.setResizable(false);
        configureMainWindow();
        mainWindowAction();
        mainWindow.setVisible(true);
    }
    public static void configureMainWindow(){
        mainWindow.setBackground(new java.awt.Color(255,255,255));
        mainWindow.setSize(500,320);
        mainWindow.getContentPane().setLayout(null);

        B_send.setBackground(new java.awt.Color(0,0,255));
        B_send.setForeground(new java.awt.Color(255,255,255));
        B_send.setText("SEND");
        mainWindow.getContentPane().add(B_send);
        B_send.setBounds(250,40,81,25);

        B_disconnect.setBackground(new java.awt.Color(0,0,255));
        B_disconnect.setForeground(new java.awt.Color(255,255,255));
        B_disconnect.setText("DISCONNECT");
        mainWindow.getContentPane().add(B_disconnect);
        B_disconnect.setBounds(10,40,110,25);

        B_connect.setBackground(new java.awt.Color(0,0,255));
        B_connect.setForeground(new java.awt.Color(255,255,255));
        B_connect.setText("CONNECT");
        B_connect.setToolTipText("");
        mainWindow.getContentPane().add(B_connect);
        B_connect.setBounds(130,40,110,25);

        B_help.setBackground(new java.awt.Color(0,0,255));
        B_help.setForeground(new java.awt.Color(255,255,255));
        B_help.setText("HELP");
        mainWindow.getContentPane().add(B_help);
        B_help.setBounds(420,40,70,25);

        B_about.setBackground(new java.awt.Color(0,0,255));
        B_about.setForeground(new java.awt.Color(255,255,255));
        B_about.setText("ABOUT");
        mainWindow.getContentPane().add(B_about);
        B_about.setBounds(340,40,75,25);

        L_message.setText("Message: ");
        mainWindow.getContentPane().add(L_message);
        L_message.setBounds(10,10,60,20);

        TF_message.setForeground(new java.awt.Color(0,0,255));
        TF_message.requestFocus();
        mainWindow.getContentPane().add(TF_message);
        TF_message.setBounds(70,4,260,30);

        L_conversation.setHorizontalAlignment(SwingConstants.CENTER);
        L_conversation.setText("Conversation");
        mainWindow.getContentPane().add(L_conversation);
        L_conversation.setBounds(100,70,140,16);

        TA_conversation.setColumns(20);
        TA_conversation.setFont(new java.awt.Font("Times New Roman",0,12));
        TA_conversation.setForeground(new java.awt.Color(0,0,255));
        TA_conversation.setLineWrap(true);
        TA_conversation.setRows(5);
        TA_conversation.setEditable(false);

        SP_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_conversation.setViewportView(TA_conversation);
        mainWindow.getContentPane().add(SP_conversation);
        SP_conversation.setBounds(10,90,330,180);

        L_online.setHorizontalAlignment(SwingConstants.CENTER);
        L_online.setText("Currently Online");
        L_online.setToolTipText("");
        mainWindow.getContentPane().add(L_online);
        L_online.setBounds(350,70,130,16);

        String[] testNames = {"Pipi", "Kethz", "Hish", "Thar"};
        JL_online.setForeground(new java.awt.Color(0,0,255));
        JL_online.setListData(testNames);

        SP_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_online.setViewportView(JL_online);
        mainWindow.getContentPane().add(SP_online);
        SP_online.setBounds(350,90,130,180);

        L_loggedInAs.setFont(new java.awt.Font("Times New Roman",0,12));
        L_loggedInAs.setText("Currently Logeed In As");
        mainWindow.getContentPane().add(L_loggedInAs);
        L_loggedInAs.setBounds(348,0,140,15);

        L_loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
        L_loggedInAsBox.setFont(new java.awt.Font("Times New Roman",0,12));
        L_loggedInAsBox.setForeground(new java.awt.Color(255,0,0));
        L_loggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
        mainWindow.getContentPane().add(L_loggedInAsBox);
        L_loggedInAsBox.setBounds(340,17,150,20);
    }
    public static void loginAction(){
        B_enter.addActionListener(
                new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        action_B_enter();
                    }
        });
    }
    public static void action_B_enter(){
        if(!TF_userNameBox.getText().equals("")){
            username = TF_userNameBox.getText().trim();
            L_loggedInAsBox.setText(username);
            Chat_Server.currentUsers.add(username);
            mainWindow.setTitle(username+"'s Chat Box");
            logInWindow.setVisible(false);
            B_send.setEnabled(true);
            B_disconnect.setEnabled(true);
            B_connect.setEnabled(false);
            connect();
        }else{
            JOptionPane.showMessageDialog(null,"Please Enter Your Name!");
        }
    }
    public static void mainWindowAction(){
        B_send.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        action_B_send();
                    }
                }
        );
        B_disconnect.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        action_B_disconnect();
                    }
                }
        );
        B_connect.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        buildLogInWindow();
                    }
                }
        );
        B_help.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        action_B_help();
                    }
                }
        );
        B_about.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        action_B_help();
                    }
                }
        );
    }
    public static void action_B_send(){
        if(!TF_message.getText().equals("")){
            ChatClient.send(TF_message.getText());
            TF_message.requestFocus();
        }
    }
    public static void action_B_disconnect(){
        try{
            ChatClient.disconnect();
        }catch (Exception Y){
            Y.printStackTrace();
        }
    }
    public static void action_B_help(){
        JOptionPane.showMessageDialog(null,"Multi-Client Chat Programme\nImalsha Gunasekara 2019");
    }
}

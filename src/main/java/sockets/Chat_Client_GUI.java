package sockets;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

public class Chat_Client_GUI {
    private static Chat_Client ChatClient;
    private String username = "Anonymous";

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

    }
}

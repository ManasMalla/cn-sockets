
package com.mycompany.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class MyServer{
    public static void main(String[] args){
        try{
            System.out.println("Waiting... ");
            ServerSocket ss = new ServerSocket(5003);
            Socket socket = ss.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
           
            Scanner scanner= new Scanner(System.in);
            dos.writeUTF("Connection established");
            String response = dis.readUTF();
            while(response != "bye"){
                System.out.println(response);
                String request = scanner.nextLine();
                dos.writeUTF(request);
                response = dis.readUTF();
            }
            dos.writeUTF("bye");
            // ss.close();
        }catch(IOException e){
            System.out.println("I/O Error: " + e.getMessage());
        }catch(SecurityException e){
            System.out.println("Security Error: " + e.getMessage());
        }catch(IllegalArgumentException e){
            System.out.println("Invalid Argument Error: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
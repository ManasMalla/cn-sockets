
package com.mycompany.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class MyClient{
    public static void main(String[] args){
        try{
            System.out.println("Waiting... ");
            Socket socket = new Socket("localhost", 5003);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner= new Scanner(System.in);
            String response = dis.readUTF();
            while(response != "bye"){
                System.out.println(response);
                String request = scanner.nextLine();
                dos.writeUTF(request);
                response = dis.readUTF();
            }
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
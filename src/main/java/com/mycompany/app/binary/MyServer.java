
package com.mycompany.app.binary;

import java.io.*;
import java.net.*;

public class MyServer {
    public final static int SOCKET_PORT = 5006; // corrected variable declaration
    public final static String FILE_TO_SEND = "binary.bin"; // corrected variable declaration
    
    public static void main(String[] args) throws IOException { // corrected method signature
        FileInputStream fis = null; // corrected variable declaration
        BufferedInputStream bis = null; 
        OutputStream os = null; // corrected variable declaration
        ServerSocket servsock = null; // corrected variable declaration
        Socket sock = null; // corrected variable declaration
        
        try {
            servsock = new ServerSocket(SOCKET_PORT); // corrected object creation syntax
            while (true) {
                System.out.println("Waiting...");
                try {
                    sock = servsock.accept();
                    System.out.println("Accepted connection " + sock); // corrected print statement
                    // send file
                    File myFile = new File(FILE_TO_SEND); // corrected variable name
                    byte[] mybytearray = new byte[(int) myFile.length()];
                    fis = new FileInputStream(myFile); // corrected object creation syntax
                    bis = new BufferedInputStream(fis);
                    bis.read(mybytearray, 0, mybytearray.length);
                    os = sock.getOutputStream(); // corrected object creation syntax
                    System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
                    os.write(mybytearray, 0, mybytearray.length);
                    os.flush();
                    System.out.println("Done.");
                } finally {
                    if (bis != null) bis.close(); // corrected condition and method call
                    if (os != null) os.close(); // corrected condition and method call
                    if (sock != null) sock.close(); // corrected condition and method call
                }
            }
        } finally {
            if (servsock != null) servsock.close(); // corrected condition and method call
        }
    }
}

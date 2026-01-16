package bai2;

import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner nhap = new Scanner(System.in);
        Socket socket = new Socket("localhost", 2008);
        System.out.println("Da ket noi voi server ");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write("gET / HTTP/1.1\r\n");
            out.write("Host: localhost\r\n");
            out.write("Connection: close\r\n");
            out.write("\r\n");
            out.flush();
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("Da ngat ket noi voi server !");
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }
    }

}

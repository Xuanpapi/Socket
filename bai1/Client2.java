package bai1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws Exception {
        Scanner nhap = new Scanner(System.in);
        Socket socket2 = new Socket("localhost", 2008);

        try {

            DataInputStream client_nhan = new DataInputStream(socket2.getInputStream());
            DataOutputStream client_gui = new DataOutputStream(socket2.getOutputStream());

            while (true) {
                System.out.print("Nhap vao noi dung gui: ");
                String name = nhap.nextLine();
                byte[] data = name.getBytes();
                client_gui.writeInt(data.length);
                client_gui.write(data);
                client_gui.flush();
                if (name.equalsIgnoreCase("stop")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Loi ket noi voi server ");

        } finally {
            try {
                if (socket2 != null) {
                    socket2.close();
                    System.out.println("Da ngat ket noi voi server !");
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }

    }

}

package bai1;

import java.io.*;
import java.net.*;

import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Scanner nhap = new Scanner(System.in);
        Socket socket = new Socket("localhost", 2006);
        System.out.println("Da ket noi toi server !");
        DataOutputStream client_gui = new DataOutputStream(socket.getOutputStream());
        DataInputStream client_nhan = new DataInputStream(socket.getInputStream());
        int chon;
        String rep;
        do {
            System.out.println("Menu");
            System.out.println("1.Tao tai khoan"
                    + "\n2.Tim tai khoan"
                    + "\n3. Thoat"
                    + "\n4.Ngat ket noi server "
                    + "\n5.Dang nhap ");
            System.out.print("Nhap vao lua chon: ");
            chon = nhap.nextInt();
            nhap.nextLine();
            switch (chon) {
                case 1:
                    client_gui.writeUTF("1");
                    client_gui.flush();
                    System.out.print("Tai khoan: ");
                    String tai_khoan = nhap.nextLine();
                    client_gui.writeUTF(tai_khoan);
                    client_gui.flush();
                    System.out.print("Mat khau: ");
                    String mat_khau = nhap.nextLine();
                    client_gui.writeUTF(mat_khau);
                    client_gui.flush();
                    rep = client_nhan.readUTF();
                    System.out.println(rep);
                    break;
                case 2:
                    client_gui.writeUTF("2");
                    client_gui.flush();
                    System.out.print("Nhap vao ten tai khoan can tim: ");
                    String taikhoan = nhap.nextLine();
                    client_gui.writeUTF(taikhoan);
                    client_gui.flush();
                    rep = client_nhan.readUTF();
                    System.out.println(rep);
                    break;
                case 3:
                    System.out.println("Da thoat chuong trinh !");
                    break;
                case 4:
                    client_gui.writeUTF("4");
                    client_gui.flush();
                    rep = client_nhan.readUTF();
                    System.out.println(rep);
                    chon = 3;
                    socket.close();
                    break;
                case 5:
                    client_gui.writeUTF("5");
                    client_gui.flush();
                    System.out.print("Tai khoan: ");
                    String tk = nhap.nextLine();
                    client_gui.writeUTF(tk);
                    System.out.print("Mat khau: ");
                    String mk = nhap.nextLine();
                    client_gui.writeUTF(mk);
                    client_gui.flush();
                    rep = client_nhan.readUTF();
                    System.out.println(rep);
                    break;
                default:
                    System.out.println("Lua chon khong hop le !");
                    break;
            }
        } while (chon != 3);
    }

}

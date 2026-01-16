package bai1;

import java.io.*;
import java.net.*;

public class TCPServer {
    static Quanly ql = new Quanly();

    public static void start_server() throws Exception {
        ServerSocket server = new ServerSocket(2006);
        System.out.println("Server dang doi client ket noi :....");

        while (true) {
            Socket client = server.accept();
            System.out.println("Client da ket noi : " + client.getInetAddress());
            new Thread(() -> xu_ly_request(client)).start();
        }
    }

    private static void xu_ly_request(Socket client) {
        try {
            // Quanly ql = new Quanly();
            DataInputStream server_nhan = new DataInputStream(client.getInputStream());
            DataOutputStream server_gui = new DataOutputStream(client.getOutputStream());
            while (true) {
                String request = server_nhan.readUTF();
                if (request.equalsIgnoreCase("1")) {
                    String tai_khoan = server_nhan.readUTF();
                    // String mat_khau = server_nhan.readUTF();
                    // kiem tra xem tai khoan bi trung khong ?
                    try {
                        ql.kiem_tra(tai_khoan);
                    } catch (Exception e) {
                        server_gui.writeUTF(e.getMessage());
                        server_gui.flush();
                    }
                    String mat_khau = server_nhan.readUTF();
                    ql.them_user(new User(tai_khoan, mat_khau));
                    server_gui.writeUTF("Dang ki tai khoan thanh cong !");
                    server_gui.flush();
                } else if (request.equalsIgnoreCase("2")) {
                    String tai_khoan = server_nhan.readUTF();
                    try {
                        String kq = ql.tim_tai_khoan(tai_khoan);
                        server_gui.writeUTF("Da tim thay !" + "\n" + kq);
                        server_gui.flush();

                    } catch (Exception e) {
                        server_gui.writeUTF(e.getMessage());
                        server_gui.flush();
                    }
                } else if (request.equalsIgnoreCase("4")) {
                    server_gui.writeUTF("Da ngat ket noi voi server !");
                    server_gui.flush();
                    break;
                } else if (request.equalsIgnoreCase("5")) {
                    String tai_khoan = server_nhan.readUTF();
                    String mat_khau = server_nhan.readUTF();
                    try {
                        String kq = ql.dang_nhap(tai_khoan, mat_khau);
                        server_gui.writeUTF(kq);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Loi ket noi: " + e.getMessage());
        } finally {
            try {
                if (client != null) {

                    System.out.println("Da ngat ket noi voi client:" + client.getInetAddress());
                    client.close();
                }
            } catch (Exception e) {
                System.out.println("Loi " + e.getMessage());
            }
        }
    }

    public static void main(String[] asgr) throws Exception {
        start_server();
    }
}

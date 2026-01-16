package UDP;

import java.net.*;
import java.util.Scanner;

import org.json.JSONObject;

public class ClientUDP {
    public static void main(String[] args) throws Exception {
        // Tạo socket UDP cho client (OS tự cấp port ngẫu nhiên)
        DatagramSocket client = new DatagramSocket();
        // IP server (localhost nếu chạy cùng máy)
        InetAddress ip = InetAddress.getByName("localhost");
        // Dùng để nhập nội dung chat từ bàn phím
        Scanner nhap = new Scanner(System.in);
        JSONObject obj = new JSONObject();// tao json de truyen di
        System.out.print("Nhap vao so a: ");
        int a = nhap.nextInt();
        System.out.print("Nhap vao so b: ");
        int b = nhap.nextInt();
        nhap.nextLine();
        System.out.print("Nhap vao phep tinh thuc hien: ");
        String phep_tinh = nhap.nextLine();
        // tao key va value
        obj.put("a", a);
        obj.put("b", b);
        obj.put("phep_tinh", phep_tinh);
        byte[] data = obj.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, 9000);// tao packet de gui den server
        client.send(packet);

        // tao buffer de nhan phan hoi tu server
        byte[] buffer = new byte[1024];
        DatagramPacket packet_respone = new DatagramPacket(buffer, buffer.length);// tao goi tin nhan
        client.receive(packet_respone);// cho goi tin phan hoi
        String noi_dung = new String(packet_respone.getData(), 0, packet_respone.getLength());
        JSONObject data_nhan = new JSONObject(noi_dung);
        System.out.println("ket qua la: " + data_nhan.getInt("ket qua"));

    }
}

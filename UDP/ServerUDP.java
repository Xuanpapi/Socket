package UDP;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class ServerUDP {
    public static void start_server() throws Exception {
        DatagramSocket server = new DatagramSocket(9000);// Tạo socket UDP lắng nghe cổng 9000
        ExecutorService pool = Executors.newFixedThreadPool(5);
        System.out.println("UDP Server dang cho...");
        while (true) {
            byte[] buffer = new byte[1024];// Tạo buffer mới để chứa dữ liệu nhận
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);// Gói tin UDP để nhận dữ liệu
            server.receive(packet);// Chờ client gửi gói tin (blocking)
            pool.execute(() -> xu_ly_request(packet, server));
        }
    }

    private static void xu_ly_request(DatagramPacket packet, DatagramSocket server) {
        try {
            String json = new String(packet.getData(), 0, packet.getLength());
            JSONObject data = new JSONObject(json);
            int a = data.getInt("a");
            int b = data.getInt("b");
            String phep_tinh = data.getString("phep_tinh");
            JSONObject respone = new JSONObject();// tao json tra ve client
            if (phep_tinh.equalsIgnoreCase("nhan")) {
                respone.put("ket qua", a * b);
            }
            if (phep_tinh.equalsIgnoreCase("cong")) {
                respone.put("ket qua", a + b);
            }
            // tao packet de gui ve client
            byte[] rep = respone.toString().getBytes();
            DatagramPacket gui_di = new DatagramPacket(rep, rep.length, packet.getAddress(), packet.getPort());
            server.send(gui_di);

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    public static void main() throws Exception {
        start_server();
    }
}

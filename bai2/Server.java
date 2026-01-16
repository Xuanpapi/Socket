package bai2;

import java.io.*;
import org.json.JSONObject;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void start_sever() throws Exception {
        ServerSocket server = new ServerSocket(2008);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        System.out.println("Server dang doi client ket noi.....");
        while (true) {
            Socket client = server.accept();
            System.out.println("Client da ket noi: " + client.getInetAddress());
            pool.execute(() -> xu_ly_request(client));
        }
    }

    private static void xu_ly_request(Socket client) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String request = in.readLine();
            String[] parts = request.split(" ");
            if (parts.length != 3) {
                send400(out);
                client.close();
                return;
            }

            String method = parts[0];
            String path = parts[1];
            String version = parts[2];

            // Bỏ qua header
            String line;
            while (!(line = in.readLine()).isEmpty()) {
                System.out.println(line);
            }

            // ===== Xử lý đơn giản =====
            if (!version.startsWith("HTTP/")) {
                send400(out);
            } else if (!method.equals("GET")) {
                send405(out);
            } else {
                send200(out, "<h1>OK</h1>");
            }

        } catch (Exception e) {
            System.out.println("Loi ket noi: " + e.getMessage());
        } finally {
            try {
                if (client != null) {
                    System.out.println("Da ngat ket noi voi client " + client.getInetAddress());
                    client.close();
                }
            } catch (Exception e) {
                System.out.println("Loi : " + e.getMessage());
            }
        }
    }

    static void send200(BufferedWriter out, String body) throws Exception {
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Length: " + body.length() + "\r\n");
        out.write("Content-Type: text/html\r\n");
        out.write("\r\n");
        out.write(body);
        out.flush();
    }

    static void send400(BufferedWriter out) throws Exception {
        String body = "400 Bad Request";
        out.write("HTTP/1.1 400 Bad Request\r\n");
        out.write("Content-Length: " + body.length() + "\r\n");
        out.write("\r\n");
        out.write(body);
        out.flush();
    }

    static void send405(BufferedWriter out) throws Exception {
        String body = "405 Method Not Allowed";
        out.write("HTTP/1.1 405 Method Not Allowed\r\n");
        out.write("Content-Length: " + body.length() + "\r\n");
        out.write("\r\n");
        out.write(body);
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        Server.start_sever();
    }

}

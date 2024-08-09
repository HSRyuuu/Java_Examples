package socket.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;

        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // host와 port에 해당하는 소켓 생성 (host: ServerMain (localhost) , port: 8080)
            socket = new Socket("127.0.0.1", 8000);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while(true) {
                System.out.print("전송하기>>> ");
                String outputMessage = scanner.nextLine();
                out.println(outputMessage); // 클라이언트 콘솔에 출력
                out.flush(); // 서버측 콘솔로 전송
                if ("quit".equalsIgnoreCase(outputMessage)) break;

                String inputMessage = in.readLine();
                System.out.println("From Server: " + inputMessage);
                if ("quit".equalsIgnoreCase(inputMessage)) break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                scanner.close();
                if (socket != null) socket.close();
                System.out.println("서버연결종료");
            } catch (IOException e) {
                System.out.println("socket close error");
            }
        }
    }
}

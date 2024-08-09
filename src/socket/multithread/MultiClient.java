package socket.multithread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.chrono.IsoEra;
import java.util.Scanner;


public class MultiClient {
    String username;

    public MultiClient(String username) {
        this.username = username;
    }

    public void start() {
        BufferedReader in = null;
        Socket socket = this.connectSocket();

        try {
            // thread start
            Thread clientThread = new ClientThread(socket, username);
            clientThread.start();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String inputMsg = in.readLine();
                if(("[" + username + "]님이 나가셨습니다").equals(inputMsg)) break;
                System.out.println(inputMsg);
            }
        } catch (IOException e) {
            System.out.println("[서버 접속끊김]");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[서버 연결종료]");
    }

    private Socket connectSocket(){
        try{
            Socket socket = new Socket("localhost", 8000);
            System.out.println("[서버와 연결되었습니다. localhost:8080]");
            return socket;
        }catch (IOException e){
            System.out.println("서버측 소켓이 존재하지 않습니다.");
        }
        return null;
    }
}

class ClientThread extends Thread {
    Socket socket = null;
    String name;
    PrintStream out;

    Scanner scanner = new Scanner(System.in);

    public ClientThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.out = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        this.sendName();// 최초1회는 client의 name을 서버에 전송
        while (true) {
            String outputMsg = scanner.nextLine();
            out.println(outputMsg);
            out.flush();
            if("quit".equals(outputMsg)) break;
        }
    }

    private void sendName(){
        out.println(name);
        out.flush();
    }
}


package socket.multithread;

public class ClientMain2 {
    public static void main(String[] args) {
        MultiClient multiClient = new MultiClient("happy2");
        multiClient.start();
    }
}

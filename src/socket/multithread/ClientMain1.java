package socket.multithread;

public class ClientMain1 {
    public static void main(String[] args) {
        MultiClient multiClient = new MultiClient("hsryu1");
        multiClient.start();
    }
}

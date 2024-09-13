import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        });
        thread.start();
        thread.interrupt();
        System.out.println("fin");
    }
}

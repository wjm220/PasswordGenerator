import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        for (int i = 0; i < 1_000_000; i++) {
            String password = PasswordGenerator.generatePassword(12, true, true, true);
            System.out.println(password);
        }

        long elapsedTime = TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        System.out.println("\nCompleted in " + String.valueOf((double) (System.nanoTime() - startTime) / 1_000_000_000) + "s");
    }
}

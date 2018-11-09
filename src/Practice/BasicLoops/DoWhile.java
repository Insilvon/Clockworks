package Practice.BasicLoops;

import java.util.Scanner;

public class DoWhile {
    public static void run() {
        Scanner inp = new Scanner(System.in);
        String allowed = "1234567890";
        String userInput;
        do {
            System.out.println("Please input your number.");
            userInput = inp.nextLine();
        } while (!allowed.contains(userInput));
    }
}

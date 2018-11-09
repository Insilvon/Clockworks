package Practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void deadMan(int counter){
        String person = "";
        switch(counter){
            case(1):
                person  = " O \n";
                System.out.println(person);
                break;
            case(2):
                person  = " O \n/";
                System.out.println(person);
                break;
            case(3):
                person  = " O \n/|";
                System.out.println(person);
                break;
            case(4):
                person  = " O \n/|\\";
                System.out.println(person);
                break;
            case(5):
                person  = " O \n/|\\\n/";
                System.out.println(person);
                break;
            case(6):
                person  = " O \n/|\\\n/ \\";
                System.out.println(person);
                break;
            default:
                break;
        }
    }
    public static void run(){


        File words = new File("../Dictionary.txt");
        try {
            String repeat = "y";
            do {
                Scanner in = new Scanner(words);

                Random rand = new Random();
                int max = 109562;
                int min = 1;

                int line = rand.nextInt((max - min) + 1) + min;
                String word = "";
                for (int i = 0; i < line; i++) word = in.nextLine();

                String[] display = new String[word.length()];
                for (int i = 0; i<display.length;i++) {
                    display[i] = "_";
                    System.out.print(display[i]+" ");
                }
                ArrayList<String> guesses = new ArrayList<String>();
                String guess = "";
                int counter = 6;
                Scanner inp = new Scanner(System.in);

                do {
                    guess = inp.nextLine().toLowerCase();

                    if (guess.equals(word)) break;
                    if (word.contains(guess)){
                        int check = 0;
                        for (int i = 0; i<word.length(); i++){
                            if (word.charAt(i)==guess.charAt(0)){
                                display[i] = guess;
                            }
                            else {
                                if (display[i].equalsIgnoreCase("_")) check++;
                            }
                        }
                        if (check==0) break;
                    }
                    else
                    {
                        counter--;
                        if (guess.length()==1)guesses.add(guess);
                    }

                    deadMan(counter);
                    for (int i = 0; i<guesses.size();i++) System.out.print(guesses.get(i));
                    System.out.println();
                    for (int i = 0; i<display.length;i++) System.out.print(display[i]+" ");

                } while (guess != word && counter != 0);

                if (counter != 0) {
                    System.out.println("You won!");
                    System.out.println("You guessed " + word);
                }
                else {
                    System.out.println("You lost!");
                    System.out.println("The word was "+word);
                }
                System.out.println("Would you like to play again? Y/N");
                repeat = inp.nextLine();
            } while (repeat.equalsIgnoreCase("y"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}

package Tutoring.COSC241;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Insilvon, crcrowe0@frostburg.edu
 */
public class Project2EC {
//    public static ArrayList<String> keys = new ArrayList<>();
//    public static ArrayList<ArrayList<String>> values = new ArrayList<>();

    public static void run() throws FileNotFoundException {

        String userInput = "8226228"; //tacocat
        userInput = "6469463"; //ohmygod

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();

        File file = new File("../COSC241_P2_EnglishWordList.txt");
        Scanner r = new Scanner(file);
        System.out.println("[System] Scanning File...");

        long start = System.currentTimeMillis();

        while (r.hasNext()) {
            String line = r.nextLine();
            String key = convert(line);
            words.add(line);
            numbers.add(key);
        }

        long end = System.currentTimeMillis()-start;

        System.out.println("[System] Completed build in: "+end/1000+"s.");


        System.out.println("[System] Now Searching for "+userInput);

        ArrayList<String> answer = arraySearch(words, numbers, userInput);
        int counter = 10;
        System.out.println("[System] Total number of matches found: "+answer.size());
        System.out.print("========================================================================================================================");
        for (int i = 0; i<answer.size();i++) {
            if (i%counter==0) System.out.println();
            System.out.print(answer.get(i)+"\t");
        }
        System.out.println();
        System.out.println("========================================================================================================================");
    }

    public static ArrayList<String> arraySearch(ArrayList<String> words, ArrayList<String> nums, String line) {
        ArrayList<String> answers = new ArrayList<String>();
        String firstChars = "";

        for (int i = 0; i<line.length();i++) {

            firstChars+=line.charAt(i);
            String eol = "";

            for (int j = i+1; j<line.length(); j++) {
                eol+=line.charAt(j);
            }

            ArrayList<String> firstWords = new ArrayList<>();
            ArrayList<String> theRest = new ArrayList<>();

            for (int l = 0; l<nums.size(); l++) {
                if (nums.get(l).equals(firstChars)) firstWords.add(words.get(l));
            }
            if (firstWords.size()!=0) {
                for (int j = 0; j < nums.size(); j++)
                { if (nums.get(j).equals(eol)) theRest.add(words.get(j)); }

                ArrayList<String> temp3 = arraySearch(words, nums, eol);

                for (int j = 0; j<temp3.size();j++)
                { theRest.add(temp3.get(j)); }
                for (int k = 0; k < firstWords.size(); k++) {
                    for (int l = 0; l < theRest.size(); l++)
                    { answers.add(firstWords.get(k) + "-" + theRest.get(l)); }
                }
            }
        }
        return answers;
    }

    public static String convert(String word) {
        String answer = "";
        for (int i = 0; i<word.length();i++) {
            String t = Character.toString(word.charAt(i));
            if ("abc".contains(t)) answer += "2";
            else if ("def".contains(t))  answer += "3";
            else if ("ghi".contains(t))  answer += "4";
            else if ("jkl".contains(t))  answer += "5";
            else if ("mno".contains(t))  answer += "6";
            else if ("pqrs".contains(t)) answer += "7";
            else if ("tuv".contains(t))  answer += "8";
            else                         answer += "9";
        }
        return answer;
    }

}
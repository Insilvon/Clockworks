package My241.project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import My241.collection.MyVector;
/**
 * Program will output any combination of possible words as a list of combinations: (<word>|<word2>|<word3>|...), ...
 *	For example, the number "2868378" could return a|to|test
 *	a|to|test: 2868378
 *	For example, the number "2283647" could return cat|dogs
 * @author Colin
 *
 */
public class Project2 {
	
	/**
	 * Test Method - runs various methods to find word matches to a user inputted phone number via an 
	 * english word text document.
	 * @throws FileNotFoundException
	 */
	public static void test() throws FileNotFoundException{
		String response = getResponse();
		
		organizeIt();
		
		searchItBasic(response);
		
		searchItComplex(response);
		
		System.out.println("Answers are...");
		int spacer = 0;
		for (int i = 0;i<answers.size();++i){
			if (spacer == 10){
				System.out.println();
				spacer = 0;
			}
			System.out.print(answers.get(i)+", ");
			spacer ++;
		}
	}
	/**
	 * Method which prompts the user to input a 7 character phone number. 
	 * @return Returns String response which contains numbers 2-9. Rejects 0, 1, or less than/more than 7 characters.
	 */
	public static String getResponse(){
		Scanner inp = new Scanner(System.in);
		String response;
		System.out.println("Please input your 7 digit telephone number, without 1s or 0s.");
		response = inp.nextLine();
		int check = String.valueOf(response).length();
		while (response.contains("0")||response.contains("1")||check!=7){
			System.out.print(check);
			System.out.println("Error! Either your number contained a one, zero, or your number was not 7 characters.");
			System.out.println("Please re-enter!");
			Scanner inp2 = new Scanner(System.in);
			response = inp2.nextLine();
			check = String.valueOf(response).length();
		}//end while
		return response;
	}
	
	/**
	 * A quick search which takes in 7-long string response, searches COSC241_P2_EnglishWordList.txt, and returns
	 * any words which, when converted to numbers using the standard telephone keyboard, match the response.
	 * @param response 7 character string of numbers 2-9, from user Input.
	 * @throws FileNotFoundException
	 */
	public static void searchItBasic(String response) throws FileNotFoundException{
		for (int i = 0;i<list7.size();++i){
			boolean check = compareIt(response, 0, list7.get(i));
			if (check == true){
				String adding = list7.get(i);
				answers.add(adding);
			}
			else{
			}
		}//end for
	}//end method
	
	/**
	 * A longer search which scans through a list of words 6 characters long or less. It converts the current word in the list to numbers
	 * via a phone keypad and compares against a user inputted response. If it's an exact match, the program will add it to the answers list. 
	 * If it's a match, but isn't the right size, it'll run a follow up program to fill in the blanks.
	 * @param response 7 character string of numbers 2-9, from user Input.
	 * @throws FileNotFoundException
	 */
	public static void searchItComplex(String response) throws FileNotFoundException{
		String current;
		String currentNumbers;
		int folly;
		int responseLength = response.length();
		int currentLength;
		for (int i = 0;i<list8.size();++i){  //cycle through every element of list 8
			current = list8.get(i);
			currentLength = current.length();
			boolean check = false;
			check = compareIt(response, 0, current);
			if (check==true){
				int place = currentLength;
				String followup = current;
				ArrayList<String> returned =new ArrayList();
				returned = followUp(response,place);//
				for (int j = 0;j<returned.size();++j){
				 followup = current;
				 	followup += "|";
					followup += returned.get(j);
					folly = followup.length();
					answers.add(followup);
					}
			}
			//compareIt to response
		}
//2283647 catdogs
	}
	
	/**
	 * A follow up program to serve as a recursive extension to searchItComplex. Takes in the user response under the name "old" and the
	 * current place in the response to indicate which letter to start searching for. Will search through list8 to find any words/combination of words that would
	 * equal the required search terms.
	 * @param old The original 7-character user response. chars 2-9
	 * @param place Pointer to indicate which index of old to begin the search at.
	 * @return an arraylist, consisting of every possible combination of words that could fill old, from place to end.
	 */
	public static ArrayList followUp(String old, int place){
		//Let's make a string with our remaining values to find. It'll be our new response
		ArrayList<String> answered = new ArrayList();
		String response = "";
		String current;
		String currentNumbers = null;
		int oldLength = old.length();
		int currentLength;
		for (int i = place;i<oldLength;++i){
			char c = old.charAt(i);
			response += c;
		}
		int responseLength = response.length();
		
		for (int j = 0;j<list8.size();++j){ //loop through whole document
			current = list8.get(j);
			currentLength = current.length();
			
			if (currentLength==response.length()){//exact length match
				currentNumbers = convertIt(current.charAt(0),current,3);
				if (response.equals(currentNumbers)==true){
						answered.add(current);
				}
			}
			
			else {//current length has to be less
				if (currentLength<response.length()){
					boolean test = compareIt(response, 0, current);
					if (test==true){ //_---------------------
						int place1 = currentLength;
						ArrayList<String> returned =new ArrayList();
						returned = followUp(response,place1);
						String followup = current;
						int folly;
						for (int k = 0;k<returned.size();++k){ //every final possible letter
							followup = current;
							followup += "|";
							followup += returned.get(k);
							folly = followup.length();
							answered.add(followup);
							}
					}
				}
			}
		}
		return answered;	
		
	}
	
	/**
	 * Converts a character into a String of your choice. (character to convert, parent string, method to use)
	 * @param a Character you wish to convert to another character.
	 * @param b Which conversion you want to run. Specify 0 to get a starting letter range for your number, specify 1 to get a Number for a letter, specify 2 to get a 
	 * Number to All Possible Letters, or specify 3 to convert an entire String of letters to an equivalent string of numbers.
	 * @param c Optional string input for third conversion method
	 * @return String containing your desired conversion.
	 */
	public static String convertIt(char a, String c, int b){	
		if (b==3){
			int d = c.length();
			String test ="";
			for (int i = 0;i<d;++i){
				a = c.charAt(i);
			switch(a){
			case 'a':
				test+="2";
				break;
			case 'b':
				test+="2";
				break;
			case 'c':
				test+="2";
				break;
			case 'd':
				test+="3";
				break;
			case 'e':
				test+="3";
				break;
			case 'f':
				test+="3";
				break;
			case 'g':
				test+="4";
				break;
			case 'h':
				test+="4";
				break;
			case 'i':
				test+="4";
				break;
			case 'j':
				test+="5";
				break;
			case 'k':
				test+="5";
				break;
			case 'l':
				test+="5";
				break;
			case 'm':
				test+="6";
				break;
			case 'n':
				test+="6";
				break;
			case 'o':
				test+="6";
				break;
			case 'p':
				test+="7";
				break;
			case 'q':
				test+="7";
				break;
			case 'r':
				test+="7";
				break;
			case 's':
				test+="7";
				break;
			case 't':
				test+="8";
				break;
			case 'u':
				test+="8";
				break;
			case 'v':
				test+="8";
				break;
			case 'w':
				test+="9";
				break;
			case 'x':
				test+="9";
				break;
			case 'y':
				test+="9";
				break;
			case 'z':
				test+="9";
				break;
			default:
				break;
			}
		}
			return test;
		}
		if (b==1){
			//Do a number conversion + return number per letter
			switch(a){
			case 'a':
				return "2";
			case 'b':
				return "2";
			case 'c':
				return "2";
			case 'd':
				return "3";
			case 'e':
				return "3";
			case 'f':
				return "3";
			case 'g':
				return "4";
			case 'h':
				return "4";
			case 'i':
				return "4";
			case 'j':
				return "5";
			case 'k':
				return "5";
			case 'l':
				return "5";
			case 'm':
				return "6";
			case 'n':
				return "6";
			case 'o':
				return "6";
			case 'p':
				return "7";
			case 'q':
				return "7";
			case 'r':
				return "7";
			case 's':
				return "7";
			case 't':
				return "8";
			case 'u':
				return "8";
			case 'v':
				return "8";
			case 'w':
				return "9";
			case 'x':
				return "9";
			case 'y':
				return "9";
			case 'z':
				return "9";
			default:
				return null;
			}
		}
		else if (b==2){
			//Do a letter conversion + return all possible letters
			switch(a){
			case '2':
				return "abc";
			case '3':
				return "def";
			case '4':
				return "ghi";
			case '5':
				return "jkl";
			case '6':
				return "mno";
			case '7':
				return "pqrs";
			case '8':
				return "tuv";
			case '9':
				return "wxyz";
			default:
				return null;
			}
		}
		else if (b==0){
			//Do a letter conversion + return first possible letter
			switch(a){
			case '2':
				return "a";
			case '3':
				return "d";
			case '4':
				return "g";
			case '5':
				return "j";
			case '6':
				return "m";
			case '7':
				return "p";
			case '8':
				return "t";
			case '9':
				return "w";
			default:
				return null;
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Converts a string from EnglishWordList.txt to numbers and compares to a section of the user response.
	 * @param response User Number Input
	 * @param responsePlace Current Place for the Number
	 * @param compare the raw String from EnglishWordList.txt to compare
	 * @return True or False, true if all of compare is found in response, false if even one character is off.
	 */
	public static boolean compareIt(String response, int responsePlace, String compare){
		int compareLength = compare.length();
		int count = responsePlace;
		boolean status = false;
		String quark = "";
		//Convert your word to numbers to check it
		for (int j = 0;j<compareLength;++j){
			quark = convertIt(compare.charAt(j), compare, 3);
		}
		//Compare your word to your section of Response
		for (int i = 0;i<compareLength;++i){
			if (response.charAt(count)==quark.charAt(i)){
				count++;
				status = true;
			}
			else {
				status = false;
				count = 0;
				break;
			}
		}//end for
		
		//Did it work?
		if (status==true){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Takes the EnglishWordList.txt file and sorts each line by size into various arrays to reduce search time later.
	 * @throws FileNotFoundException
	 */
	public static void organizeIt() throws FileNotFoundException{
		
		File words = new File("../COSC241_P2_EnglishWordList.txt");
		Scanner read = new Scanner(words);

		while (read.hasNextLine()){
			String current = read.nextLine();
			int size = current.length();
			switch(size){
			case 1:
				list8.add(current);
				break;
			case 2:
				list8.add(current);
				break;
			case 3:
				list8.add(current);
				break;
			case 4:
				list8.add(current);
				break;
			case 5:
				list8.add(current);
				break;
			case 6:
				list8.add(current);
				break;
			case 7:
				list7.add(current);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * A list of Strings taken from the COSC241_P2_EnglishWordList.txt document which contain 7 characters. 
	 */
	public static ArrayList<String> list7 = new ArrayList();
	/**
	 * A list of Strings taken from the COSC241_P2_EnglishWordList.txt document which contain 1 to 6 characters. 
	 */
	public static ArrayList<String> list8 = new ArrayList();
	/**
	 * A list of Strings that are equivalent to user input "response" from test method. 
	 */
	public static ArrayList<String> answers = new ArrayList();



}

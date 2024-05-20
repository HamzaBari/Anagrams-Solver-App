
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ApplicationRunner {

    static HashMap<String, ArrayList<String>> listAnagrams;
    static BufferedReader readFile;
    final static ArrayList<String> anagramDisplayList = new ArrayList<>();

    public static void findAnagrams() {
        listAnagrams = new HashMap<>();

        String fromPath = System.getProperty("user.dir") + File.separator + "lexicon.txt";
        File myInputFile = new File(fromPath);

        try {

            readFile = new BufferedReader(new FileReader(myInputFile));

            while (true) {

                String readingAllLines = readFile.readLine();

                if (readingAllLines == null) {
                    break;
                }

                char[] convertingStringLinesToArrayLines = readingAllLines.toCharArray();
                Arrays.sort(convertingStringLinesToArrayLines);
                String convertingArrayLinesToStringLines = Arrays.toString(convertingStringLinesToArrayLines);
                String duplicateAllLines = convertingArrayLinesToStringLines.replaceAll("[^A-Za-z]+", "");

                if (listAnagrams.containsKey(duplicateAllLines) == true) {

                    listAnagrams.get(duplicateAllLines).add(readingAllLines);

                } else {

                    ArrayList<String> joinTogather = new ArrayList<>();

                    joinTogather.add(readingAllLines);

                    listAnagrams.put(duplicateAllLines, joinTogather);

                }

            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
            System.exit(0);
        }

        System.out.println("Please enter a string (single word or phrase)");
        System.out.print("> ");

        Scanner usrInput = new Scanner(System.in);
        String inputtedWord = usrInput.nextLine();
        String finalInputtedWord = inputtedWord.replaceAll("[^A-Za-z]+", "");

        char[] chFinalInputtedWord = finalInputtedWord.toCharArray();
        Arrays.sort(chFinalInputtedWord);
        String tsFinalInputtedWord = Arrays.toString(chFinalInputtedWord);
        String finalWord = tsFinalInputtedWord.replaceAll("[^A-Za-z]+", "");

        if (listAnagrams.containsKey(finalWord)) {

            ArrayList<String> wordList = listAnagrams.get(finalWord);

            for (String listWordAnagrams : wordList) {
                anagramDisplayList.add(listWordAnagrams);
            }

            System.out.println("Possible anagrams for " + "\"" + inputtedWord + "\"" + ": " + anagramDisplayList);
            anagramDisplayList.clear();

        } else {

            System.out.println("Possible anagrams for " + "\"" + inputtedWord + "\"" + ": " + "[" + inputtedWord + "]");

        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        findAnagrams();
        System.out.println();
        System.out.print("Try again (1) or Exit (0) > ");

        while (userInput.hasNextLine()) {
            String input = userInput.next();
            if (input.equalsIgnoreCase("0")) {
                System.exit(0);
            } else if (input.equalsIgnoreCase("1")) {
                findAnagrams();
                System.out.println();
                System.out.print("Try again (1) or Exit (0) > ");
            }
        }
    }
}

import sheffield.EasyReader;
import sheffield.EasyWriter;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

        public class ComputerGuesses extends BaseGame{
            private String secretWord = "";
            private final int maxAttempts = 5;
            private int attemptsLeft = maxAttempts;
            private String [] attemptedWord= new String[7];
            private String fileName = "D:\\Moses\\upwork\\enoch\\my project 2024\\my project 2024\\sgb-words.txt";
            private final int wordSize = 5757;
            private char [] characterMarkedAsX= new char[5*6]; //26 Alphabetical letters
            private int characterMarkedAsXSize =0;
            private  int totalWordsAvailable = 5757;
            private boolean firstTime =true;

            private String [] availableAttempts;

            public ComputerGuesses(EasyReader keyboard, EasyWriter screen) {
                super(keyboard,screen);
            }

            @Override
            public void run() {

                Scanner scanner = new Scanner(System.in);

                System.out.println("Constructing a new game with Computer guesses");
                System.out.println("Select a word by choosing a number between 0 and "+(wordSize-1) +" : ");
                int selection = scanner.nextInt();

                //Read the word that the user picked
                secretWord =selectSecretWord(selection);

                while (attemptsLeft > 0) {
                    System.out.println("Attempt " + (maxAttempts - attemptsLeft + 1) + " of " + maxAttempts);
                    //Computer randomly select a word from the file
                    String guessInput = getRandomWord();

            /add to the attempted list/
                            attemptedWord [maxAttempts - attemptsLeft + 1] =guessInput;

                    //If it is the first attempt skip this
                    if((maxAttempts - attemptsLeft + 1) !=1){
                        // System.out.println("Total number of possible words is : "+getTotalWordsForAttempt());
                        //System.out.println("Selecting a random word number : "+getTotalWordsForAttempt()+"/"+totalWordsAvailable);
                        System.out.print("Correct word : "+secretWord +", previous guess : "+attemptedWord[(maxAttempts - attemptsLeft + 0 )]+"\n");

                    }

            /validate the input/
                    while (validateInput(guessInput)==false){
                        //System.out.println("Word is not valid – try again");
                        guessInput = getRandomWord();
                    }

                    //System.out.println("character not wanted  : " + Arrays.toString(characterMarkedAsX).toCharArray().toString());

                    System.out.println("Guess is : " + guessInput);
                    String response = generateResponse(guessInput);
                    System.out.println(response);

                    attemptsLeft--;

                }
            }

            private String generateResponse(String guess) {
                StringBuilder response = new StringBuilder();

                for (int i = 0; i < guess.length(); i++) {
                    char guessedChar = guess.charAt(i);
                    char secretChar = secretWord.charAt(i);

                    if (guessedChar == secretChar) {
                        response.append("+");
                    } else if (secretWord.contains(String.valueOf(guessedChar))) {
                        response.append("*");
                    } else {
                        response.append("X");

                        /*update the list of letter marked as X */
                        characterMarkedAsX [characterMarkedAsXSize] =guessedChar;
                        characterMarkedAsXSize++;

                    }
                }

                return response.toString();
            }

            /*
             * Validate the word input by the user it should check the size and if the word is valid */
            private boolean validateInput(String word) {
        /Check the length of the word/

                if (word.length() != secretWord.length()) {
                    System.out.println("Invalid input length. Please enter a word with " + secretWord.length() + " characters.");
                    return false;
                }

        /Check if it is a valid word only letters/
                if (!word.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid word entered. Please use characters from a-z only.");
                    return false;
                }

        /Check if the word contains the characters marked as X on the previous guess/
                for(int i=0; i< characterMarkedAsX.length; i++){
                    if(containsChar(word,characterMarkedAsX[i] ) ){
                        return false;
                    }
                }

                return true;
            }

            public boolean containsChar(String s, char search) {
                if (s.length() == 0)
                    return false;
                else{return s.charAt(0) == search || containsChar(s.substring(1), search);}
            }

    /Predict a word by picking a random word from the list/
            private String selectSecretWord(int index) {
                String line=null;
                try {
            /Read the file and pick a word randomly/
                    EasyReader inFile = new EasyReader(fileName);
                    int count =0;
                    while ( (line = inFile.readLine()) != null  )
                    {
                        if( count == index+1){
                            break;
                        }
                        count++;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                return line;
            }

            //Select a word randomly from the file
            private String getRandomWord() {
                String line=null;
                try {
            /Read the file and pick a word randomly/
                    EasyReader inFile = new EasyReader(fileName);
                    int randomNum = ThreadLocalRandom.current().nextInt(1, wordSize + 1);
                    int count =0;
                    while ( (line = inFile.readLine()) != null  )
                    {
                        //For the first attempt pick it randomly
                        if( count == randomNum){
                            break;
                        }
                        count++;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                firstTime=false;
                return line;
            }

            private int getTotalWordsForAttempt() {
                String line=null;
                int totalWordPossible=0;
                try {
            /Read the file and pick a word randomly/
                    EasyReader inFile = new EasyReader(fileName);
                    int randomNum = ThreadLocalRandom.current().nextInt(1, wordSize + 1);
                    int count =0;
                    while ( (line = inFile.readLine()) != null  )
                    {
                        //For the first attempt pick it randomly
                        for(int i=0; i< characterMarkedAsX.length; i++){
                            if(containsChar(line,characterMarkedAsX[i] ) ){
                                totalWordPossible++;
                            }
                        }

                        count++;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                totalWordsAvailable= totalWordPossible;
                return totalWordPossible;
            }

import sheffield.EasyReader;
import sheffield.EasyWriter;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

            public class ComputerGuesses extends BaseGame{
                private String secretWord = "";
                private final int maxAttempts = 6;
                private int attemptsLeft = maxAttempts;
                private String [] attemptedWord= new String[7];
                private String fileName = "D:\\Moses\\upwork\\enoch\\my project 2024\\my project 2024\\sgb-words.txt";
                private final int wordSize = 5757;
                private char [] characterMarkedAsX= new char[5*6]; //26 Alphabetical letters
                private int characterMarkedAsXSize =0;
                private  int totalWordsAvailable = 5757;
                private boolean firstTime =true;

                private String [] availableAttempts;

                public ComputerGuesses(EasyReader keyboard, EasyWriter screen) {
                    super(keyboard,screen);
                }

                @Override
                public void run() {

                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Constructing a new game with Computer guesses");
                    System.out.println("Select a word by choosing a number between 0 and "+(wordSize-1) +" : ");
                    int selection = scanner.nextInt();

                    //Read the word that the user picked
                    secretWord =selectSecretWord(selection);

                    while (attemptsLeft > 0) {
                        System.out.println("Attempt " + (maxAttempts - attemptsLeft + 1) + " of " + maxAttempts);
                        //Computer randomly select a word from the file
                        String guessInput = getRandomWord();

            /add to the attempted list/
                                attemptedWord [maxAttempts - attemptsLeft + 1] =guessInput;

                        //If it is the first attempt skip this
                        if((maxAttempts - attemptsLeft + 1) !=1){
                            // System.out.println("Total number of possible words is : "+getTotalWordsForAttempt());
                            //System.out.println("Selecting a random word number : "+getTotalWordsForAttempt()+"/"+totalWordsAvailable);
                            System.out.print("Correct word : "+secretWord +", previous guess : "+attemptedWord[(maxAttempts - attemptsLeft + 0 )]+"\n");

                        }

            /validate the input/
                        while (validateInput(guessInput)==false){
                            //System.out.println("Word is not valid – try again");
                            guessInput = getRandomWord();
                        }

                        //System.out.println("character not wanted  : " + Arrays.toString(characterMarkedAsX).toCharArray().toString());

                        System.out.println("Guess is : " + guessInput);
                        String response = generateResponse(guessInput);
                        System.out.println(response);

                        attemptsLeft--;

                    }
                }

                private String generateResponse(String guess) {
                    StringBuilder response = new StringBuilder();

                    for (int i = 0; i < guess.length(); i++) {
                        char guessedChar = guess.charAt(i);
                        char secretChar = secretWord.charAt(i);

                        if (guessedChar == secretChar) {
                            response.append("+");
                        } else if (secretWord.contains(String.valueOf(guessedChar))) {
                            response.append("*");
                        } else {
                            response.append("X");

                            /*update the list of letter marked as X */
                            characterMarkedAsX [characterMarkedAsXSize] =guessedChar;
                            characterMarkedAsXSize++;

                        }
                    }

                    return response.toString();
                }

                /*
                 * Validate the word input by the user it should check the size and if the word is valid */
                private boolean validateInput(String word) {
        /Check the length of the word/

                    if (word.length() != secretWord.length()) {
                        System.out.println("Invalid input length. Please enter a word with " + secretWord.length() + " characters.");
                        return false;
                    }

        /Check if it is a valid word only letters/
                    if (!word.matches("[a-zA-Z]+")) {
                        System.out.println("Invalid word entered. Please use characters from a-z only.");
                        return false;
                    }

        /Check if the word contains the characters marked as X on the previous guess/
                    for(int i=0; i< characterMarkedAsX.length; i++){
                        if(containsChar(word,characterMarkedAsX[i] ) ){
                            return false;
                        }
                    }

                    return true;
                }

                public boolean containsChar(String s, char search) {
                    if (s.length() == 0)
                        return false;
                    else{return s.charAt(0) == search || containsChar(s.substring(1), search);}
                }

    /Predict a word by picking a random word from the list/
                private String selectSecretWord(int index) {
                    String line=null;
                    try {
            /Read the file and pick a word randomly/
                        EasyReader inFile = new EasyReader(fileName);
                        int count =0;
                        while ( (line = inFile.readLine()) != null  )
                        {
                            if( count == index+1){
                                break;
                            }
                            count++;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return line;
                }

                //Select a word randomly from the file
                private String getRandomWord() {
                    String line=null;
                    try {
            /Read the file and pick a word randomly/
                        EasyReader inFile = new EasyReader(fileName);
                        int randomNum = ThreadLocalRandom.current().nextInt(1, wordSize + 1);
                        int count =0;
                        while ( (line = inFile.readLine()) != null  )
                        {
                            //For the first attempt pick it randomly
                            if( count == randomNum){
                                break;
                            }
                            count++;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    firstTime=false;
                    return line;
                }

                private int getTotalWordsForAttempt() {
                    String line=null;
                    int totalWordPossible=0;
                    try {
            /Read the file and pick a word randomly/
                        EasyReader inFile = new EasyReader(fileName);
                        int randomNum = ThreadLocalRandom.current().nextInt(1, wordSize + 1);
                        int count =0;
                        while ( (line = inFile.readLine()) != null  )
                        {
                            //For the first attempt pick it randomly
                            for(int i=0; i< characterMarkedAsX.length; i++){
                                if(containsChar(line,characterMarkedAsX[i] ) ){
                                    totalWordPossible++;
                                }
                            }

                            count++;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    totalWordsAvailable= totalWordPossible;
                    return totalWordPossible;
                }


